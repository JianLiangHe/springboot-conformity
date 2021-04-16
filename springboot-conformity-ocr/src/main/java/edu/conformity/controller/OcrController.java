package edu.conformity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.aip.ocr.AipOcr;

import edu.conformity.util.JsonChange;

@RestController
public class OcrController {

	private static final String APP_ID = "17247027";
	private static final String API_KEY = "1OBVubcETaoDCUxRCfeQeDjN";
	private static final String SECRET_KEY = "G4hsARotv6iweCuDvozOeHaoNqXGOTRG";
	
	@RequestMapping(value = "/ocr", method = RequestMethod.POST)
	public Map<Object, Object> ocr(MultipartFile file) throws Exception {
		AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
		// 传入可选参数调用接口
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("language", "CHN_ENG");
		options.put("detect_direction", "true");
		options.put("detect_language", "true");
		options.put("probability", "true");
		
		// 参数为二进制数组
		byte[] buf = file.getBytes();
		JSONObject res = client.basicGeneral(buf, options);
		
		Map map = JsonChange.json2map(res.toString());
		
		return map;
	}
	
	@RequestMapping(value = "/ocrStr", method = RequestMethod.POST)
	public String ocrStr(MultipartFile file) throws Exception {
		AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
		
		// 传入可选参数调用接口
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("language_type", "CHN_ENG");
		options.put("detect_direction", "true");
		options.put("detect_language", "true");
		options.put("probability", "true");
		
		String str = "";
		
		// 参数为二进制数组
		byte[] buf = file.getBytes();
		JSONObject res = client.basicGeneral(buf, options);
		
		Map map = JsonChange.json2map(res.toString());
		// 提取并打印出识别的文字
		List list = (List) map.get("words_result");
		int len = ((List) map.get("words_result")).size();
		
		for (int i = 0; i < len; i++) {
			Map obj = (Map) list.get(i);
			str = str + obj.get("words") + "\n";
		}
		
		return str;
	}
	
}
