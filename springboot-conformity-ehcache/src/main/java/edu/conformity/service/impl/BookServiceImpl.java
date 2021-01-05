package edu.conformity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import edu.conformity.dao.BookDao;
import edu.conformity.entity.Book;
import edu.conformity.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;
	
	private final String BOOKLIST_CACHE_NAME = "bookList";
	
	@Override
	public List<Book> findAll() {
		List<Book> bookList = bookDao.findAll();
		return bookList;
	}

	@Cacheable(value = BOOKLIST_CACHE_NAME, key = "'bookList' + #id")
	@Override
	public Book get(String id) {
		Book book = bookDao.getOne(id);
		return book;
	}

	@Override
	public String save(Book book) {
		try {
			bookDao.save(book);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	@CacheEvict(value = BOOKLIST_CACHE_NAME, key = "'bookList' + #book.id")
	@Override
	public String update(Book book) {
		try {
			bookDao.saveAndFlush(book);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	@CacheEvict(value = BOOKLIST_CACHE_NAME, key = "'bookList_' + #id")
	@Override
	public String delete(String id) {
		try {
			bookDao.deleteById(id);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

}
