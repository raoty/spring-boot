package ngp.test.dbTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ngp.db.dao.service.LoginService;
import com.ngp.db.dao.vo.NgpWxUser;

import ngp.test.TestParent;



public class NgpWxUserTest extends TestParent{
	
	@Autowired
	private LoginService ls;
	
	@Test
	public void userSelectByIdTest() {
		NgpWxUser ngpWxUser = ls.selectUserById("1");

		System.out.println("====:"+ngpWxUser);
		System.out.println("@@@@@@ success");
	}
}
