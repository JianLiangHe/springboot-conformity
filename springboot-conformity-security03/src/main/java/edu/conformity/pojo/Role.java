package edu.conformity.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Role implements Serializable {

	private int id;
	
	private String name;
	
	private String remark;
	
}
