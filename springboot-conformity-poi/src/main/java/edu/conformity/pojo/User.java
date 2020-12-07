package edu.conformity.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User implements Serializable {

	private int id;
	
	private String name;
	
	private String sex;
	
	private String address;
	
	private String phone;
	
	private String idcard;
	
}
