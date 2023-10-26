package com.example.jpademo.repository;

import com.example.jpademo.Entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository courseRepository;

    @Test
    void findById() {
        Course course = courseRepository.findById(10001L);
        assertEquals("JPA", course.getName());
    }

    @Test
    @DirtiesContext // Will reset the data after executing below test. The delete op will be undone.
    void deleteById() {
        courseRepository.deleteById(10002L);

        Course course = courseRepository.findById(10002L);
        assertNull(course);
    }

    @Test
    @DirtiesContext // Will reset the data after executing below test. The delete op will be undone.
    void save() {
        Course course = courseRepository.findById(10001L);
        assertEquals("JPA", course.getName());

        course.setName("JPA new");
        courseRepository.save(course);

        Course updatedCourse = courseRepository.findById(10001L);
        assertEquals("JPA new", updatedCourse.getName());
    }

    @Test
    @DirtiesContext // Will reset the data after executing below test. The delete op will be undone.
    void playWithEntityManager() {
        courseRepository.playWithEntityManager();
    }


}