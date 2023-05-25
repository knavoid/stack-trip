package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.dao.UserDAO;
import com.ssafy.enjoytrip.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public void signUp(User user) throws Exception {
        userDAO.insert(user);
    }

    @Override
    public User login(String email, String password) throws Exception {
        return userDAO.findByEmailAndPassword(email, password);
    }

    @Override
    public User getUserInfo(int userCode) throws Exception {
        return userDAO.findByUserCode(userCode);
    }

    @Override
    public boolean isEmailExist(String email) throws Exception {
        User user = userDAO.findByEmail(email);
        return user != null;
    }

    @Override
    public User findPassword(String email, String name) throws Exception {
        return userDAO.findByEmailAndName(email, name);
    }

    @Override
    public void modifyUserInfo(User user) throws Exception {
        userDAO.update(user);
    }

    @Override
    public void deleteAccount(int userCode) throws Exception {
        userDAO.deleteByUserCode(userCode);
    }

}
