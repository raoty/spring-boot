package com.ngp.wx.web.pkg.vo;

public class LoginRequest extends PubRequest{

    private String id;//登录id
    private String passWord;//登录密码
    private String msg;//验证码

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "id:"+id+",password:"+passWord+",msg:"+msg;
    }
}
