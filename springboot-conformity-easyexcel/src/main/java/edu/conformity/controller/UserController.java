package edu.conformity.controller;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;

import edu.conformity.pojo.User;
import edu.conformity.service.UserService;

@RestController
@RequestMapping("/user/")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "findAll", method = RequestMethod.GET)
	public List<User> findAll() {
		return userService.findAll();
	}
	
	@RequestMapping(value = "exportExcel", method = RequestMethod.GET)
	public void exportExcel(HttpServletResponse response) {
		ExcelWriter writer = null;
		OutputStream out = null;
		
		try {
			String fileName = "用户信息表";
			List<User> userList = userService.findAll();
			out = response.getOutputStream();
			writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
			
			Sheet sheet = new Sheet(1, 0, User.class);
			sheet.setSheetName("用户信息");
			writer.write(userList, sheet);
			
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xlsx").getBytes(), "ISO8859-1"));
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.finish();
			}
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
