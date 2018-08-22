package com.ngp.core.util;

import com.alibaba.fastjson.JSON;
import com.ngp.db.dao.vo.MenuVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;

public class MenuUtil {


    private static Log log = LogFactory.getLog(MenuUtil.class);
    public static String list2JsonStrForMenu(List list){
        Iterator<MenuVo> menus = list.iterator();
        Map res = new HashMap();
        List listres = new LinkedList();
        while (menus.hasNext()){
            MenuVo menu = menus.next();

            if(0 == menu.getFirstmenu()){//一级菜单
                Map map0 = BeanUtils.bean2Map(menu);
                //TODO 可能要去掉一些值
                log.info("map0:"+map0);
                map0.remove("MenuId");

                int menuid = menu.getMenuid();
                List subList = new LinkedList();
                while (menus.hasNext()) {
                    MenuVo menu1 = menus.next();
                    if(menuid == menu1.getFirstmenu()){
                        Map map1 = BeanUtils.bean2Map(menu1);
                        log.info("map1:"+map1);
                        //TODO 要去掉一些值
                        subList.add(map1);
                    }
                }
                map0.put("sub_button" ,subList);


                listres.add(map0);
            }



        }
        res.put("button",listres);

        return JSON.toJSONString(res);

    }
}
