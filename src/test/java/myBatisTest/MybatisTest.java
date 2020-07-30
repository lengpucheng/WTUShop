//import cn.hll520.wtuShop.mapper.CategoryMapper;
//import cn.hll520.wtuShop.mapper.UserInfoMapper;
//import cn.hll520.wtuShop.pojo.Category;
//import cn.hll520.wtuShop.pojo.UserInfo;
//import cn.hll520.wtuShop.utils.MyBatisTools;
//import org.apache.ibatis.io.Resources;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//import org.junit.Test;
//
//import java.io.IOException;
//import java.io.Reader;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author lpc
// * @create 2020-07-20-11:10
// */
//public class MybatisTest {
//
//    @Test
//    public void testSelect() throws IOException {
//        String resource="mybatis/Configuration.xml";
//
//        /* 以字符流读取 */
//        Reader reader = Resources.getResourceAsReader(resource);
//
//        /* 通过配置文件构造会话工厂类 */
//        SqlSessionFactory  sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//
//        /* 获取SQL会话 */
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        /* 根据接口获取实现对象 */
//        UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);
//
//        UserInfo user = mapper.selectByID(1);
//
//        System.out.println(user);
//
//        /* 关闭数据库会话 */
//        sqlSession.close();
//
//    }
//
//
//    @Test
//    public void testInsert() {
//        String resource="mybatis/Configuration.xml";
//        SqlSession sqlSession = null;
//        try {
//            /* 以字符流读取 */
//            Reader reader = Resources.getResourceAsReader(resource);
//
//            /* 通过配置文件构造会话工厂类 */
//            SqlSessionFactory  sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//
//            /* 获取SQL会话 */
//            sqlSession = sqlSessionFactory.openSession();
//
//            UserInfo userInfo=new UserInfo();
//            userInfo.setUsername("sadfdsfsdafsafsdfsdfsdfdsfdfdsds车");
//            userInfo.setPassword("uewouwei");
//            userInfo.setRealname("ddg");
//
//            /*  通过配置文件的  命名空间.方法  传入参数 */
//            UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);
//            int insert = mapper.insertUser(userInfo);
//
//            System.out.println(insert);
//            /* 默认不提交   需要手动提交 */
//            sqlSession.commit();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            /* 关闭数据库会话 */
//            assert sqlSession != null;
//            sqlSession.close();
//        }
//    }
//
//    @Test
//    public void testUpdate() {
//        String resource="mybatis/Configuration.xml";
//        SqlSession sqlSession = null;
//        try {
//            /* 以字符流读取 */
//            Reader reader = Resources.getResourceAsReader(resource);
//
//            /* 通过配置文件构造会话工厂类 */
//            SqlSessionFactory  sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//
//            /* 获取SQL会话 */
//            sqlSession = sqlSessionFactory.openSession();
//
//            UserInfo userInfo=new UserInfo();
//            userInfo.setUserid(7);
//            userInfo.setUsername("修改后的");
//            userInfo.setPassword("huawei");
//            userInfo.setRealname("dg");
//
//            /*  通过配置文件的  命名空间.方法  传入参数 */
//            int update = sqlSession.update("cn.hll520.wtuShop.mapper.UserInfoMapper.update", userInfo);
//            System.out.println(update);
//            /* 默认不提交   需要手动提交 */
//            sqlSession.commit();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            /* 关闭数据库会话 */
//            assert sqlSession != null;
//            sqlSession.close();
//        }
//    }
//
//    @Test
//    public void testDelete() {
//        String resource="mybatis/Configuration.xml";
//        SqlSession sqlSession = null;
//        try {
//            /* 以字符流读取 */
//            Reader reader = Resources.getResourceAsReader(resource);
//
//            /* 通过配置文件构造会话工厂类 */
//            SqlSessionFactory  sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//
//            /* 获取SQL会话 */
//            sqlSession = sqlSessionFactory.openSession();
//
//
//            /*  通过配置文件的  命名空间.方法  传入参数 */
//            UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);
//            int delete= mapper.deleteById(5);
//            System.out.println(delete);
//            /* 默认不提交   需要手动提交 */
//            sqlSession.commit();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            /* 关闭数据库会话 */
//            assert sqlSession != null;
//            sqlSession.close();
//        }
//    }
//
//    @Test
//    public void testSelectAll() {
//        String resource="mybatis/Configuration.xml";
//        SqlSession sqlSession = null;
//        try {
//            /* 以字符流读取 */
//            Reader reader = Resources.getResourceAsReader(resource);
//
//            /* 通过配置文件构造会话工厂类 */
//            SqlSessionFactory  sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//
//            /* 获取SQL会话 */
//            sqlSession = sqlSessionFactory.openSession();
//
//
//            /*  通过配置文件的  命名空间.方法  传入参数 */
//
//            List<UserInfo> userInfos = sqlSession.selectList("cn.hll520.wtuShop.mapper.UserInfoMapper.selectAll");
//            System.out.println(Arrays.toString(new List[]{userInfos}));
//            /* 默认不提交   需要手动提交 */
//            sqlSession.commit();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            /* 关闭数据库会话 */
//            assert sqlSession != null;
//            sqlSession.close();
//        }
//    }
//
//    @Test
//    public void testSelectCount() {
//        String resource="mybatis/Configuration.xml";
//        SqlSession sqlSession = null;
//        try {
//            /* 以字符流读取 */
//            Reader reader = Resources.getResourceAsReader(resource);
//
//            /* 通过配置文件构造会话工厂类 */
//            SqlSessionFactory  sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//
//            /* 获取SQL会话 */
//            sqlSession = sqlSessionFactory.openSession();
//
//
//            /*  通过配置文件的  命名空间.方法  传入参数 */
//            int count = sqlSession.selectOne("cn.hll520.wtuShop.mapper.UserInfoMapper.selectCount");
//            System.out.println(count);
//            /* 默认不提交   需要手动提交 */
//            sqlSession.commit();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            /* 关闭数据库会话 */
//            assert sqlSession != null;
//            sqlSession.close();
//        }
//    }
//
//    @Test
//    public void testSelectLimit() {
//        String resource="mybatis/Configuration.xml";
//        SqlSession sqlSession = null;
//        try {
//            /* 以字符流读取 */
//            Reader reader = Resources.getResourceAsReader(resource);
//
//            /* 通过配置文件构造会话工厂类 */
//            SqlSessionFactory  sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//
//            /* 获取SQL会话 */
//            sqlSession = sqlSessionFactory.openSession();
//
//
//            /*  通过配置文件的  命名空间.方法  传入参数 */
//            Map<String,Object> map= new HashMap<>();
//            map.put("starIndex",1);
//            map.put("pageSize",2);
//            UserInfo info=new UserInfo();
//            info.setUsername("%sf%");
//            info.setRealname("%sf%");
//            map.put("key",info);
//            List<UserInfo> userInfos = sqlSession.selectList("cn.hll520.wtuShop.mapper.UserInfoMapper.selectLimit", map);
//            System.out.println(Arrays.toString(new List[]{userInfos}));
//            /* 默认不提交   需要手动提交 */
//            sqlSession.commit();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            /* 关闭数据库会话 */
//            assert sqlSession != null;
//            sqlSession.close();
//        }
//    }
//
//    @Test
//    public void testGoodsAndCategory(){
//        SqlSession sqlSession = MyBatisTools.getSqlSession();
//        CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
//        Category category = mapper.selectCategoryOfGoodsById(1);
//        System.out.println(category);
//        Category category1 = mapper.selectById(1);
//        System.out.println(category1);
//    }
//}
