package edu.conformity.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "book")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {

	private String id;
	
	private String name;
	
	private String info;
	
}
