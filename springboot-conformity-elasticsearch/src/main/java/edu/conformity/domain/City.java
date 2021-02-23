package edu.conformity.domain;

import java.io.Serializable;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 城市实体类
 * @author hejianliang
 *
 */
@Document(indexName = "cityindex", type = "city")
public class City implements Serializable {

	private final long serialVersionUID = -1;
	
	private Long id;
	
	private Long provinceid;
	
	private String cityname;
	
	private String description;

	public City() {
		super();
	}

	public City(Long id, Long provinceid, String cityname, String description) {
		super();
		this.id = id;
		this.provinceid = provinceid;
		this.cityname = cityname;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(Long provinceid) {
		this.provinceid = provinceid;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}
	
}
