package com.ngp.wx.web.action;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ngp.core.action.AbstractExecuteableAction;
import com.ngp.core.util.BeanUtils;
import com.ngp.db.dao.service.LoginService;
import com.ngp.db.dao.vo.NgpWxUser;

@Component
public class LoginAction extends AbstractExecuteableAction {

	@Autowired
	private LoginService ls;

    @Override
    public Map execute(Map map) throws Exception {
        String id = (String) map.get("Id");
        String passWord = (String) map.get("PassWord");
        NgpWxUser query = new NgpWxUser();
        query.setName(id);
        query.setPasswd(passWord);
        System.out.println("==========query:" + query);
        NgpWxUser user = ls.selectUserByUser(query);
        
        System.out.println("==================res:" + user);
        if (user!=null) {
        	return BeanUtils.bean2Map(user);
		} else {
			return null;
		}
    }
}
