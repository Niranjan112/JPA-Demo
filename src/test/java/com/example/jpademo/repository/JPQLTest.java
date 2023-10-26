package com.example.jpademo.repository;

import com.example.jpademo.Entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class JPQLTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    @Test
    void jpql_basic() {
        Query query = entityManager.createNamedQuery("query_get_all_courses");
        List result = query.getResultList();
        logger.info("Using simple JPQL -> {}", result);
    }

    @Test
    void jpql_typed() {
        TypedQuery<Course> query = entityManager.createNamedQuery("query_get_all_courses", Course.class);
        List<Course> result = query.getResultList();
        logger.info("Using typed JPQL -> {}", result);
    }

    @Test
    void jpql_where() {
        TypedQuery<Course> query = entityManager.createQuery("Select c From Course c where name like 'New%'", Course.class);
        List<Course> result = query.getResultList();
        logger.info("Using JPQL with where -> {}", result);
    }

}