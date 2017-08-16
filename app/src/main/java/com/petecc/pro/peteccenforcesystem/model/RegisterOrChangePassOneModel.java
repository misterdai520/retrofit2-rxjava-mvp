package com.petecc.pro.peteccenforcesystem.model;

/**
 * Created by daiyf on 2017/4/25.
 * 注册或者修改密码第一步返回的数据
 */

public class RegisterOrChangePassOneModel extends BaseModel{

    private String data;

    public String getData(){
        return data==null?"":data;
    }
    public void setData(String data){
        this.data = data;
    }
}
