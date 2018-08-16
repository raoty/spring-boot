package com.ngp.wx.web.pkg.vo;

import java.io.Serializable;

public class PubResponse implements Serializable {

    private String resCode;//返回码
    private String resMsg;//返回码信息
    private String globNo;//流水号
    private String time;//交易时间

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public String getGlobNo() {
        return globNo;
    }

    public void setGlobNo(String globNo) {
        this.globNo = globNo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
