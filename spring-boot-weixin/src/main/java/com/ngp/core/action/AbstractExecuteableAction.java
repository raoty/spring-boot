package com.ngp.core.action;

import com.ngp.core.dict.Dict;
import com.ngp.core.transport.HttpTransport;
import com.ngp.wx.config.db.MultiJdbcAccess;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lettuce on 2017/6/28.
 */
public abstract class AbstractExecuteableAction implements Action,Executable{
    @Autowired
    private MultiJdbcAccess multiJdbcAccess;

    @Autowired
    private HttpTransport httpTransport;

    /**
     * 获取默认数据库操作类
     * @return
     */
    public SqlSession getSqlMap(){
        return multiJdbcAccess.getSqlMap();
    }

    public HttpTransport getHttpTransport(){
        return httpTransport;
    }

    public Map execute(Object obj) throws Exception{
		Map responseMap = new HashMap();
		Map requestMap = new HashMap();
    	if(obj instanceof HttpEntity){
    		HttpEntity httpEntity = (HttpEntity)obj;
    		requestMap = new HashMap();
            if(httpEntity.getBody() == null ||httpEntity.getBody() instanceof Map){
                requestMap = (Map)httpEntity.getBody();
                responseMap = execute(requestMap);
            }
            else
                throw new Exception("request parameters must be null or instanceof map ");
    	}
    	if(obj instanceof String){
    		requestMap.put(Dict.REQUESTSTRING, obj);
    		responseMap = execute(requestMap);
    	}
    	if(obj instanceof Map){
    	    responseMap = execute((Map)obj);
        }
    	
        responseMap.put(Dict.RESPONSECODE,"AAAAAAA");
        return responseMap;
    }


}
