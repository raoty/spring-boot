package com.ngp.wx.web.controller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.ngp.core.dict.WxConstants;
import com.ngp.core.util.BeanUtils;
import com.ngp.wx.web.action.LoginAction;
import com.ngp.wx.web.pkg.vo.LoginRequest;
import com.ngp.wx.web.pkg.vo.LoginResponse;

/**
 * json 接口数据请求
 */
@RestController
@RequestMapping("/wx")

@SuppressWarnings({ "rawtypes", "unchecked" })		
public class MainController {

    Log log = LogFactory.getLog(MainController.class);

    /**
     * 登录功能
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/login")
    public String login(@RequestBody LoginRequest request) throws Exception {
    	//TODO 打印交易时间
        log.info("trans begin: Login");
        long begin = System.currentTimeMillis();
        Map res = loginAction.execute(BeanUtils.bean2Map(request));
        long end = System.currentTimeMillis();
        log.info("trans end: Login,on time: " + (end - begin));
        return new Gson().toJson(res);
    }
    
    /**
     * 微信鉴权获取wxid
     * 	重定向
     * @param request
     * @throws Exception
     */
    @RequestMapping("/wxOauthUrl")
    public void getWxOauthUrl(HttpServletResponse response) throws Exception {

    	String redirect_uri = URLEncoder.encode(WxConstants.MYAPP_URL + "/wxh/wxOauthOpenid", "UTF-8");
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append(WxConstants.WXDOMAIN);
    	sb.append("&appid=");
    	sb.append(WxConstants.APPID);
    	sb.append("&redirect_uri=");
    	sb.append(redirect_uri);
    	sb.append("#wechat_redirect");
    	
    	response.sendRedirect(sb.toString());
    }
    
    

    
    //TODO 测试代码，后续删除
    @RequestMapping(value = "/login1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public LoginResponse login1(@RequestBody LoginRequest request) throws Exception {
        log.info("trans begin: Login");
        long begin = System.currentTimeMillis();
        log.info("request: " + request.toString());
        Map res = new HashMap();
        res.put("Name", "1234");
        LoginResponse response = BeanUtils.map2Bean(res, LoginResponse.class);
        long end = System.currentTimeMillis();
        log.info("res: "+response);
        log.info("trans end: Login,on time: " + (end - begin));
        return response;
    }
    
	
    /**
     * action注册区
     */
    @Autowired
    LoginAction loginAction;

}
