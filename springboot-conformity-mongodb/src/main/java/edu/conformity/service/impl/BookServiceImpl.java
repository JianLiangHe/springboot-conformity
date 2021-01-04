package edu.conformity.service.impl;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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

	@Override
	public Book getByName(String name) {
		Query query = new Query(Criteria.where("name").is(name));
		return mongoTemplate.findOne(query, Book.class);
	}

	@Override
	public String updateBook(Book book) {
		Query query = new Query(Criteria.where("_id").is(book.getId()));
		Update update = new Update().set("name", book.getName()).set("info", book.getInfo());
		// updateFirst 更新查询返回结果集的第一条
		mongoTemplate.updateFirst(query, update, Book.class);
		// updateMulti 更新返回结果集的全部
		//mongoTemplate.updateMulti(query, update, Book.class);
		// upsert 更新对象，不存在则去添加
		//mongoTemplate.upsert(query, update, Book.class);
		
		return "success";
	}

	@Override
	public String deleteById(String id) {
		Book book = this.get(id);
		mongoTemplate.remove(book);
		return "success";
	}

	@Override
	public String deleteByBook(Book book) {
		mongoTemplate.remove(book);
		return "success";
	}

	@Override
	public List<Book> findByLikes(String search) {
		Query query = new Query();
		Criteria criteria = new Criteria();
		Pattern pattern = Pattern.compile("^.*$" + search + ".*$", Pattern.CASE_INSENSITIVE);
		criteria.where("name").regex(pattern);
		query.addCriteria(criteria);
		List<Book> bookList = mongoTemplate.find(query, Book.class);
		return bookList;
	}

}
