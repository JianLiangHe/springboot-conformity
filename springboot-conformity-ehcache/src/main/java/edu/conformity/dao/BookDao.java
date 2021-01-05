package edu.conformity.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.conformity.entity.Book;

public interface BookDao extends JpaRepository<Book, String> {

}
