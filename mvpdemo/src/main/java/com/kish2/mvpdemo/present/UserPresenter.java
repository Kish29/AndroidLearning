package com.kish2.mvpdemo.present;

import com.kish2.mvpdemo.bean.User;

public interface UserPresenter extends BasePresenter {

    public User save();

    public User findUserByUsername(String user);
}
