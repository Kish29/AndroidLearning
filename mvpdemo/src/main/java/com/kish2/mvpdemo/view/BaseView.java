package com.kish2.mvpdemo.view;

import com.kish2.mvpdemo.present.BasePresenter;

public interface BaseView<P extends BasePresenter> {

    public void initPresenter(P presenter);

    public void initData();
}
