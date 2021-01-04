package edu.conformity.service;

import java.util.List;

import edu.conformity.entity.Book;

public interface BookService {

	String save(Book book);
	
	List<Book> findAll();
	
	Book get(String id);
	
}
