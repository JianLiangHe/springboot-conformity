package edu.conformity.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.conformity.entity.Book;
import edu.conformity.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private SolrClient solrClient;
	
	@Override
	public void save(Book book) {
		SolrInputDocument document = new SolrInputDocument();
		document.setField("id", book.getId());
		document.setField("name", book.getName());
		document.setField("description", book.getDescription());
		
		try {
			solrClient.add(document);
			solrClient.commit();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String query) {
		try {
			solrClient.deleteByQuery(query);
			solrClient.commit();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(Book book) {
		try {
			solrClient.addBean(book);
			solrClient.commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Book> queryAll() {
		List<Book> bookList = new ArrayList<Book>();
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*"); // 设置查询所有
		try {
			QueryResponse queryResponse = solrClient.query(solrQuery);
			if (queryResponse != null) {
				bookList = queryResponse.getBeans(Book.class);
			}
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return bookList;
		}
	}

}
