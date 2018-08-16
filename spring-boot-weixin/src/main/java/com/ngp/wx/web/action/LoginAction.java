package com.ngp.wx.web.action;

import com.ngp.core.action.AbstractExecuteableAction;
import com.ngp.core.dict.Dict;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LoginAction extends AbstractExecuteableAction {


    @Override
    public Map execute(Map map) throws Exception {
        System.out.println("==========" + map.toString());
        Map res = new HashMap();
        res.put(Dict.NAME,"ZHANGSAN");
        return res;
    }
}
