package com.example.jpademo.repository;

import com.example.jpademo.Entity.Passport;
import com.example.jpademo.Entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class StudentRepository {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public Student save(Student student) {
        if(student.getId() == null) {
            entityManager.persist(student); // insert
        } else {
            entityManager.merge(student); // update
        }

        return student;
    }

    public void deleteById(Long id) {
        Student student = findById(id);
        entityManager.remove(student);
    }

    public void saveStudentWithPassport() {
        Passport passport = new Passport("W4493");
        entityManager.persist(passport);

        Student student = new Student("Mike");
        student.setPassport(passport);
        entityManager.persist(student);
    }

    @Transactional
    /**
     * In JPA, we entity manager to talk to Persistence Context.
     * Entity Manager is the interface to the Persistence Context.
     * Same for hibernate the term is Session and Session factory.
     *
     * When we apply a @Transactional to a method, it's create a Session/Persistence Context.
     * In this method at DB operation 1 it will store student in Persistence Context for further operation.
     * As Persistence Context still exists and contains student, at DB operation 2 it will fetch passport.
     * For DB operation 3/4 will also work as Persistence Context will be available till end.
     * And at last it will send data to update in DB and Persistence Context will get closed.
     * @Transactional makes sure that either all operation succeeds or all fails.
     * If let's say DB operation 4 get's failed then above DB operation will get rollback to previous state.
     *
     * If we don't apply @Transactional then after DB operation 1 only the Session/Persistence Context will get closed.
     * And further DB operation will throw an error.
     * */
    public void someOperationToUnderstandPersistenceContext() {
        // DB operation 1 - Retrieve student
        Student student = entityManager.find(Student.class, 20001L);
        //Persistence Context (student)

        // DB operation 2 - Retrieve passport
        Passport passport = student.getPassport();
        //Persistence Context (student, passport)

        // DB operation 3 - update passport
        passport.setNumber("X456");
        // As passport is updated it will shows as passport++ to track by Persistence Context.
        //Persistence Context (student, passport++)

        // DB operation 4 - update student
        student.setName("Niranjan Updated");
        // As passport is updated it will shows as student++, passport++ to track by Persistence Context.
        //Persistence Context (student++, passport++)

        logger.info("Passport -> {}", passport);
        logger.info("Student -> {}", student);
    }
}
