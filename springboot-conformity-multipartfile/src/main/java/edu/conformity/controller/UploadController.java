package edu.conformity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.conformity.service.UploadService;
import edu.conformity.util.FtpUtil;

@RestController
@RequestMapping("/upload/")
public class UploadController {

	@Autowired
	private UploadService uploadService;
	
	@RequestMapping(value = "uploadImage", method = RequestMethod.POST)
	public ResponseEntity<String> uploadImage(
			MultipartFile file
			) {
		String url = uploadService.uploadImage(file);
		
		if (url == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(url);
	}
	
	@RequestMapping(value = "uploadImageByFtp", method = RequestMethod.POST)
	public ResponseEntity<String> uploadImageByFtp(MultipartFile file) {
		String url = uploadService.uploadImageByFtp(file);
		
		if (url == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(url);
	}
	
}
