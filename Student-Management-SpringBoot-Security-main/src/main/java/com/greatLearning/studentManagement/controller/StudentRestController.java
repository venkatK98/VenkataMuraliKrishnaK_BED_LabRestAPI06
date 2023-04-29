package com.greatLearning.studentManagement.controller;

import com.greatLearning.studentManagement.Exception.StudentException;
import com.greatLearning.studentManagement.entity.Student;
import com.greatLearning.studentManagement.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/studentApis")
@Slf4j
public class StudentRestController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/save")
	public Student save(@RequestBody Student student) {
		Student theStudent = new Student();
		if (student.getId() != 0) {
			try {
				theStudent = studentService.findById(student.getId());
				theStudent.setFirstName(student.getFirstName());
				theStudent.setLastName(student.getLastName());
				theStudent.setCourse(student.getCourse());
				theStudent.setCountry(student.getCountry());
			} catch (StudentException e) {
				log.error(e.getMessage());
			}
		} else
			theStudent = new Student(student.getFirstName(), student.getLastName(), student.getCourse(),
					student.getCountry());

		return studentService.save(theStudent);
	}

}
