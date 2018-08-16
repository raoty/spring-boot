package com.ngp.wx.web.controller;

import com.ngp.core.util.BeanUtils;
import com.ngp.wx.web.action.LoginAction;
import com.ngp.wx.web.pkg.vo.LoginRequest;
import com.ngp.wx.web.pkg.vo.LoginResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * json 接口数据请求
 */
@RestController
@RequestMapping("/wx")
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
    public LoginResponse login(@RequestBody LoginRequest request) throws Exception {
        log.info("trans begin: Login");
        long begin = System.currentTimeMillis();
        request.toString();
        Map res = loginAction.execute(BeanUtils.bean2Map(request));
        LoginResponse response = BeanUtils.map2Bean(res, LoginResponse.class);
        long end = System.currentTimeMillis();

        log.info("trans end: Login,on time: " + (end - begin));
        return response;
    }

    @RequestMapping(value = "/login1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public LoginResponse login1(@RequestBody LoginRequest request) throws Exception {
        log.info("trans begin: Login");
        long begin = System.currentTimeMillis();
        log.info("request: " + request.toString());
        Map res = new HashMap();
        res.put("Name", "1234");
        LoginResponse response = BeanUtils.map2Bean(res, LoginResponse.class);
       // response.setResCode("AAAA");
        //response.setResMsg("Success!!!");
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
