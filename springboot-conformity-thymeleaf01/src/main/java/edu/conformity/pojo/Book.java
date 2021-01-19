package edu.conformity.pojo;

import java.io.Serializable;

public class Book implements Serializable {

	private String id;
	
	private String name;
	
	private String info;

	public Book() {
		super();
	}

	public Book(String id, String name, String info) {
		super();
		this.id = id;
		this.name = name;
		this.info = info;
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

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
}
