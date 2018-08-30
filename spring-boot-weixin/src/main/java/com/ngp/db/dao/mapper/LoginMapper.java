package com.ngp.db.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ngp.db.dao.vo.NgpWxAccessToken;
import com.ngp.db.dao.vo.NgpWxUser;
import com.ngp.db.dao.vo.TestVo;

/**
 * 对应Login.xml中的相应方法
 *
 */
@Mapper
public interface LoginMapper {

	TestVo selectObjById(String id);		//根据id选一个数据

	@Select("select * from test")
	List<TestVo> selectAll();//查询全部

	NgpWxUser selectUserById(String id);
	
	NgpWxUser selectUserByUser(NgpWxUser nwu);

	void insertAccessToken(NgpWxAccessToken ngpWxAccessToken);
	
	List<NgpWxAccessToken> selectAccessTokenList();
	
	int updateAccessToken(NgpWxAccessToken ngpWxAccessToken);
}
