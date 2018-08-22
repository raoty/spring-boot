package ngp.test.menu;

import com.ngp.core.util.BeanUtils;
import com.ngp.db.dao.service.MenuService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.spring_boot_weixin.TestParent;

public class MenuTest extends TestParent{

    @Autowired
    private MenuService menuService;

    @Test
    public void testMenu(){

        System.out.println(BeanUtils.listBean2ListMap(menuService.selectAllMenu()));
    }
}
