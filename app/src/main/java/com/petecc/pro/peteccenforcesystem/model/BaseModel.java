package com.petecc.pro.peteccenforcesystem.model;

/**
 * Created by Administrator on 2017/4/19.
 */

public class BaseModel {

    private int errno;//是否登录成功

    private String errmsg;//消息

    public void setErrno(int errno){
        this.errno = errno;
    }

    public int getErrno(){
        return errno;
    }

    public void setErrmsg(String errmsg){
        this.errmsg = errmsg;
    }

    public String getErrmsg(){
        return errmsg==null?"":errmsg;
    }

}
