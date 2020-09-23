package com.kish2.mvpdemo.model;

import com.kish2.mvpdemo.bean.User;

public interface UserModel {

    public User save(User user) throws InterruptedException;

    public User findByUsername(String username) throws InterruptedException;

}
