package edu.conformity.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.conformity.pojo.User;
import edu.conformity.service.UserService;
import edu.conformity.util.ExcelUtil;

@RestController
@RequestMapping("/user/")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "exportExcel", method = RequestMethod.GET)
	public void exportExcel(HttpServletResponse response) {
		OutputStream outputStream = null;
		
		try {
			String fileName = "用户信息表.xlsx";
			outputStream = response.getOutputStream();
	        response.setContentType("application/octet-stream ");
	        // 表示不能用浏览器直接打开
	        response.setHeader("Connection", "close");
	        // 告诉客户端允许断点续传多线程连接下载
	        response.setHeader("Accept-Ranges", "bytes");
	        response.setHeader("Content-Disposition",
	                "attachment;filename=" + fileName);
	        response.setCharacterEncoding("UTF-8");
	        
	        // 新增sheet工作表
	        XSSFWorkbook wb = new XSSFWorkbook();
	        XSSFSheet sheet = wb.createSheet("用户列表");
	        
	        // 在sheet中新建首行元素
	        int rowIdx = 0;
	        XSSFRow headRow = sheet.createRow(rowIdx++);
	        String[] heads = new String[]{"id", "姓名", "性别", "地址", "联系方式", "身份证"};
	        int cellIdx = 0;
	        for (String string : heads) {
                headRow.createCell(cellIdx++).setCellValue(string);
            }
	        
	        List<User> userList = userService.findAll();
	        Iterator<User> it = userList.iterator();
	        int count = 0;
	        while (it.hasNext()) {
	        	// 每次循环中创建一个row，将用户信息设置进去
	        	User user = it.next();
	        	XSSFRow row = sheet.createRow(rowIdx++);
	        	cellIdx = 0;
	        	count++;
	        	row.createCell(cellIdx++).setCellValue(count);
	        	row.createCell(cellIdx++).setCellValue(user.getName());
                row.createCell(cellIdx++).setCellValue(user.getSex());
                row.createCell(cellIdx++).setCellValue(user.getAddress());
                row.createCell(cellIdx++).setCellValue(user.getPhone());
                row.createCell(cellIdx++).setCellValue(user.getIdcard());
	        }
	        
	        // 将Excel写出到输出流中
	        wb.write(outputStream);
	        outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@RequestMapping(value = "importExcel", method = RequestMethod.POST)
	public void importExcel(MultipartFile file) {
		InputStream inputStream = null;
		Workbook wb = null;
		List<User> userList = new ArrayList<User>();
		try {
			inputStream = file.getInputStream();
			if (ExcelUtil.isExcel2007(file.getOriginalFilename())) {
				wb = new XSSFWorkbook(inputStream);
			} else {
				wb = new HSSFWorkbook(inputStream);
			}
			
			Sheet sheet = wb.getSheetAt(0); // 获取第一张表
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				int id = (int) row.getCell(0).getNumericCellValue();
				String name = row.getCell(1).getStringCellValue();
				String sex = row.getCell(2).getStringCellValue();
				String address = row.getCell(3).getStringCellValue();
				String phone = row.getCell(4).getStringCellValue();
				String idcard = row.getCell(5).getStringCellValue();
				User user = new User(id, name, sex, address, phone, idcard);
				userList.add(user);
			}
			
			userService.save(userList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
