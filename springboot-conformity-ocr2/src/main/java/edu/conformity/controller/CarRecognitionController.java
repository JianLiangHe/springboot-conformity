package edu.conformity.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.conformity.service.CarRecognitionService;

/**
 * 车辆识别
 * @author hejianliang
 *
 */
@RestController
@RequestMapping(value = "/carRecognition")
public class CarRecognitionController {
	
	@Autowired
	private CarRecognitionService carRecognitionService;
	
	/**
	 * 车牌号码识别
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/plateNumberRecognition", method = RequestMethod.POST)
	public Map<String, Object> plateNumberRecognition(MultipartFile file) {
		Map<String, Object> resultMap = carRecognitionService.plateNumberRecognition(file);
		
		return resultMap;
	}
	
}
