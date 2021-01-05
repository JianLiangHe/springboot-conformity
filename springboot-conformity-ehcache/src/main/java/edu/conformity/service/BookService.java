package edu.conformity.service;

import java.util.List;

import edu.conformity.entity.Book;

public interface BookService {

	List<Book> findAll();
	
	Book get(String id);
	
	String save(Book book);
	
	String update(Book book);
	
	String delete(String id);
	
}
