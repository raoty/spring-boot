package com.ngp.wx.web.pkg.vo;

import java.io.Serializable;

public class PubRequest implements Serializable {
    private String netWork;//网络类型
    private String ipAddr;//ip地址
    private String time;//交易时间
    private String globNo;//流水号

    public String getNetWork() {
        return netWork;
    }

    public void setNetWork(String netWork) {
        this.netWork = netWork;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGlobNo() {
        return globNo;
    }

    public void setGlobNo(String globNo) {
        this.globNo = globNo;
    }

}
