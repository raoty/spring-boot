package com.ngp.core.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ngp.core.transport.HttpTransport;

public abstract class BaseAction {
	protected Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private HttpTransport httpTransport;

	public HttpTransport getHttpTransport() {
		return httpTransport;
	}
	
}
