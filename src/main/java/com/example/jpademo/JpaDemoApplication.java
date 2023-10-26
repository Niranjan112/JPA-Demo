package com.example.jpademo;

import com.example.jpademo.Entity.Course;
import com.example.jpademo.Entity.Person;
import com.example.jpademo.jpa.PersonJpaRepository;
import com.example.jpademo.repository.CourseRepository;
import com.example.jpademo.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PersonJpaRepository personJpaRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		logger.info("User -> {}", personJpaRepository.findById(10001));
//		logger.info("Inserting... {}", personJpaRepository.insert(new Person("Niranjan", "Mumbai", new Date())));
//		logger.info("Updating... {}", personJpaRepository.update(new Person(1,"Niranjan", "Pune", new Date())));
//		logger.info("All Persons... {}", personJpaRepository.findAll());
//		personJpaRepository.delete(10001);

//		Course course = courseRepository.findById(10001L);
//		logger.info("Course 10001 -> {}", course);
//		courseRepository.save(new Course("Microservices"));
//
//		courseRepository.playWithEntityManager();

		studentRepository.saveStudentWithPassport();
	}
}
