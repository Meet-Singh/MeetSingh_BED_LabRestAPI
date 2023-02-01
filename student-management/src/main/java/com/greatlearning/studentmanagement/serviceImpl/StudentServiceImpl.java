package com.greatlearning.studentmanagement.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.greatlearning.studentmanagement.model.Student;
import com.greatlearning.studentmanagement.service.StudentService;
import com.greatlearning.studentmanagement.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Override
	public void saveOrUpdate(Student student) {
		studentRepository.save(student);
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public List<Student> getStudentByName(String name) {
		return studentRepository.findStudentByName(name);
	}
	
	@Override
	public Student getStudentById(long id) {
		return studentRepository.findById(id).get();
	}

	@Override
	public void deleteStudent(long id) {
		studentRepository.deleteById(id);

	}

}
