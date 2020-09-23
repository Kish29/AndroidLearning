package com.kish2.mvpdemo.view;

public interface UserView{

    public void setUsername(String username);

    public void setPassword(String password);

    public void error(String errorMsg);

    public String getUsername();

    public String getPassword();

}
