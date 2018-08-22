package com.ngp.wx.web.action;

import com.ngp.core.action.AbstractExecuteableAction;
import com.ngp.core.dict.Dict;
import com.ngp.core.transport.HttpClientRequest;
import com.ngp.core.util.MenuUtil;
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

        String jsonmenu = MenuUtil.list2JsonStrForMenu(menus);

        HttpClientRequest request = new HttpClientRequest();
        request.setJsonStr(jsonmenu);
        request.setUrl(Dict.WXURL);

        getHttpTransport().execute(request);
        System.out.println(menus);

        return null;
    }
}
