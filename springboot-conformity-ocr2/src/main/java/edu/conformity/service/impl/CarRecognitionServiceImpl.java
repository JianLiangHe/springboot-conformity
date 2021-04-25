package edu.conformity.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.aip.ocr.AipOcr;

import edu.conformity.service.CarRecognitionService;
import edu.conformity.util.JsonChange;
import edu.conformity.util.OcrUtil;

@Service
public class CarRecognitionServiceImpl implements CarRecognitionService {

	@Value("${baiduclould.ocr_car_recognition.app_id}")
	private String APP_ID;
	
	@Value("${baiduclould.ocr_car_recognition.api_key}")
	private String API_KEY;
	
	@Value("${baiduclould.ocr_car_recognition.secret_key}")
	private String SECRET_KEY;
	
	@Override
	public Map<String, Object> plateNumberRecognition(MultipartFile file) {
		Map<String, Object> resultMap = null;
		
		AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
		// 传入可选参数调用接口
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("language_type", "CHN_ENG");
		options.put("detect_direction", "true");
		options.put("detect_language", "true");
		options.put("probability", "true");
		
		// 将文件转换为二进制数组
		try {
			byte[] buf = file.getBytes();
			JSONObject res = client.basicGeneral(buf, options);
			
			resultMap = JsonChange.json2map(res.toString());
			
			OcrUtil.printWordsResult(resultMap);
			
			// 判断识别出来的内容是否是车牌号码
			List<Map<String, Object>> wordsResult = (List<Map<String, Object>>) resultMap.get("words_result");
			
			Map<String, Object> wordsMap = wordsResult.get(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return resultMap;
		}
	}

}
