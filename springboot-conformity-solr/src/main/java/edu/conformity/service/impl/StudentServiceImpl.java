package edu.conformity.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.conformity.service.StudentService;
import edu.conformity.solr.entity.Student;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private SolrClient solrClient;
	
	@Override
	public List<Student> queryAll() {
		List<Student> studentList = new ArrayList<Student>();
		SolrQuery solrQuery = new SolrQuery(); 
		solrQuery.setQuery("*:*");
		try {
			QueryResponse queryResponse = solrClient.query(solrQuery);
			
			if (queryResponse != null) {
				studentList = queryResponse.getBeans(Student.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return studentList;
	}

}
