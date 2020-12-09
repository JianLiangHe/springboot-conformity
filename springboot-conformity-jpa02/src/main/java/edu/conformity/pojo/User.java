package edu.conformity.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class User implements Serializable {

	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	private String sex;
	
	private String address;
	
	private String phone;
	
	private String idcard;
	
}
