package com.bursuc.student.dal.repos;

import org.springframework.data.repository.CrudRepository;

import com.bursuc.student.dal.entities.Student;

public interface StudentRepository extends CrudRepository<Student, Long>{

}
