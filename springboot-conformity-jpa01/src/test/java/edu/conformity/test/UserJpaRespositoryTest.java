package edu.conformity.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import edu.conformity.dao.UserJpaRepository;
import edu.conformity.pojo.User;
import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserJpaRespositoryTest {

	@Autowired
	private UserJpaRepository userJpaRepository;
	
	/**
	 * 批量插入数据
	 * 执行总时长：527799
	 */
	//@Test
	public void batchSave() {
		long startTime = System.currentTimeMillis();
		List<User> userList = new ArrayList<User>();
		
		for (int i = 0; i < 2000; i++) {
			User user = new User();
            user.setName("user_" + i);
            user.setAddress("中国湖南省长沙市");
            user.setIdcard(String.valueOf(i));
            user.setPhone(String.valueOf(i));
            user.setSex("男");
            userList.add(user);
            if (i % 100 == 0) {
                userJpaRepository.saveAll(userList);
                userList.clear();
            }
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("执行总时长：" + (endTime - startTime));
	}
	
	/**
	 * 批量执行
	 * 执行总时长：444439
	 */
	//@Test
	public void batchSave2() {
		long startTime = System.currentTimeMillis();
		List<User> userList = new ArrayList<User>();
		
		for (int i = 0; i < 2000; i++) {
			User user = new User();
            user.setName("user_" + i);
            user.setAddress("中国广西省南宁市");
            user.setIdcard(String.valueOf(i));
            user.setPhone(String.valueOf(i));
            user.setSex("女");
            userList.add(user);
            if (i % 100 == 0) {
                userJpaRepository.saveAll(userList);
                userJpaRepository.flush();
                userList.clear();
            }
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("执行总时长：" + (endTime - startTime));
	}
	
	/**
	 * 查询所有数据
	 */
	//@Test
	public void findAll() {
		List<User> userList = userJpaRepository.findAll();
		System.out.println("userList.size: " + userList.size());
	}
	
	/**
	 * 根据id排序查询
	 */
	//@Test
	public void findAllSortId() {
		List<User> userList = userJpaRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		Iterator<User> it = userList.iterator();
		
		while (it.hasNext()) {
			User user = it.next();
			System.out.println(user.toString());
		}
	}
	
	/**
	 * 分页查询
	 */
	//@Test
	public void findByPage() {
		PageRequest pageRequest = new PageRequest(0, 10);
		Page<User> userPage = userJpaRepository.findAll(pageRequest);
		List<User> userList = userPage.getContent();
		
		Iterator<User> it = userList.iterator();
		
		while (it.hasNext()) {
			User user = it.next();
			System.out.println(user.toString());
		}
	}
	
	/**
	 * 分页排序查询
	 */
	//@Test
	public void findByPageAndSort() {
		PageRequest pageRequest = new PageRequest(0, 10, Sort.by(Sort.Direction.DESC, "id"));
		Page<User> userPage = userJpaRepository.findAll(pageRequest);
		List<User> userList = userPage.getContent();
		
		Iterator<User> it = userList.iterator();
		
		while (it.hasNext()) {
			User user = it.next();
			System.out.println(user.toString());
		}
	}
	
	/**
	 * 根据id集合查询
	 */
	//@Test
	public void findByIds() {
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add(2);
		ids.add(5);
		
		List<User> userList = userJpaRepository.findAllById(ids);
		
		Iterator<User> it = userList.iterator();
		
		while (it.hasNext()) {
			User user = it.next();
			System.out.println(user.toString());
		}
	}
	
	/**
	 * 保存数据并刷新缓存
	 */
	//@Test
	public void saveAndFlush() {
		User user = new User();
        user.setName("lucy");
        user.setAddress("中国广西省玉林市");
        user.setSex("女");
        User result = userJpaRepository.saveAndFlush(user);
	}
	
	/**
	 * 根据id查询
	 */
	//@Test
	public void getById() {
		User user = userJpaRepository.findById(10).get();
		System.out.println(user.toString());
	}
	
	/**
	 * 示例匹配器
	 */
	//@Test
	public void findUserByExample() {
		User user = new User();
		user.setAddress("中国湖南省长沙市");
		
		List<User> userList = userJpaRepository.findAll(Example.of(user));
		System.out.println("size: " + userList.size());
	}
	
	/**
	 * 模糊查询
	 * @return
	 */
	@Test
	public void findUserByExampleQuery() {
		User user = new User();
        user.setName("20");
        user.setAddress("中国湖南省长沙市");
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith())//模糊查询匹配开头，即{username}%
                .withMatcher("address", ExampleMatcher.GenericPropertyMatchers.contains())//全部模糊查询，即%{address}%
                .withIgnorePaths("id");//忽略字段，即不管id是什么值都不加入查询条件
        Example<User> example = Example.of(user, matcher);
        List<User> userList = userJpaRepository.findAll(example);
        System.out.println(userList);
	}
	
	/**
	 * 批量删除
	 */
	//@Test
	public void deleteInBatch() {
		PageRequest pageRequest = new PageRequest(0, 10, Sort.by(Sort.Direction.DESC, "id"));
		Page<User> userPage = userJpaRepository.findAll(pageRequest);
		List<User> userList = userPage.getContent();
		userJpaRepository.deleteInBatch(userList);
	}
	
	/**
	 * 批量删除所有数据
	 */
	//@Test
	public void deleteAllInBatch() {
		userJpaRepository.deleteAllInBatch();
	}
	
}
