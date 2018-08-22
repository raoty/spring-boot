package com.ngp.core.dict.enums;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public enum Sex {
	man("0"),women("1");
	
	private Log log = LogFactory.getLog(getClass());
	private String sexFlag;
	
	private Sex(String sex) {
		sexFlag = sex; 
	}
	
	public String getsexFlag() {
		if ("0".equals(sexFlag)) {
			return "man";
		}else if("1".equals(sexFlag)) {
			return "women";
		}else {
			log.error("unkown sex, sex value is: " + sexFlag);
			return "UNKOWN";
		}
	}
	
	 public static String parseOfValue(String sex){
		 if ("0".endsWith(sex)) {
			 return Sex.man.getsexFlag();
		}else {
			return Sex.women.getsexFlag();
		}
	 }
}
