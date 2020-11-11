package edu.conformity.service;

import java.util.List;

import edu.conformity.entity.Book;

/**
 * Book业务层
 * @author hejianliang
 *
 */
public interface BookService {

	/**
	 * 新增
	 * @param book
	 */
	void save(Book book);
	
	/**
	 * 删除
	 * @param query
	 */
	void delete(String query);
	
	/**
	 * 更新
	 * @param book
	 */
	void update(Book book);
	
	/**
	 * 查询所有数据
	 * @return
	 */
	List<Book> queryAll();
	
	/**
	 * 带参查询
	 * @param query
	 * @return
	 */
	List<Book> queryByParams(String query);
	
}
