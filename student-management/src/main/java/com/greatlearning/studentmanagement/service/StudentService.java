package com.greatlearning.studentmanagement.service;

import java.util.List;

import com.greatlearning.studentmanagement.model.Student;

public interface StudentService {

	public void saveOrUpdate(Student student);

	public List<Student> getAllStudents();

	public List<Student> getStudentByName(String name);

	public void deleteStudent(long id);

	public Student getStudentById(long id);
}
