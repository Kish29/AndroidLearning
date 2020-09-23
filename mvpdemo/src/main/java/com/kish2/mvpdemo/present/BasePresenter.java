package com.kish2.mvpdemo.present;

import com.kish2.mvpdemo.view.BaseView;

public interface BasePresenter {

    // 该方法在每个Activity退出时必须调用
    public void detachView();
}
