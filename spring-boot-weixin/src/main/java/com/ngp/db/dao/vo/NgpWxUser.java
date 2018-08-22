package com.ngp.db.dao.vo;

import java.sql.Timestamp;

public class NgpWxUser {

	private int user_id;
	private String name;
	private String nick_name;
	private String tel_num;
	private String passwd;
	private String sex;
	private Timestamp sign_date;
	private Timestamp up_date;
	private String remark1;
	private String remark2;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getTel_num() {
		return tel_num;
	}
	public void setTel_num(String tel_num) {
		this.tel_num = tel_num;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Timestamp getSign_date() {
		return sign_date;
	}
	public void setSign_date(Timestamp sign_date) {
		this.sign_date = sign_date;
	}
	public Timestamp getUp_date() {
		return up_date;
	}
	public void setUp_date(Timestamp up_date) {
		this.up_date = up_date;
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
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		splitJoint(sb,"name",name);
		splitJoint(sb,"nick_name",nick_name);
		splitJoint(sb,"tel_num",tel_num);
		splitJoint(sb,"passwd",passwd);
		splitJoint(sb,"sex",sex);
		splitJoint(sb,"sign_date",sign_date);
		splitJoint(sb,"up_date",up_date);
		splitJoint(sb,"remark1",remark1);
		splitJoint(sb,"remark2",remark2);
		
		return sb.toString();
	}
	
	/**
	 * 拼接返回的string
	 * @param sb
	 * @param name
	 * @param value
	 */
	private void splitJoint(StringBuffer sb,String name,Object value) {
		if (value!=null) {
			sb.append(name);
			sb.append(": ");
			sb.append(value);
			sb.append("|");
		}
	}
}
