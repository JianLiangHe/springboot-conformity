package edu.conformity.log.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.conformity.log.entity.Log;

@Mapper
public interface LogMapper {

	/**
	 * 保存日志记录
	 * @param log
	 * @return
	 */
	int insertLog(Log log);
	
	/**
	 * 根据userid获取日志列表
	 * @param userId
	 * @return
	 */
	List<Log> getLogsByUserId(Long userId);
	
}
