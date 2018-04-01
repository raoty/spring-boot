package hello.service.db.mapper;

import org.apache.ibatis.annotations.Mapper;

import hello.service.db.bean.AppMessage;

/**
 * 对应AppMessageMapper.xml中的相应方法
 * @author Miao
 *
 */
@Mapper
public interface AppMessageMapper {
	
	AppMessage selectByPrimaryKey(String id);		//根据id选一个数据
	
    int deleteByPrimaryKey(String id);				//删除一个数据

    int insert(AppMessage record);					//插入一个数据

    int insertSelective(AppMessage record);			//动态sql

    int updateByPrimaryKeySelective(AppMessage record);

}
