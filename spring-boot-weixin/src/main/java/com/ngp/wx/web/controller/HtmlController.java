package com.ngp.wx.web.controller;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.ngp.core.action.BaseAction;
import com.ngp.core.dict.WxConstants;
import com.ngp.core.transport.HttpClientRequest;

@Controller
@RequestMapping("/wxh")
public class HtmlController extends BaseAction{
	
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
    	log.info(reqMap);
    	
    	String code = (String) reqMap.get("code");
    	
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WxConstants.APPID + "&secret=" + WxConstants.APPSECRET + "&code=" + code + "&grant_type=authorization_code";
		
		//TODO 优化代码
		HttpClientRequest httpClientRequest = new HttpClientRequest();
		httpClientRequest.setUrl(url);
		String execute = getHttpTransport().execute(httpClientRequest);
		
		log.info("============鉴权结果：" + execute);
		
		//TODO 服务端发起httpclint请求
    	return  "test";
    }
}
