package edu.conformity.log.service;

import java.util.List;

import edu.conformity.log.entity.Log;

public interface LogService {

	/**
	 * 添加日志
	 * @param log
	 * @return
	 */
	int insertLog(Log log);
	
	/**
	 * 通过用户id查询日志记录
	 * @param userId
	 * @return
	 */
	List<Log> getLogsByUserId(Long userId);
	
}
