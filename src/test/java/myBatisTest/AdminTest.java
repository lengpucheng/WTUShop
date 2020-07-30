package myBatisTest;

import cn.hll520.wtuShop.mapper.AdminMapper;
import cn.hll520.wtuShop.pojo.Admin;
import cn.hll520.wtuShop.pojo.AdminExample;
import cn.hll520.wtuShop.utils.MyBatisTools;
import org.apache.ibatis.session.SqlSession;
import cn.hll520.wtuShop.pojo.AdminExample.*;
import org.junit.Test;

/**
 * @author lpc
 * @create 2020-07-24-12:53
 */
public class AdminTest {

    /* 全覆盖增加 */
    @Test
    public void test1() {
        SqlSession sqlSession = MyBatisTools.getSqlSession();
        int insert = 0;
        try {
            AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
            Admin admin = new Admin();
            admin.setUsername("zhangshan");
            admin.setPassword("123123");
            admin.setRealname("张三");
            insert = mapper.insert(admin);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            MyBatisTools.close(sqlSession);
        }

        System.out.println(insert);
    }

    /* 选择增加 */
    @Test
    public void test2(){
        SqlSession sqlSession = MyBatisTools.getSqlSession();
        int insert = 0;
        try {
            AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
            Admin admin = new Admin();
            admin.setUsername("lisi");
            admin.setPassword("123123");
//            admin.setRealname("李四");
            insert = mapper.insertSelective(admin);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            MyBatisTools.close(sqlSession);
        }
        System.out.println(insert);
    }

    /* 修改全部 */
    @Test
    public void test3(){
        SqlSession sqlSession = MyBatisTools.getSqlSession();
        int insert = 0;
        try {
            AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
            Admin admin = new Admin();
            admin.setAdminid(2);
            admin.setUsername("lisi");
            admin.setPassword("123123");
            admin.setRealname("李四");
            insert = mapper.updateByPrimaryKey(admin);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            MyBatisTools.close(sqlSession);
        }
        System.out.println(insert);
    }

    /* 修改部分 */
    @Test
    public void test4(){
        SqlSession sqlSession = MyBatisTools.getSqlSession();
        int insert = 0;
        try {
            AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
            Admin admin = new Admin();
            admin.setAdminid(1);

            admin.setPassword("zhang3");
            insert = mapper.updateByPrimaryKeySelective(admin);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            MyBatisTools.close(sqlSession);
        }
        System.out.println(insert);
    }

    /* 条件修改部分 */
    @Test
    public void test5(){
        SqlSession sqlSession = MyBatisTools.getSqlSession();
        int insert = 0;
        try {
            AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);

            //修改后的对象
            Admin admin = new Admin();
            admin.setPassword("999999");

            //修改的条件
            AdminExample example=new AdminExample();
            Criteria criteria = example.createCriteria();
            criteria.andUsernameEqualTo("lisi");

            Criteria criteria1 = example.or();

            criteria1.andUsernameEqualTo("zhangshan");


            //执行修改
            insert = mapper.updateByExampleSelective(admin,example);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            MyBatisTools.close(sqlSession);
        }
        System.out.println(insert);
    }


    /* 删除 */
    @Test
    public void test6(){

    }
}
