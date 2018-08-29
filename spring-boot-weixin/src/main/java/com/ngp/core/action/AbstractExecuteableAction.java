package com.ngp.core.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;

import com.ngp.core.action.interfaces.Action;
import com.ngp.core.action.interfaces.Executable;
import com.ngp.core.dict.Dict;

/**
 * Created by lettuce on 2017/6/28.
 */
public abstract class AbstractExecuteableAction extends BaseAction implements Action,Executable{
	
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
