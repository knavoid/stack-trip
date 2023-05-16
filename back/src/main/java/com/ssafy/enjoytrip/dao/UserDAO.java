package com.ssafy.enjoytrip.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ssafy.enjoytrip.dto.User;

@Mapper
@Repository
public interface UserDAO {

	public void insert(User user);

	public User findByEmailAndPassword(String email, String password);

	public User findByUserCode(int userCode);

	public void update(User user);

	public void deleteByUserCode(int userCode);

}
