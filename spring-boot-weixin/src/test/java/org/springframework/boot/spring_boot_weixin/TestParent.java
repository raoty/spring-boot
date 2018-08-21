package org.springframework.boot.spring_boot_weixin;

import com.ngp.WxApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={WxApplication.class})
@SpringBootTest
public class TestParent {
}
