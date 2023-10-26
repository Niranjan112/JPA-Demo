package com.example.jpademo.repository;

import com.example.jpademo.Entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@SpringBootTest
class NativeQueriesTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    @Test
    void native_queries_basic() {
        Query query = entityManager.createNativeQuery("SELECT * FROM COURSE", Course.class);
        List<Course> result = query.getResultList();
        logger.info("Native query simple -> {}", result);
    }

    @Test
    void native_queries_with_parameter() {
        Query query = entityManager.createNativeQuery("SELECT * FROM COURSE where id = ?", Course.class);
        query.setParameter(1, 10001L);
        List<Course> result = query.getResultList();
        logger.info("Native query with param -> {}", result);
    }

    @Test
    void native_queries_with_named_parameter() {
        Query query = entityManager.createNativeQuery("SELECT * FROM COURSE where id = :id", Course.class);
        query.setParameter("id", 10001L);
        List<Course> result = query.getResultList();
        logger.info("Native query with named param -> {}", result);
    }

    @Test
    // As in other test class we calling JPA method and transaction anno
    // was already at entity class. Here it's a native query so this anno is required here
    @Transactional
    void native_queries_to_update() {
        Query query = entityManager.createNativeQuery("Update COURSE set last_updated_date = LOCALTIMESTAMP", Course.class);
        int noOfRowsUpdated = query.executeUpdate();
        logger.info("Native query to update -> {}", noOfRowsUpdated);
    }
}