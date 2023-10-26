package com.example.jpademo.jpa;

import com.example.jpademo.Entity.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PersonJpaRepository {

    //connect to database
    @PersistenceContext
    EntityManager entityManager;

    public List<Person> findAll() {
        TypedQuery<Person> namedQuery = entityManager.createNamedQuery("find_all_persons", Person.class);
        return namedQuery.getResultList();
    }

    public Person findById(int id) {
        return entityManager.find(Person.class, id);
    }

    public Person update(Person person) {
        return entityManager.merge(person); // If person found with id then it will update else insert a new record
    }

    public Person insert(Person person) {
        return entityManager.merge(person); // If person found with id then it will update else insert a new record
    }

    public void delete(int id) {
        Person person = this.findById(id);
        entityManager.remove(person);
    }
}
