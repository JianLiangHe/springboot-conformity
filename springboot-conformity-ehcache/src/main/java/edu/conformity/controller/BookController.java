package edu.conformity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.conformity.entity.Book;
import edu.conformity.service.BookService;

@RestController
@RequestMapping("/book/")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = "findAll", method = RequestMethod.GET)
	public List<Book> findAll() {
		return bookService.findAll();
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(
			@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "info", required = true) String info
			) {
		String id = String.valueOf(System.currentTimeMillis());
		Book book = new Book(id, title, info);
		return bookService.save(book);
	}
	
	@RequestMapping(value = "get", method = RequestMethod.GET)
	public Book get(
			@RequestParam(value = "id", required = true) String id) {
		return bookService.get(id);
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String delete(
			@RequestParam(value = "id", required = true) String id) {
		return bookService.delete(id);
	}
	
}
