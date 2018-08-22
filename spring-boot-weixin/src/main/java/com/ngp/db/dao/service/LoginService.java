package com.ngp.db.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ngp.db.dao.mapper.LoginMapper;
import com.ngp.db.dao.vo.NgpWxUser;
import com.ngp.db.dao.vo.TestVo;

/**
 * 登录service
 *
 */
@Service
public class LoginService{

    @Autowired
    private LoginMapper mapper;

    public TestVo selectObjById(String id){
        return mapper.selectObjById(id);
    }
    
    public NgpWxUser selectUserById(String id) {
    	return mapper.selectUserById(id);
    }
    
    public NgpWxUser selectUserByUser(NgpWxUser nwu) {
    	return mapper.selectUserByUser(nwu);
    }
    
}
