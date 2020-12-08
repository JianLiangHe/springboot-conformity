package edu.conformity.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.conformity.pojo.User;

public interface UserJpaRepository extends JpaRepository<User, Integer> {

}
