package com.ngp.wx.web.controller;


import com.ngp.core.action.Action;
import com.ngp.core.action.Executable;
import com.ngp.core.dict.ErrorConstants;
import com.ngp.wx.config.db.MultiJdbcAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * json 接口数据请求
 */
@RestController
public class MainController implements ApplicationContextAware{

    @Autowired
    private MultiJdbcAccess multiJdbcAccess;

    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @RequestMapping(value = "/wx/{trsId}")
    public String queryprojects(@PathVariable("trsId") String trsId) throws Exception{
        Object action = applicationContext.getBean(trsId);
        if(action instanceof Action){
            Action action1 = (Action)action;
            if(action1 instanceof Executable){
                ((Executable) action1).execute(new HashMap());
            }
        }else{
            throw new Exception(ErrorConstants.UNDEFIND_ERROR);
        }
        return "";
    }

}
