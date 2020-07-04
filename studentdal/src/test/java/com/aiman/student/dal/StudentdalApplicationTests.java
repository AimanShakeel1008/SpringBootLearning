package com.aiman.student.dal;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.aiman.student.dal.entities.Student;
import com.aiman.student.dal.repos.StudentRepository;

@SpringBootTest
class StudentdalApplicationTests {

	@Autowired
	private StudentRepository studentRepository;

	@Test
	public void testCreateStudent() {
		Student student = new Student();
		student.setName("Mark");
		student.setCourse("MySQl");
		student.setFees(5000d);
		studentRepository.save(student);
	}

	@Test
	public void testStudentFindById() {

		Optional<Student> student = studentRepository.findById(1l);
		if (student.isPresent()) {
			System.out.println(student.get());
		}
	}

	@Test
	public void testStudentUpdate() {

		Optional<Student> student = studentRepository.findById(1l);
		if (student.isPresent()) {
			Student fetchedStudent = student.get();
			fetchedStudent.setFees(7000d);
			studentRepository.save(fetchedStudent);

		}
	}

	@Test
	public void testStudentDelete() {
		Student student = new Student();
		student.setId(1l);
		studentRepository.delete(student);
	}
}
