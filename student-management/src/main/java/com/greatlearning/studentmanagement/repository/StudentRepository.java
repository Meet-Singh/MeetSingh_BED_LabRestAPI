package com.greatlearning.studentmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greatlearning.studentmanagement.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	@Query(value = "select * from students s where s.first_name like %:name% or s.last_name like %:name%", nativeQuery = true)
	List<Student> findStudentByName(@Param("name") String name);
}
