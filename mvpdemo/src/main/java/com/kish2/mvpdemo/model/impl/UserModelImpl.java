package com.kish2.mvpdemo.model.impl;

import com.kish2.mvpdemo.bean.User;
import com.kish2.mvpdemo.dao.UserDao;
import com.kish2.mvpdemo.dao.impl.UserDaoImpl;
import com.kish2.mvpdemo.model.UserModel;

public class UserModelImpl implements UserModel {

    private UserDao userDao;

    public UserModelImpl() {
        this.userDao = new UserDaoImpl();
    }

    @Override
    public User save(User user) throws InterruptedException {
        return userDao.save(user);
    }

    @Override
    public User findByUsername(String username) throws InterruptedException{
        return userDao.findByUsername(username);
    }
}
