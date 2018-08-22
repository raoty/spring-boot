package com.ngp.wx.web.controller;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

@Controller
@RequestMapping("/wxh")
public class HtmlController {

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
	@RequestMapping("/wxTokenCheck")
    public String wxTokenCheck(HttpServletRequest request) throws Exception {
		Gson gson = new Gson();
    	
    	Map reqMap = new TreeMap();
    	Map paramaMap = request.getParameterMap();
    	Set keySet = paramaMap.keySet();
    	for (Object object : keySet) {
    		reqMap.put(object, gson.toJson(paramaMap.get(object)));
		}
    	System.out.println(reqMap);
    	
//    	WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
//    	String result2 = pc.decryptMsg(msgSignature, timestamp, nonce, fromXML);
    	
    	return "true";

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
		
		String appid = "wx74667b8251dbca03";
		String appsecret = "4622fdb9ba3d56a187456cb7c80ad3a1";
		Map reqMap = new TreeMap();
    	Map paramaMap = request.getParameterMap();
    	Set keySet = paramaMap.keySet();
    	for (Object object : keySet) {
    		reqMap.put(object, ((String[])paramaMap.get(object))[0]);
		}
    	
    	System.out.println(reqMap);
    	String code = (String) reqMap.get("code");
    	
		String res = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&secret=" + appsecret + "&code=" + code + "&grant_type=authorization_code";
		//TODO 服务端发起httpclint请求
    	return  "redirect:"+res;
    }
}
