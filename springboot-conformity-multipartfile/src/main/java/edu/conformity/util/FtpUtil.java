package edu.conformity.util;

import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

@Component
public class FtpUtil {

	@Value("${ftp.host}")
	private static String host;
	
	@Value("${ftp.port}")
	private static int port;
	
	@Value("${ftp.userName}")
	private static String userName;
	
	@Value("${ftp.password}")
	private static String password;
	
	@Value("${ftp.rootPath}")
	private static String rootPath;
	
	@Value("${ftp.fileUrl}")
	private static String fileUrl;
	
	@Value("${ftp.host}")
    public void setHost(String host) {
        FtpUtil.host = host;
    }
    @Value("${ftp.port}")
    public void setPort(int port) {
        FtpUtil.port = port;
    }
    @Value("${ftp.userName}")
    public void setUserName(String userName) {
        FtpUtil.userName = userName;
    }
    @Value("${ftp.password}")
    public void setPassword(String password) {
        FtpUtil.password = password;
    }
    @Value("${ftp.rootPath}")
    public void setRootPath(String rootPath) {
        FtpUtil.rootPath = rootPath;
    }
    @Value("${ftp.fileUrl}")
    public void setFileUrl(String fileUrl) {
        FtpUtil.fileUrl = fileUrl;
    }
	
	private static ChannelSftp getChannel() throws JSchException {
		JSch jsch = new JSch();
		
		Session sshSession = jsch.getSession(userName, host, port);
		sshSession.setPassword(password);
		
		Properties sshConfig = new Properties();
        // 设置第一次登陆的时候提示，可选值：(ask | yes | no)
        sshSession.setConfig("StrictHostKeyChecking", "no");
        sshSession.setConfig(sshConfig);
        //设置登陆超时时间
        // 注意！！这里不设置超时间会报错
        sshSession.connect(60000);

        Channel channel = sshSession.openChannel("sftp");
        channel.connect(1000);

        return (ChannelSftp) channel;
	}
	
	 /**
     * ftp上传文件
     *
     * @param inputStream 文件io流
     * @param imagePath   路径，不存在就创建目录
     * @param imagesName  文件名称
     * @return urlStr 文件的存放路径
     */
    public static String putFile(InputStream inputStream, String fileName, String type) {
        try {
            ChannelSftp sftp = getChannel();
            //String path = rootPath + "/" + type + "/";
            String path = rootPath + "/";
            createDir(path, sftp);

            // 上传文件
            sftp.put(inputStream, path + fileName);
            System.out.println("上传成功！");
            sftp.quit();
            sftp.exit();

            // 处理返回的路径
            String resultFile;
            //resultFile = fileUrl + type + "/" + fileName;
            resultFile = fileUrl + "/" + fileName;
            
            return resultFile;

        } catch (Exception e) {
            System.out.println("上传失败：" + e.getMessage());
        }
        return "";
    }
    
    /**
     * 创建目录
     */
    private static void createDir(String path, ChannelSftp sftp) throws SftpException {
        String[] folders = path.split("/");
        sftp.cd("/");
        for (String folder : folders) {
            if (folder.length() > 0) {
                try {
                    sftp.cd(folder);
                } catch (SftpException e) {
                    sftp.mkdir(folder);
                    sftp.cd(folder);
                }
            }
        }
    }

    /**
     * 删除文件
     */
    public static void delFile(String fileName,String type) {
        try {
            ChannelSftp sftp = getChannel();
            String path = rootPath + "/" + type + "/" + fileName;
            sftp.rm(path);
            sftp.quit();
            sftp.exit();
        } catch (Exception e) {
            System.out.println(" 删除失败：" + e.getMessage());
        }
    }
	
}
