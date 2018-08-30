package com.ngp.batch.task;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.ngp.core.action.BaseAction;
import com.ngp.core.dict.WxConstants;
import com.ngp.core.transport.HttpClientRequest;
import com.ngp.db.dao.service.LoginService;
import com.ngp.db.dao.vo.NgpWxAccessToken;

@Component
public class WxAccessTokenFlushTask extends BaseAction{

    private AtomicLong counter = new AtomicLong();

	@Autowired
	private LoginService loggeroginService;
	
    @Scheduled(cron = "0/10 * * * * *")
    public void scheduleWithCronExpression() throws Exception {
    	long count = counter.incrementAndGet();
    	
    	log.info("Schedule executor begin {} times with ", count);
    	
    	String WxRefreshUrl = WxConstants.WX_REFRESH_TOKEN + "appid=" + WxConstants.APPID + "&grant_type=refresh_token&refresh_token=";
    	List<NgpWxAccessToken> list = loggeroginService.getAccessTokenList();
    	HttpClientRequest httpClientRequest = new HttpClientRequest();
    	NgpWxAccessToken tmp = new NgpWxAccessToken();
    	Gson gson = new Gson();
    	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    	String taskRes;
    	
    	for (NgpWxAccessToken ngpWxAccessToken : list) {
    		WxRefreshUrl = WxRefreshUrl + ngpWxAccessToken.getRefresh_token();
    		log.info("===" + WxRefreshUrl);
    		httpClientRequest.setUrl(WxRefreshUrl);
    		taskRes = getHttpTransport().execute(httpClientRequest);
    		tmp = gson.fromJson(taskRes, NgpWxAccessToken.class);
    		tmp.setAccess_up_date(timestamp);
    		loggeroginService.updateAccessToken(tmp);
    		
    		log.info("=====批量执行结果：" + taskRes);
		}
    	
        log.info("Schedule executor end {} times with ", count);
    }
}
