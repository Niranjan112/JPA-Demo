package com.example.jpademo.Entity;

import javax.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    // By setting it to LAZY it will do lazy fetch i.e it will populate passport when you call getPassport().
    // By default it's Eager and it will populate passport at the time of fetching student only.
    @OneToOne(fetch = FetchType.LAZY)
    private Passport passport; // Student -> Passport

    /**
     * In Spring Data JPA, it is recommended to provide an empty constructor for entity classes.
     * This is because the framework uses reflection to create instances of entity classes,
     * and the default constructor is used to create an instance of the entity class.
     *
     * If an entity class does not have an empty constructor,
     * Spring Data JPA will not be able to create instances of the entity class.
     *
     * This can cause errors and make it difficult to use the framework.
     * */
    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
