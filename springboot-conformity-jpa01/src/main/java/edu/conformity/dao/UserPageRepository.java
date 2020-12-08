package edu.conformity.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import edu.conformity.pojo.User;

/**
 * 具有分页和排序功能，同时也继承了CrudRepository
 * @author hejianliang
 *
 */
public interface UserPageRepository extends PagingAndSortingRepository<User, Integer> {

}
