package edu.conformity.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.conformity.service.UploadService;
import edu.conformity.util.FtpUtil;

@Service
public class UploadServiceImpl implements UploadService {

	private static final List<String> CONTENT_TYPES = Arrays.asList("image/gif","image/jpeg","image/png");
	
	@Autowired
	private FtpUtil ftpUtil;
	
	@Override
	public String uploadImage(MultipartFile file) {
		// 获取文件类型
		String originalFilename = file.getOriginalFilename();
		
		//检验文件类型
		String contentType = file.getContentType();
		
		if (!CONTENT_TYPES.contains(contentType)) {
			System.out.println("文件类型不合法：" + originalFilename);
			return null;
		}
		
		try {
			// 检验文件的内容， ImageIO读取文件内容
			BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
			
			if (bufferedImage == null) {
				System.out.println("文件内容不合法：" + originalFilename);
				return null;
			}
			
			// 保存到服务器
			originalFilename =  String.valueOf(System.currentTimeMillis()) 
					+ originalFilename.substring(originalFilename.indexOf("."), originalFilename.length());
			file.transferTo(new File("F:\\tmp\\" + originalFilename));
			
			// 返回url
			return "F:\\tmp\\" + originalFilename;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String uploadImageByFtp(MultipartFile file) {
		String fileUrl = null;
		String originalFilename = file.getOriginalFilename();
		originalFilename =  String.valueOf(System.currentTimeMillis()) 
				+ originalFilename.substring(originalFilename.indexOf("."), originalFilename.length());
		try {
			fileUrl = ftpUtil.putFile(file.getInputStream(), originalFilename, "hejl");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			return fileUrl;
		}
	}

}
