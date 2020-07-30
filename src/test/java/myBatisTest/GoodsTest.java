//import cn.hll520.wtuShop.mapper.CategoryMapper;
//import cn.hll520.wtuShop.mapper.GoodsMapper;
//import cn.hll520.wtuShop.pojo.Category;
//import cn.hll520.wtuShop.pojo.Goods;
//import cn.hll520.wtuShop.pojo.GoodsComplex;
//import cn.hll520.wtuShop.utils.MyBatisTools;
//import org.apache.ibatis.session.SqlSession;
//import org.junit.Test;
//
//import java.lang.reflect.Array;
//import java.time.LocalDateTime;
//import java.util.*;
//
///**
// * @author lpc
// * @create 2020-07-22-9:37
// */
//public class GoodsTest {
//
//
//    @Test
//    public void insertCategoryTest() {
//        Category category = new Category();
//        category.setName("数码产品");
//        category.setGoods_num(0);
//        SqlSession sqlSession = MyBatisTools.getSqlSession();
//
//
//
//        int i = 0;
//        try {
//            CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
//            i = mapper.insertCategory(category);
//            sqlSession.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            sqlSession.rollback();
//        } finally {
//            MyBatisTools.close(sqlSession);
//        }
//
//        System.out.println(i);
//    }
//
//    @Test
//    public void selectByIdTest(){
//        SqlSession sqlSession = MyBatisTools.getSqlSession();
//        Category category=null;
//        try {
//            CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
//           category = mapper.selectById(1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            MyBatisTools.close(sqlSession);
//        }
//        System.out.println(category);
//    }
//
//    @Test
//    public void insertGoodsTest(){
//        Goods goods=new Goods();
//        Date time=new Date();
//        goods.setName("海尔洗衣机");
//        goods.setPrice(200000);
//        goods.setNum(20);
//        goods.setCreateDate(time);
//        goods.setUpdateDate(time);
//        goods.setCategory_id(1);
//
//        SqlSession sqlSession = MyBatisTools.getSqlSession();
//        int i = 0;
//        try {
//            GoodsMapper mapper = sqlSession.getMapper(GoodsMapper.class);
//            i = mapper.insertGoods(goods);
//            sqlSession.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            sqlSession.rollback();
//        } finally {
//            MyBatisTools.close(sqlSession);
//        }
//
//        System.out.println(i);
//        System.out.println(goods);
//        System.out.println(goods.getId());
//    }
//
//
//    @Test
//    public void selectGoodsTest(){
//        SqlSession sqlSession = MyBatisTools.getSqlSession();
//        Goods goods=null;
//        try {
//            GoodsMapper mapper = sqlSession.getMapper(GoodsMapper.class);
//            goods = mapper.selectById(1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            MyBatisTools.close(sqlSession);
//        }
//        System.out.println(goods);
//    }
//
//    @Test
//    public void selectGoodsJoinTest(){
//        SqlSession sqlSession = MyBatisTools.getSqlSession();
//        Goods goods=null;
//        try {
//            GoodsMapper mapper = sqlSession.getMapper(GoodsMapper.class);
//            goods = mapper.selectByJoinID(1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            MyBatisTools.close(sqlSession);
//        }
//        System.out.println(goods);
//    }
//
//    @Test
//    public void searchTest() {
//        SqlSession sqlSession = MyBatisTools.getSqlSession();
//        Goods goods = new Goods();
//        goods.setName("%洗衣机%");
////        goods.setCategory_id(1);
////        Map<String, Object> map = new HashMap<>();
////        map.put("startIndex", 1);
////        map.put("pageSize", 10);
////        map.put("key", goods);
//        List<Goods> search = null;
//        try {
//
//            GoodsMapper mapper = sqlSession.getMapper(GoodsMapper.class);
//            search = mapper.search(goods,1,10);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            MyBatisTools.close(sqlSession);
//        }
//        System.out.println(Arrays.toString(new List[]{search}));
//    }
//
//    @Test
//    public void delManyTest() {
//        SqlSession sqlSession = MyBatisTools.getSqlSession();
//        List<Integer> ids = new ArrayList<>();
//        int i = 0;
//        try {
//            ids.add(1);
//            ids.add(2);
//            GoodsMapper mapper = sqlSession.getMapper(GoodsMapper.class);
//            i = mapper.delManyByIds(ids);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            MyBatisTools.close(sqlSession);
//        }
//        System.out.println(i);
//    }
//
//    @Test
//    public void updateTest() {
//        SqlSession sqlSession = MyBatisTools.getSqlSession();
//        Goods goods = new Goods();
////        goods.setName("%洗衣机%");
////        goods.setPrice(20000);
////        goods.setNum(50);
////        goods.setCategory_id(1);
//        int i = 0;
//        try {
//
//            GoodsMapper mapper = sqlSession.getMapper(GoodsMapper.class);
//            i = mapper.updateGoods(goods);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            MyBatisTools.close(sqlSession);
//        }
//        System.out.println(i);
//    }
//}
