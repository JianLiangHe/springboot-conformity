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
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "info", required = true) String info
			) {
		Book book = new Book();
		String id = String.valueOf(System.currentTimeMillis());
		book.setId(id);
		book.setName(name);
		book.setInfo(info);
		return bookService.save(book);
	}
	
	@RequestMapping(value = "findAll", method = RequestMethod.GET)
	public List<Book> findAll() {
		return bookService.findAll();
	}
	
	@RequestMapping(value = "get", method = RequestMethod.GET)
	public Book get(
			@RequestParam(value = "id", required = true) String id) {
		return bookService.get(id);
	}
	
	@RequestMapping(value = "getByName", method = RequestMethod.GET)
	public Book getByName(
			@RequestParam(value = "name", required = true) String name) {
		return bookService.getByName(name);
	}
	
	@RequestMapping(value = "updateBook", method = RequestMethod.POST)
	public String updateBook(
			@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "info", required = true) String info
			) {
		Book book = new Book(id, name, info);
		return bookService.updateBook(book);
	}
	
	@RequestMapping(value = "deleteById", method = RequestMethod.POST)
	public String deleteById(
			@RequestParam(value = "id", required = true) String id) {
		return bookService.deleteById(id);
	}
	
	@RequestMapping(value = "deleteByBook", method = RequestMethod.POST)
	public String deleteByBook(
			@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "info", required = true) String info) {
		Book book = new Book(id, name, info);
		return bookService.deleteByBook(book);
	}
	
}
