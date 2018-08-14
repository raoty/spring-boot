package com.ngp.wx.config.db;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MultiJdbcAccess {

    private Map<String,SqlSession> sqlMapRegister = new HashMap<String,SqlSession>();

    public static final String DB_DEFAULT = "default";  //默认数据源
    public static final String DB_WX = "wx";          //

    @Autowired
    public MultiJdbcAccess(SqlSession sqlSession,
                           @Qualifier("wxSqlSessionTemplate") SqlSession wxSqlSessionTemplate){
        sqlMapRegister.put(DB_DEFAULT, sqlSession);
        sqlMapRegister.put(DB_WX, wxSqlSessionTemplate);

    }

    public SqlSession getSqlMap(){
        return sqlMapRegister.get(DB_WX);
    }

}
