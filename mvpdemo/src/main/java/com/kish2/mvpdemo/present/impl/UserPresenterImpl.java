package com.kish2.mvpdemo.present.impl;

import android.util.Log;

import com.kish2.mvpdemo.bean.User;
import com.kish2.mvpdemo.model.UserModel;
import com.kish2.mvpdemo.model.impl.UserModelImpl;
import com.kish2.mvpdemo.present.BasePresenter;
import com.kish2.mvpdemo.present.UserPresenter;
import com.kish2.mvpdemo.view.BaseView;
import com.kish2.mvpdemo.view.UserView;

public class UserPresenterImpl implements UserPresenter {

    private UserModel userModel;
    private UserView userView;

    public UserPresenterImpl(UserView userView) {
        this.userView = userView;
        this.userModel = new UserModelImpl();
    }

    @Override
    public User save() {
        try {
            User user = new User();
            user.setUsername(userView.getUsername());
            user.setPassword(userView.getPassword());
            return userModel.save(user);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findUserByUsername(String user) {
        try {
            return userModel.findByUsername(user);
        } catch (InterruptedException e) {
            userView.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void detachView() {
        Log.d("detach", "detachView called....\n");
        this.userView = null;
    }
}
