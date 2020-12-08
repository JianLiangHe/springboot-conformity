package edu.conformity.dao;

import org.springframework.data.repository.CrudRepository;

import edu.conformity.pojo.User;

/**
 * 具有增删改查的一些基本功能
 * @author hejianliang
 *
 */
public interface UserCrudRepository extends CrudRepository<User, Integer> {

}
