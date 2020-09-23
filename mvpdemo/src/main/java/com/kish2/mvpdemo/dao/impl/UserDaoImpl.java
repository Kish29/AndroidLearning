package com.kish2.mvpdemo.dao.impl;

import com.kish2.mvpdemo.bean.User;
import com.kish2.mvpdemo.dao.UserDao;
import com.kish2.mvpdemo.util.OkHttpUtils;

public class UserDaoImpl implements UserDao {

    @Override
    public User save(User user) {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findByUsername(String username) {
        User user = new User();
        String list = OkHttpUtils.getList();
        user.setUsername(list);
        user.setPassword(list);
        return user;
    }
}
