package com.ngp.core.dict;


public class WxConstants {
	
	public static final String WXDOMAIN = "https://open.weixin.qq.com/connect/oauth2/authorize?response_type=code&scope=snsapi_base";	//微信鉴权domain
	public static final String APPID = "wx74667b8251dbca03";			//微信appid
	public static final String APPSECRET = "4622fdb9ba3d56a187456cb7c80ad3a1";
	public static final String MYAPP_URL = "http://5f410506.ngrok.io";		//服务器的域名
	public static final String WX_OAUTH_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?";	//微信鉴权，获取access_token
	public static final String WX_REFRESH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token?";		//微信刷新access_token连接
}
