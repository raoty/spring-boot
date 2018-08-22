package com.ngp.core.util;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JsonUtil {




    public static void main(String[] args) {
        Map map = new HashMap<String,String>();
        map.put("name","zhangsan");
        map.put("age","12");
        Map map1 = new HashMap<String,String>();
        map1.put("name","zhangsan");
        map1.put("age","12");

        List list = new LinkedList();
        list.add(map);
        list.add(map1);


        System.out.println(JSON.toJSON(map));
        System.out.println(JSON.toJSON(list));

    }
}
