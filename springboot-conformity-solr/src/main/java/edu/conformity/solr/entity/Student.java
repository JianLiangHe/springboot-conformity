package edu.conformity.solr.entity;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(solrCoreName = "t_student")
public class Student {

	@Id
	@Field
	private String id;
	
	@Field
	private String name;
	
	@Field
	private String nickName;
	
	@Field
	private String info;
	
	@Field
	private String sex;
	
	@Field
	private String hobby;
	
	@Field
	private String major;

	public Student() {
		super();
	}

	public Student(String id, String name, String nickName, String info, String sex, String hobby, String major) {
		super();
		this.id = id;
		this.name = name;
		this.nickName = nickName;
		this.info = info;
		this.sex = sex;
		this.hobby = hobby;
		this.major = major;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
}
