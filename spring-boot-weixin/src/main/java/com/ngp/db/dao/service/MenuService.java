package com.ngp.db.dao.service;

import com.ngp.db.dao.mapper.MenuMapper;
import com.ngp.db.dao.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Menu-service
 *
 */
@Service
public class MenuService {

    @Autowired
    private MenuMapper mapper;

    public List<MenuVo> selectAllMenu() {
        return mapper.selectAllMenu();
    }

}
