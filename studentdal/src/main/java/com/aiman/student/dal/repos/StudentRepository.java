package com.aiman.student.dal.repos;

import org.springframework.data.repository.CrudRepository;

import com.aiman.student.dal.entities.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

}
