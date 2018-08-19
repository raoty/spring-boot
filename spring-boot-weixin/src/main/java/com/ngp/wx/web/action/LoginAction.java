package com.ngp.wx.web.action;

import com.ngp.core.action.AbstractExecuteableAction;
import com.ngp.core.dict.Dict;
import com.ngp.db.dao.service.LoginService;
import com.ngp.db.dao.vo.NgpWxUser;
import com.ngp.db.dao.vo.TestVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LoginAction extends AbstractExecuteableAction {

//	@Autowired
//	private LoginService ls;

    @Override
    public Map execute(Map map) throws Exception {
        System.out.println("==========" + map.toString());
        Map res = new HashMap();
        res.put(Dict.NAME,"ZHANGSAN");
        return res;
    }
}
