package edu.conformity.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.aip.ocr.AipOcr;

import edu.conformity.util.JsonChange;
import edu.conformity.util.OcrUtil;

@RestController
@RequestMapping(value = "/ocr")
public class OcrController {

	@Value("${baiduclould.ocr.app_id}")
	private String APP_ID;
	
	@Value("${baiduclould.ocr.api_key}")
	private String API_KEY;
	
	@Value("${baiduclould.ocr.secret_key}")
	private String SECRET_KEY;
	
	@RequestMapping(value = "/ocr", method = RequestMethod.POST)
	public Map<Object, Object> ocr(MultipartFile file) throws Exception {
		AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
		// 传入可选参数调用接口
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("language_type", "CHN_ENG");
		options.put("detect_direction", "true");
		options.put("detect_language", "true");
		options.put("probability", "true");
		
		// 参数为二进制数组
		byte[] buf = file.getBytes();
		JSONObject res = client.basicGeneral(buf, options);
		
		Map map = JsonChange.json2map(res.toString());
		
		// 打印识别出来的文字
		OcrUtil.printWordsResult(map);
		
		return map;
	}
	
}
