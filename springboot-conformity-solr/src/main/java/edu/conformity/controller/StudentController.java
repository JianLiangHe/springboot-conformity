package edu.conformity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.conformity.service.StudentService;
import edu.conformity.solr.entity.Student;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
	
	/*@Autowired
	private StudentService studentService;
	
	@RequestMapping(value = "/queryAll", method = RequestMethod.GET)
	public List<Student> queryAll() {
		List<Student> list = studentService.queryAll();
		return list;
	} */
	
}
