package edu.conformity.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.conformity.entity.User;

/**
 * User持久层
 * @author Administrator
 *
 */
public interface UserDao extends JpaRepository<User, Integer> {

}
