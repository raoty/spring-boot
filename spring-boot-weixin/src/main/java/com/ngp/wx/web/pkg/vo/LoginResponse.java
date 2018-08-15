package com.ngp.wx.web.pkg.vo;

public class LoginResponse extends PubResponse{
    private String name;//昵称



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "name:"+name;
    }
}
