package com.ngp.wx.web.controller;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.ngp.core.action.BaseAction;
import com.ngp.core.dict.WxConstants;
import com.ngp.core.transport.HttpClientRequest;
import com.ngp.db.dao.service.LoginService;
import com.ngp.db.dao.vo.NgpWxAccessToken;

@Controller
@RequestMapping("/wxh")
public class HtmlController extends BaseAction{
	
	@Autowired
	private LoginService loggeroginService;
	
    @RequestMapping("/login")
    public String loginHtml(){
        return "login";
    }
    
    @RequestMapping("/index")
    public String welcomeHtml(){
        return "index";
    }
    
    /**
     * 微信鉴权获取wxid
     * @param request
     * @return
     * @throws Exception
     */
	@RequestMapping("/wxOauthOpenid")
    public String wxOauthOpenid(HttpServletRequest request) throws Exception {
		
		Gson gson = new Gson();
		
		Map reqMap = new TreeMap();
    	Map paramaMap = request.getParameterMap();
    	Set keySet = paramaMap.keySet();
    	for (Object object : keySet) {
    		//TODO 请求参数解析
    		reqMap.put(object, ((String[])paramaMap.get(object))[0]);
		}
    	
    	//TODO 日志代码
    	log.info(reqMap.toString());
    	
    	String code = (String) reqMap.get("code");
    	
		String url = WxConstants.WX_OAUTH_ACCESS_TOKEN + "appid=" + WxConstants.APPID + "&secret=" + WxConstants.APPSECRET + "&code=" + code + "&grant_type=authorization_code";
		
		//TODO 优化代码
		HttpClientRequest httpClientRequest = new HttpClientRequest();
		httpClientRequest.setUrl(url);
		String wxres = getHttpTransport().execute(httpClientRequest);
		
		NgpWxAccessToken ngpWxAccessToken = gson.fromJson(wxres, NgpWxAccessToken.class);
		log.info("============鉴权结果：" + gson.toJson(ngpWxAccessToken));
		
		loggeroginService.insertAccessToken(ngpWxAccessToken);
		
		//TODO 服务端发起httpclint请求
    	return  "test";
    }

	/**
	 * 组织微信鉴权插入数据
	 * @param wxresMap
	 * @return
	 */
//	private Map<String,String> getAccessTokenMap(HashMap wxresMap) {
//		Map<String,String> resMap = new HashMap<String,String>();
//		resMap.put("access_token",(String) wxresMap.get("access_token"));		//网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
//		resMap.put("expires_in",(String) wxresMap.get("expires_in"));		//access_token接口调用凭证超时时间，单位（秒）
//		resMap.put("refresh_token",(String) wxresMap.get("refresh_token"));	//用户刷新access_token
//		resMap.put("openid",(String) wxresMap.get("openid"));			//用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
//		resMap.put("scope",(String) wxresMap.get("scope"));			//用户授权的作用域，使用逗号（,）分隔
//		return resMap;
//	}
}
