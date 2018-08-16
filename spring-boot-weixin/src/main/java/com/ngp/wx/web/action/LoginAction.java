package com.ngp.wx.web.action;

import com.ngp.core.action.AbstractExecuteableAction;
import com.ngp.db.dao.service.LoginService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;


public class LoginAction extends AbstractExecuteableAction {


	@Autowired
	private LoginService loginService;
	
    @Override
    public Map execute(Map map) throws Exception {
        return loginService.selectObjById("");
    }
}
