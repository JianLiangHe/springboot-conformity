package edu.conformity.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * 车辆识别业务层
 * @author hejianliang
 *
 */
public interface CarRecognitionService {

	/**
	 * 车牌号码识别
	 * @param file
	 * @return
	 */
	Map<String, Object> plateNumberRecognition(MultipartFile file);
	
}
