package com.bursuc.student.dal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bursuc.student.dal.entities.Student;
import com.bursuc.student.dal.repos.StudentRepository;

@SpringBootTest
class StudentdalApplicationTests {
	
	@Autowired
	private StudentRepository studendRepository;

	@Test
	public void testCreateStudent() {
		Student student = new Student();
		student.setName("John");
		student.setCourse("Java Web Services");
		student.setFee(30d);
		
		studendRepository.save(student);
	}
	
	@Test
	public void testFindStudentById() {
		Student student = studendRepository.findById(1l).get();
		
		System.out.println(student);
	}
	
	//Update a student
	@Test
	public void testUpdateStudent() {
		Student student = studendRepository.findById(1l).get();
		student.setFee(40d);
		
		studendRepository.save(student);
	}
	
	@Test
	public void testDeleteStudent() {
		studendRepository.deleteById(1l);
	}

}
