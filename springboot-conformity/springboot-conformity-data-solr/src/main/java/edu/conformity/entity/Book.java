package edu.conformity.entity;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(solrCoreName = "book_core")
public class Book implements Serializable {

	/**
	 * id
	 */
	@Id
	@Field
	private String id;
	
	/**
	 * 名称
	 */
	@Field
	private String name;
	
	/**
	 * 描述
	 */
	@Field
	private String description;

	public Book() {
		super();
	}

	public Book(String id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
