package com.kish2.mvpdemo.dao;

import com.kish2.mvpdemo.bean.User;

public interface UserDao {

    public User save(User user);

    public User findByUsername(String username);

}
