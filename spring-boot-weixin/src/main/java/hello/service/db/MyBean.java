package hello.service.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyBean {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
//	@Autowired
//	public MyBean(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	

	
}
