package com.ssafy.enjoytrip.dao;

import com.ssafy.enjoytrip.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDAO {

    public void insert(User user);

    public User findByEmailAndPassword(String email, String password);

    public User findByUserCode(int userCode);

    public User findByEmail(String email);

    public User findByEmailAndName(String email, String name);

    public void update(User user);

    public void deleteByUserCode(int userCode);

}
