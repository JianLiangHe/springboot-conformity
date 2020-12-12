package edu.conformity.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRole implements Serializable {

	private int id;
	
	private int userId;
	
	private int roleId;
	
}
