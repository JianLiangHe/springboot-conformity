package edu.conformity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.conformity.service.HdfsService;

@RestController
@RequestMapping("/hdfs/")
public class HdfsController {

	@Autowired
	private HdfsService service;
	
	@RequestMapping(value = "mkdir", method = RequestMethod.GET)
	public String mkdir(String path) {
		try {
			service.mkdir(path);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	@PostMapping("/createFile")
    public Object createFile(String path, MultipartFile file){
        try {
            service.createFile(path, file);
            return RtnData.ok();
        } catch (Exception e) {
            return RtnData.fail(e);
        }
    }

    @GetMapping("/readFileToString")
    public Object readFileToString(String path){
        try {
            return RtnData.ok(service.readFileToString(path));
        } catch (Exception e) {
            return RtnData.fail(e);
        }
    }
	
}
