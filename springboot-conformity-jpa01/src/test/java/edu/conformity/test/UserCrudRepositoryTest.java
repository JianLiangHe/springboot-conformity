package edu.conformity.test;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.conformity.dao.UserCrudRepository;
import edu.conformity.pojo.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserCrudRepositoryTest {

	@Autowired
	private UserCrudRepository userCrudRepository;
	
	/**
	 * 新增
	 */
	//@Test
	public void add() {
		User user = new User();
		user.setName("Jacky");
		user.setSex("男");
		user.setAddress("中国香港");
		User result = userCrudRepository.save(user);
		System.out.println(result.toString());
	}
	
	/**
	 * 修改
	 */
	//@Test
	public void edit() {
		User user = new User();
		user.setId(3817);
		user.setName("Jacky");
		user.setSex("男");
		user.setAddress("中国广东省广州市");
		User result = userCrudRepository.save(user);
		System.out.println(result.toString());
	}
	
	/**
	 * 根据id查询
	 */
	//@Test
	public void findById() {
		Optional<User> userOptional = userCrudRepository.findById(3817);
		User result = userOptional.get();
		System.out.println(result);
	}
	
	/**
	 * 查询所有
	 */
	//@Test
	public void findAll() {
		List<User> userList = (List<User>) userCrudRepository.findAll();
		System.out.println("size: " + userList.size());
	}
	
	/**
	 * 计算总数
	 */
	@Test
	public void count() {
		long count = userCrudRepository.count();
		System.out.println("count: " + count);
	}
	
}
