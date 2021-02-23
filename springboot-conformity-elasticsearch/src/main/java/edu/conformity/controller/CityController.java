package edu.conformity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.conformity.domain.City;
import edu.conformity.service.CityService;

@RestController
@RequestMapping("/city/")
public class CityController {

	@Autowired
	private CityService cityService;
	
	@RequestMapping(value = "searchAll", method = RequestMethod.GET)
	public List<City> searchAll() {
		List<City> list = cityService.searchAll();
		
		return list;
	}
	
}
