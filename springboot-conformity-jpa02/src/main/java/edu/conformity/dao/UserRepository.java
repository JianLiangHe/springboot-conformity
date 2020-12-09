package edu.conformity.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import edu.conformity.pojo.User;

public interface UserRepository extends Repository<User, Integer> {

	@Query("SELECT user FROM User user WHERE user.id = ?1")
	User getById(Integer id);
	
	@Query("SELECT user FROM User user WHERE user.id = :id")
	User getByParam(@Param("id") Integer id);
	
	@Query("SELECT user FROM User user WHERE user.id = ?1 AND user.name = ?2")
	User getByIdAndName(Integer id, String name);
	
	@Query("SELECT user FROM User user WHERE user.address LIKE %?1%")
	List<User> findByLikeAddress(String address);
	
	@Query("SELECT user FROM User user WHERE user.address LIKE CONCAT('%', ?1, '%')")
	List<User> findByLikeConcatAddress(String address);
	
	@Query(value = "SELECT * FROM user LIMIT 0, 100", nativeQuery = true)
	List<User> findBySql();
	
}
