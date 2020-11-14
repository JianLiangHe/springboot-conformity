package edu.conformity.config;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.fs.FileSystem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "hadoop.name-node")
public class HadoopConfig {

	@Value("${hadoop.name-node}")
	private String nameNode;
	
	@Bean("fileSystem")
	public FileSystem createFs() throws URISyntaxException, IOException, InterruptedException {
		org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();
		conf.set("fs.defalutFS", nameNode);
		conf.set("dfs.replication", "3");
		URI uri = new URI(nameNode.trim());
		FileSystem fs = FileSystem.get(uri, conf, "root");
		System.out.println("fs.defaultFS: "+conf.get("fs.defaultFS"));
		
		return fs;
	}
	
}
