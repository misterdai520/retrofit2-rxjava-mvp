package com.petecc.pro.peteccenforcesystem.model;

/**
 * Created by daiyf on 2017/4/18.
 * 登录返回的数据
 */

public class LoginResult extends BaseModel{

    private LoginData data;

    public void setLoginData(LoginData data){
        this.data = data;
    }

    public LoginData getLoginData(){
        return data;
    }
}
