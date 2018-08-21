package com.ngp.db.dao.mapper;

import com.ngp.db.dao.vo.MenuVo;
import com.ngp.db.dao.vo.TestVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 对应menu.xml中的相应方法
 *
 */
@Mapper
public interface MenuMapper {


	@Select("select * from menu")
	List<MenuVo> selectAllMenu();//查询全部菜单

}
