package edu.conformity.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class HdfsService {

	@Value("${hdfs.path}")
	private String hdfsPath;
	
	@Value("${hdfs.user}")
	private String user;
	
	/**
	 * 获取hdfs配置信息
	 * @return
	 */
	private Configuration getConfiguration() {
		Configuration configuration = new Configuration();
		configuration.set("fs.defaultFS", hdfsPath);
		configuration.set("dfs.client.use.datanode.hostname", "true");
		return configuration;
	}
	
	/**
	 * 获取文件系统对象
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws URISyntaxException
	 */
	public FileSystem getFileSystem() throws IOException, InterruptedException, URISyntaxException {
		FileSystem fileSystem = FileSystem.get(new URI(hdfsPath), this.getConfiguration(), user);
		return fileSystem;
	}

	public boolean mkdir(String dir) throws Exception {
		if (StringUtils.isBlank(dir)) {
			return false;
		}
		if(exist(dir)){
            return true;
        }
        FileSystem fileSystem = getFileSystem();
        boolean isOk = fileSystem.mkdirs(new Path(dir));
        fileSystem.close();
        return isOk;
	}
	
	/**
     * 判断HDFS的文件是否存在
     * @param path
     * @return
     * @throws Exception
     */
    public boolean exist(String path) throws Exception {
        if(StringUtils.isBlank(path)){
            return false;
        }
        FileSystem fileSystem = getFileSystem();
        return fileSystem.exists(new Path(path));
    }
	
    /**
     * 读取路径下的文件信息
     * @param path
     * @return
     * @throws Exception
     */
    public List<Map<String,Object>> readPathInfo(String path) throws Exception {
        if(!exist(path)){
            return null;
        }
        FileSystem fs = getFileSystem();
        FileStatus[] statuses = fs.listStatus(new Path(path));
        if(statuses == null || statuses.length < 1){
            return null;
        }
        List<Map<String,Object>> list = new ArrayList<>();
        for(FileStatus fileStatus : statuses){
            Map<String,Object> map = new HashMap<>();
            map.put("filePath", fileStatus.getPath());
            map.put("fileStatus", fileStatus.toString());

            list.add(map);
        }
        return  list;
    }

    /**
     * HDFS创建文件
     * @param path
     * @param file
     * @throws Exception
     */
    public void createFile(String path, MultipartFile file) throws Exception {
        if(StringUtils.isBlank(path) || null == file){
            return;
        }
        FileSystem fs = getFileSystem();
        String fileName = file.getOriginalFilename();//文件名
        Path filePath = new Path(path + "/" + fileName);
        FSDataOutputStream outputStream = fs.create(filePath);
        outputStream.write(file.getBytes());
        outputStream.close();
        fs.close();
    }

    /**
     * 读取HDFS文件内容
     * @param path
     * @return
     * @throws Exception
     */
    public String readFileToString(String path) throws Exception{
        if(!exist(path)){
            return null;
        }
        FileSystem fs = getFileSystem();
        FSDataInputStream inputStream = null;
        try {
            inputStream = fs.open(new Path(path));
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }
            return sb.toString();
        }finally {
            if(inputStream != null){
                inputStream.close();
            }
            fs.close();
        }
    }

    /**
     * 获取目录下的文件列表
     * @param path
     * @return
     * @throws Exception
     */
    public List<Map<String,Object>> listFiles(String path) throws Exception {
        if(!exist(path)){
            return null;
        }
        FileSystem fs = getFileSystem();
        RemoteIterator<LocatedFileStatus> iterator = fs.listFiles(new Path(path), true);
        List<Map<String,Object>> list = new ArrayList<>();
        while (iterator.hasNext()){
            LocatedFileStatus fileStatus = iterator.next();
            Map<String,Object> map = new HashMap<>();
            map.put("filePath", fileStatus.getPath().toString());
            map.put("fileName", fileStatus.getPath().getName());

            list.add(map);
        }
        return  list;
    }

    /**
     * 重命名HDFS文件
     * @param oldName
     * @param newName
     * @return
     * @throws Exception
     */
    public boolean renameFile(String oldName, String newName)throws Exception{
        if(!exist(oldName) || StringUtils.isBlank(newName)){
            return false;
        }
        FileSystem fs = getFileSystem();
        boolean isOk = fs.rename(new Path(oldName), new Path(newName));
        fs.close();
        return isOk;
    }

    /**
     * 删除HDFS文件
     * @param path
     * @return
     * @throws Exception
     */
    public boolean deleteFile(String path)throws Exception {
        if(!exist(path)){
            return false;
        }
        FileSystem fs = getFileSystem();
        boolean isOk = fs.deleteOnExit(new Path(path));
        fs.close();
        return isOk;
    }

    /**
     * 上传文件到HDFS
     * @param path
     * @param uploadPath
     * @throws Exception
     */
    public void uploadFile(String path,String uploadPath) throws Exception{
        if(StringUtils.isBlank(path) || StringUtils.isBlank(uploadPath)){
            return;
        }
        FileSystem fs = getFileSystem();
        fs.copyFromLocalFile(new Path(path), new Path(uploadPath));
        fs.close();
    }

    /**
     * 从HDFS下载文件
     * @param path
     * @param downloadPath
     * @throws Exception
     */
    public void downloadFile(String path, String downloadPath) throws Exception{
        if(StringUtils.isBlank(path) || StringUtils.isBlank(downloadPath)){
            return;
        }
        FileSystem fs = getFileSystem();
        fs.copyToLocalFile(new Path(path), new Path(downloadPath) );
        fs.close();
    }

    /**
     * 拷贝HDFS文件
     * @param sourcePath
     * @param targetPath
     * @throws Exception
     */
    public void copyFile(String sourcePath, String targetPath) throws Exception{
        if(StringUtils.isBlank(sourcePath) || StringUtils.isBlank(targetPath)){
            return;
        }
        FileSystem fs = getFileSystem();
        FSDataInputStream inputStream = null;
        FSDataOutputStream outputStream = null;
        try{
            inputStream = fs.open(new Path(sourcePath));
            outputStream = fs.create(new Path(targetPath));
            //todo IOUtils.copyBytes(inputStream, outputStream, , false);
        }finally {
            if(inputStream != null){
                inputStream.close();
            }
            if(outputStream != null){
                outputStream.close();
            }
            fs.close();
        }
    }

    /**
     * 读取HDFS文件并返回byte[]
     * @param path
     * @return
     * @throws Exception
     */
    public byte[] readFileToBytes(String path) throws Exception{
        if(!exist(path)){
            return null;
        }
        FileSystem fs = getFileSystem();
        FSDataInputStream inputStream = null;
        try {
            inputStream = fs.open(new Path(path));
            return IOUtils.readFullyToByteArray(inputStream);
        }finally {
            if(inputStream != null){
                inputStream.close();
            }
            fs.close();
        }
    }

    /**
     * 获取文件块在集群的位置
     * @param path
     * @return
     * @throws Exception
     */
    public BlockLocation[] getFileBlockLocations(String path)throws Exception{
        if(exist(path)){
            return null;
        }
        FileSystem fs = getFileSystem();
        FileStatus fileStatus = fs.getFileStatus(new Path(path));
        return fs.getFileBlockLocations(fileStatus, 0, fileStatus.getLen());
    }
    
}
