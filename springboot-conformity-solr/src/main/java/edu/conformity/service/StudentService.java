package edu.conformity.service;

import java.util.List;

import edu.conformity.solr.entity.Student;

public interface StudentService {

	List<Student> queryAll();
	
}
