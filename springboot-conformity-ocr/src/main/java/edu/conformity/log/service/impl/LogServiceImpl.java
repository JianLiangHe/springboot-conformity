package edu.conformity.log.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.conformity.log.entity.Log;
import edu.conformity.log.service.LogService;

@Service
public class LogServiceImpl implements LogService {

	@Override
	public int insertLog(Log log) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Log> getLogsByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
