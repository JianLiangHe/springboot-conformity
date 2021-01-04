package edu.conformity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import edu.conformity.entity.Book;
import edu.conformity.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public String save(Book book) {
		mongoTemplate.save(book);
		return "success";
	}

	@Override
	public List<Book> findAll() {
		List<Book> list = mongoTemplate.findAll(Book.class);
		return list;
	}

	@Override
	public Book get(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		return mongoTemplate.findOne(query, Book.class);
	}

}
