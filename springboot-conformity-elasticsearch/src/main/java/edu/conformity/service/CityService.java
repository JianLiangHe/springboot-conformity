package edu.conformity.service;

import java.util.List;

import edu.conformity.domain.City;

public interface CityService {

	/**
	 * 新增城市信息
	 * @param city
	 * @return
	 */
	Long saveCity(City city);

	/**
	 * 根据关键词，function score query权重分页查询
	 * @param pageNumber
	 * @param pageSize
	 * @param searchContent
	 * @return
	 */
	List<City> searchCity(Integer pageNumber, Integer pageSize, String searchContent);
	
	/**
	 * 查询所有数据
	 * @return
	 */
	List<City> searchAll();
	
}
