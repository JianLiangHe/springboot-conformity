package edu.conformity.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import edu.conformity.domain.City;

@Repository
public interface CityRepository extends ElasticsearchRepository<City, Long> {

}
