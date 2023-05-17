package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.dto.User;

public interface UserService {

    void signUp(User user) throws Exception;

    User login(String email, String password) throws Exception;

    User getUserInfo(int userCode) throws Exception;

    void modifyUserInfo(User user) throws Exception;

    void deleteAccount(int userCode) throws Exception;

}