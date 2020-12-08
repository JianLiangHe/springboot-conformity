package edu.conformity.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
	
	@RequestMapping(value = "exportExcelByZip", method = RequestMethod.GET)
	public void exportExcelByZip(HttpServletResponse response) {
		//F:\hejianliang\temp
		ExcelWriter writer = null;
        OutputStream out = null;
        try {
            List userList = userService.findAll();
            String fileName = "用户信息表格";
            String fileUrl = "F:/hejianliang/temp/" + fileName + ".xlsx";
            out = new FileOutputStream(fileUrl);
            writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
            Sheet sheet = new Sheet(1, 0, User.class);
            sheet.setSheetName("用户信息");
            writer.write(userList, sheet);
            out.flush();
            
            File file = new File(fileUrl);
            // 导出zip文件
            String zipName = "用户信息表格.zip";
            String zipPath = "F:/hejianliang/temp/" + zipName;
            ZipOutputStream zipOutput = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipPath)));
            ZipEntry zEntry = new ZipEntry(file.getName());
            zipOutput.putNextEntry(zEntry);
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[1024];
            int read = 0;
            while ((read = bis.read(buffer)) != -1) {
            	zipOutput.write(buffer, 0, read);
            }
            bis.close();
            zipOutput.close();
            
            out = response.getOutputStream();
            FileInputStream fis = new FileInputStream(zipPath);
            response.setCharacterEncoding("utf-8");
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(zipName, "UTF-8"));
			while((read = fis.read(buffer)) != -1){
                out.write(buffer, 0, read);
            }
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
	
}
