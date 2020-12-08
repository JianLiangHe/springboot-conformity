package edu.conformity.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import edu.conformity.dao.UserPageRepository;
import edu.conformity.pojo.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserPageRepositoryTest {

	@Autowired
	private UserPageRepository userPageRepository;
	
	/**
	 * 排序查询
	 */
	//@Test
	public void findAllBySort() {
		List<User> userList = (List<User>) userPageRepository.findAll(new Sort(Sort.Direction.DESC, "id"));
		System.out.println("size: " + userList.size());
	}
	
	/**
	 * 分页查询
	 */
	@Test
	public void findAllByPageable() {
		Page<User> userPage = userPageRepository.findAll(new PageRequest(0, 20));
		int number = userPage.getNumber();
		List<User> userList = userPage.getContent();
		int totalPages = userPage.getTotalPages();
		
		System.out.println("当前页数：" + number);
		System.out.println("分页的数据总量：" + userList.size());
		System.out.println("总共页数：" + totalPages);
	}
	
}
