package com.example.jpademo.repository;

import com.example.jpademo.Entity.Passport;
import com.example.jpademo.Entity.Student;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest
class StudentRepositoryTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @Transactional
    // We are using Transactional here because it's a lazy fetch and
    // When we use @Transactional the hibernate session will not get closed after calling entityManager.find()
    // If we don't use @Transactional the session will get closes after entityManager.find() and throw an error for student.getPassport()
    // In case of eager-fetch we can remove @Transactional anno.
    public void retrieveStudentAndPassportDetails() {
        Student student = entityManager.find(Student.class, 20001L);

        logger.info("Student -> {}", student);
        logger.info("Student's Passport -> {}", student.getPassport());
    }

    @Test
    public void someTestToUnderstandPersistanceContext() {
        // As we are calling a Transactional method but here transactional anno is not required.
        studentRepository.someOperationToUnderstandPersistenceContext();
    }

    @Test
    @Transactional
    public void retrievePassportAndAssociatedStudent() {
        /**
         * Fetching Passport and it's Student to check bi-directional one to one relationship
         * configured in Passport entity.
         * */
        Passport passport = entityManager.find(Passport.class, 40001L);
        logger.info("Passport -> {}", passport);
        logger.info("Student -> {}", passport.getStudent());
    }
}