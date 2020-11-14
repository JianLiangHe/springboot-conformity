package edu.conformity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.conformity.template.HadoopTemplate;

@RestController
@RequestMapping(value = "/hdfs/")
public class HdfsController {

	@Autowired
	private HadoopTemplate hadoopTemplate;
	
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public void upload(String srcFile) {
		hadoopTemplate.uploadFile(srcFile);
	}
	
}
