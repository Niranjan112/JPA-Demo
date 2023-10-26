package com.example.jpademo.Entity;

import javax.persistence.*;

@Entity
public class Passport {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String number;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "passport")
    private Student student; // Passport -> Student
    /**
     * Here we are using mappedBy="passport". So that no field should get created in Passport table with student_id.
     * But still we will achieve One to One bidirectional relationship.
     *
     * If we don't used mappedBy option then student_id field will get created in Passport table and we already having
     * passport_id in Student table which is not a good practice. As we unnecessarily having duplicate column.
     * */

    public Passport() {
    }

    public Passport(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }
}
