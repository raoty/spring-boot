package com.ngp.core.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ngp.core.transport.HttpTransport;

public abstract class BaseAction {
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private HttpTransport httpTransport;

	public HttpTransport getHttpTransport() {
		return httpTransport;
	}
	
}
