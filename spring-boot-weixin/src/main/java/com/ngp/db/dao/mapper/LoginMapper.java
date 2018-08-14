package com.ngp.db.dao.mapper;

import com.ngp.db.dao.vo.TestVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 对应Login.xml中的相应方法
 *
 */
@Mapper
public interface LoginMapper {

	TestVo selectObjById(String id);		//根据id选一个数据

	@Select("select * from test")
	List<TestVo> selectAll();//查询全部

}
