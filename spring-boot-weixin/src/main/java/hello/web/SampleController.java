package hello.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.service.db.MyBean;
import hello.service.db.bean.AppMessage;
import hello.service.db.service.AppMessageService;

@Controller
public class SampleController {

	@Autowired
	private MyBean dbWx;
	
	@Autowired
	private AppMessageService ams;
	
    @RequestMapping("/wap/jdbc")
    @ResponseBody
    String home() {
    	String h = "jdbc 数据库连接测试：<br/>";
    	List<Map<String, Object>> ql = dbWx.getJdbcTemplate().queryForList("select * from test");
    	for (Map<String, Object> map : ql) {
    		h = h + "name = " + map.get("name") + "<br/>";
		}
    	return h;
    }

    @RequestMapping("/wap/mybatis/sId")
    @ResponseBody
    public List<AppMessage> getMybatiSelectByid(){
    	return ams.getMessage("1000000");
    }
}
