package edu.conformity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.conformity.entity.Book;
import edu.conformity.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Book Controller")
@RestController
@RequestMapping("/book/")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@ApiOperation("添加Book")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(
			@RequestParam(name = "name", required = true) String name,
			@RequestParam(name = "description", required = true) String description
			) {
		String id = String.valueOf(System.currentTimeMillis());
		Book book = new Book(id, name, description);
		
		bookService.save(book);
		return "success";
	}
	
	@ApiOperation("删除")
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public void delete(
			@RequestParam(name = "query", required = true) String query
			) {
		bookService.delete(query);
	}
	
	@ApiOperation("更新")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public void update(
			@RequestParam(name = "id", required = true) String id,
			@RequestParam(name = "name", required = true) String name,
			@RequestParam(name = "description", required = true) String description
			) {
		Book book = new Book(id, name, description);
		bookService.update(book);
	}
	
	@ApiOperation("查询所有Book数据")
	@RequestMapping(value = "queryAll", method = RequestMethod.GET)
	public List<Book> queryAll() {
		return bookService.queryAll();
	}
	
	@ApiOperation("带参查询")
	@RequestMapping(value = "queryByParams", method = RequestMethod.GET)
	public List<Book> queryByParams(
			@RequestParam(name = "query", required = true) String query
			) {
		return bookService.queryByParams(query);
	}
	
}
