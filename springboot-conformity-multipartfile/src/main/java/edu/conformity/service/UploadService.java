package edu.conformity.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

	String uploadImage(MultipartFile file);
	
	String uploadImageByFtp(MultipartFile file);
	
}
