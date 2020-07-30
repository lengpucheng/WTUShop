package springTest;

import cn.hll520.wtuShop.pojo.Goods;
import cn.hll520.wtuShop.pojo.GoodsComplex;
import cn.hll520.wtuShop.pojo.UserInfo;
import cn.hll520.wtuShop.service.UserInfoService;
import cn.hll520.wtuShop.service.impl.UserInfoServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lpc
 * @create 2020-07-27-10:50
 */
public class SpringTest {
    @Test
    public void testBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring.xml");
        GoodsComplex goods = context.getBean(GoodsComplex.class);
        System.out.println(goods);
        Goods goods2 = context.getBean(GoodsComplex.class);
        System.out.println(goods2);
        System.out.println(goods == goods2);
        // 单例模式
        //IOC 控制反转 创建对象的过程交给容器  DI 依赖注入  在一个对象中ref引用另一个对象
    }

    @Test
    public void testService() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring.xml");
        UserInfoService bean = context.getBean(UserInfoServiceImpl.class);
        UserInfo userInfo=new UserInfo();
        userInfo.setUsername("222");
        userInfo.setPassword("dome");
        UserInfo login = bean.login(userInfo);
        System.out.println(login);
        // 单例模式
        //IOC 控制反转 创建对象的过程交给容器  DI 依赖注入  在一个对象中ref引用另一个对象
    }
}
