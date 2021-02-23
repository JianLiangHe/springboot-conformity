package edu.conformity.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import edu.conformity.domain.City;
import edu.conformity.repository.CityRepository;
import edu.conformity.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);
	
	@Autowired
	private CityRepository cityRepository;
	
	@Override
	public Long saveCity(City city) {
		City cityResult = cityRepository.save(city);
		
		return cityResult.getId();
	}

	@Override
	public List<City> searchCity(Integer pageNumber, Integer pageSize, String searchContent) {
		return null;
	}

	@Override
	public List<City> searchAll() {
		List<City> cityList = new ArrayList<City>();
		Iterable<City> iterable = cityRepository.findAll();
		Iterator<City> iterator =  iterable.iterator();
		
		while (iterator.hasNext()) {
			cityList.add(iterator.next());
		}
		
		return cityList;
	}

}
