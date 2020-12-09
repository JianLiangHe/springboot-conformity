package edu.conformity.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.conformity.dao.UserRepository;
import edu.conformity.pojo.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	//@Test
	public void getById() {
		User user = userRepository.getById(11);
		System.out.println(user.toString());
	}
	
	//@Test
	public void getByParam() {
		User user = userRepository.getByParam(11);
		System.out.println(user.toString());
	}
	
	//@Test
	public void getByIdAndName() {
		User user = userRepository.getByIdAndName(11, "xx");
		System.out.println(user == null);
	}
	
	//@Test
	public void findByLikeAddress() {
		List<User> userList = userRepository.findByLikeAddress("广西省");
		System.out.println(userList.size());
	}
	
	//@Test
	public void findByLikeConcatAddress() {
		List<User> userList = userRepository.findByLikeConcatAddress("中国");
		System.out.println(userList.size());
	}
	
	// 原生sql查询
	@Test
	public void findBySql() {
		List<User> userList = userRepository.findBySql();
		System.out.println(userList.size());
	}
	
}
