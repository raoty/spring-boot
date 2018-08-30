package com.ngp.db.dao.vo;

import java.sql.Timestamp;

public class NgpWxAccessToken {
	private String openid;
	private String userid;
	private String access_token;
	private String refresh_token;
	private Timestamp inser_date;
	private Timestamp access_up_date;
	private String scope;
	private String remark1;
	private String remark2;
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public Timestamp getInser_date() {
		return inser_date;
	}
	public void setInser_date(Timestamp inser_date) {
		this.inser_date = inser_date;
	}
	public Timestamp getAccess_up_date() {
		return access_up_date;
	}
	public void setAccess_up_date(Timestamp access_up_date) {
		this.access_up_date = access_up_date;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getRemark1() {
		return remark1;
	}
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	public String getRemark2() {
		return remark2;
	}
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	
}