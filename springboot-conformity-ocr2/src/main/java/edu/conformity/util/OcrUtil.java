package edu.conformity.util;

import java.util.List;
import java.util.Map;

/**
 * ocr工具类
 * @author hejianliang
 *
 */
public class OcrUtil {
	
	/**
	 * 打印出识别的文字
	 * @param map
	 */
	public static void printWordsResult(Map map) {
		String str = "";
		List list = (List) map.get("words_result");
		int len = list.size();
		
		for (int i = 0; i < len; i++) {
			Map obj = (Map) list.get(i);
			str = (str + obj.get("words") + "\n");
		}
		
		System.out.println(str);
	}
	
}
