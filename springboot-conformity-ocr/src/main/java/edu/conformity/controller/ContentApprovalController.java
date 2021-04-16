package edu.conformity.controller;

import java.io.IOException;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.aip.contentcensor.AipContentCensor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import edu.conformity.util.JsonChange;

@RestController
@RequestMapping(value = "/content")
public class ContentApprovalController {

	private static final String APP_ID = "17258144";
	private static final String API_KEY = "aLqkiDK57CHjVNaj3kff5aI6";
	private static final String SECRET_KEY = "eOEiyU5rsHhxXVfuRDZGOCeYwzbMoNVH";
	
	private AipContentCensor client = new AipContentCensor(APP_ID, API_KEY, SECRET_KEY);
	
	@RequestMapping(value = "/image", method = RequestMethod.POST)
	public Map<Object, Object> image(MultipartFile file) throws Exception {
		// 设置网络连接参数
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);
		
		// 设置为二进制数组
		byte[] buf = file.getBytes();
		JSONObject res = client.imageCensorUserDefined(buf, null);
		System.out.println(res.toString(2));
		
		Map map = JsonChange.json2map(res.toString());
		String str = (String) map.get("conclusion");
		System.out.println(str);
		
		return map;
	}
	
	@RequestMapping(value = "/text", method = RequestMethod.GET)
	public Map<Object, Object> text(@RequestParam("str") String str) throws Exception {
		JSONObject res = client.antiSpam(str, null);
		
		Map map = JsonChange.json2map(res.toString());
		Map map2 = (Map) map.get("result");
		// spam参数：0表示非违禁，1表示违禁，2表示建议人工复审
		int spam = (Integer) map2.get("spam");
		System.out.println("spam: " + spam);
		
		return map;
	}
	
}
