package com.example.jpademo.repository;

import com.example.jpademo.Entity.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
@Repository
@Transactional
public class CourseRepository {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    public Course findById(Long id) {
        return entityManager.find(Course.class, id);
    }

    public Course save(Course course) {
        if(course.getId() == null) {
            entityManager.persist(course); // insert
        } else {
            entityManager.merge(course); // update
        }

        return course;
    }

    public void deleteById(Long id) {
        Course course = findById(id);
        entityManager.remove(course);
    }

    public void playWithEntityManager() {
        // 1. persist
        Course course1 = new Course("Web services");
        entityManager.persist(course1);

        // Because of @Transactional anno the entity manager will take care of further updates as well
        // within  the method and will update/persist it in DB.
        course1.setName("New Web services");

        // 2. flush and detach
        Course course2 = new Course("Angular");
        entityManager.persist(course2);
        entityManager.flush(); // This will send everything to DB that is mentioned above.

        entityManager.detach(course2); // This will no further update in DB for course2 as it is detached.
        course2.setName("New Angular"); // This will not be updated.
        entityManager.flush();

        // 3. clear
        Course course3 = new Course("React");
        entityManager.persist(course3);
        Course course4 = new Course("Vue");
        entityManager.persist(course4);
        entityManager.flush(); // This will insert course 3 and course 4.

        entityManager.clear(); // Another detached method that will detach everything instead of detaching individual course.
        course3.setName("New React"); // This will not be updated.
        course4.setName("New Vue"); // This will not be updated.
        entityManager.flush();

        // 4. refresh
        Course course5 = new Course("Node JS");
        entityManager.persist(course5);
        entityManager.flush();

        course5.setName("New Node JS");
        entityManager.refresh(course5); // This will reset to data that was used at the time of insertion (line no 67).
        entityManager.flush();
    }
}
