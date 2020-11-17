package edu.conformity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.conformity.service.HdfsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/hdfs/")
public class HdfsController {

	@Autowired
	private HdfsService service;
	
	@RequestMapping(value = "mkdir", method = RequestMethod.GET)
	public String mkdir(
			@RequestParam(value = "path", required = true) String path
			) {
		try {
			service.mkdir(path);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	@RequestMapping(
			value = "createFile", 
			method = RequestMethod.POST,
			headers = "content-type=multipart/form-data")
	public String createFile(
			 @RequestParam(value = "path", required = true) String path,
			MultipartFile file 
			) {
		String msg = null;
		
		try {
			service.createFile(path, file);
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
		} finally {
			return msg;
		}
	}
	
	@RequestMapping(value = "readFileToString", method = RequestMethod.GET)
	public String readFileToString(
			@RequestParam(value = "path", required = true) String path
			) {
		String msg = null;
		try {
			msg = service.readFileToString(path);
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
		} finally {
			return msg;
		}
	}
	
}
