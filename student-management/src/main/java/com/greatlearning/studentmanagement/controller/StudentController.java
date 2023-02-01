package com.greatlearning.studentmanagement.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;

import java.util.List;

import com.greatlearning.studentmanagement.model.Student;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.greatlearning.studentmanagement.service.StudentService;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentService studentService;

	@RequestMapping(path = { "/list", "/search", "/" })
	public String getAllStudents(Model model, @ModelAttribute("searchKeyword") String search) {
		if (search != null) {
			List<Student> student = studentService.getStudentByName(search);
			model.addAttribute("studentList", student);
		} else {
			List<Student> students = studentService.getAllStudents();
			model.addAttribute("studentList", students);
		}
		model.addAttribute("loggedInUserRole", SecurityContextHolder.getContext().getAuthentication().getAuthorities());

		return "student_list_home_page";
	}

	@RequestMapping("/403")
	public String showError() {
		return "forbidden_error_page";
	}

	@RequestMapping("/reset")
	public String resetSearch() {
		return "redirect:/student/list";
	}

	@RequestMapping("/showFormForAdd")
	public String getSaveStudentForm(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "add_student";
	}

	@RequestMapping("/showFormForUpdate/{id}")
	public String getUpdateStudentForm(Model model, @PathVariable("id") long id) {
		Student student = studentService.getStudentById(id);
		model.addAttribute("student", student);
		return "edit_student";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentService.saveOrUpdate(student);
		return "redirect:/student/list";
	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteStudent(@PathVariable("id") long id) {
		studentService.deleteStudent(id);
		return "redirect:/student/list";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String updateStudent(@ModelAttribute("student") Student student, @PathVariable("id") long id) {
		Student stud = studentService.getStudentById(id);
		stud.setCountry(student.getCountry());
		stud.setCourse(student.getCourse());
		stud.setFirstName(student.getFirstName());
		stud.setLastName(student.getLastName());
		studentService.saveOrUpdate(student);
		return "redirect:/student/list";
	}
}
