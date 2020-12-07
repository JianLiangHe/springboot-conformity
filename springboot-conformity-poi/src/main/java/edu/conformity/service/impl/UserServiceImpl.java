package edu.conformity.service.impl;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.conformity.mapper.UserMapper;
import edu.conformity.pojo.User;
import edu.conformity.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<User> findAll() {
		return userMapper.findAll();
	}

	@Override
	public void save(List<User> userList) {
		Iterator<User> it = userList.iterator();
		
		while (it.hasNext()) {
			User user = it.next();
			try {
				userMapper.save(user);
			} catch (Exception e) {
				LOG.error("新增用户: " + user.toString() + ", 失败: " + e.getMessage());
				e.printStackTrace();
			} finally {
				continue;
			}
		}
	}

}
