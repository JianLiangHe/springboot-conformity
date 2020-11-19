package edu.conformity.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	private String password;
	
	private String sex;
	
	private String address;
	
}
