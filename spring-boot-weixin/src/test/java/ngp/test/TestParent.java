package ngp.test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ngp.WxApplication;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WxApplication.class})
@SpringBootTest
//@DirtiesContext   每个测试方法都使用新的上下文
public class TestParent {

}
