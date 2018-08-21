package com.ngp.wx.web.action;

import com.ngp.core.action.AbstractExecuteableAction;
import com.ngp.db.dao.mapper.MenuMapper;
import com.ngp.db.dao.service.MenuService;
import com.ngp.db.dao.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class MenuPushAction extends AbstractExecuteableAction{


    @Autowired
    private MenuService menuService;
    @Override
    public Map execute(Map map) throws Exception {

        List<MenuVo> menus = menuService.selectAllMenu();

        System.out.println(menus);

        return null;
    }
}
