package myBatisTest;

import cn.hll520.wtuShop.mapper.GoodsMapper;
import cn.hll520.wtuShop.pojo.Goods;
import cn.hll520.wtuShop.pojo.GoodsComplex;
import cn.hll520.wtuShop.utils.MyBatisTools;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author lpc
 * @create 2020-07-27-9:12
 */
public class GoodsMapperTest {

    @Test
    public void selectByIdTest() {
        SqlSession sqlSession = MyBatisTools.getSqlSession();
        Goods goods = null;
        GoodsComplex goodsComplex = null;
        try {
            GoodsMapper mapper = sqlSession.getMapper(GoodsMapper.class);
            goodsComplex = mapper.selectComplexByPrimaryKey(5);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisTools.close(sqlSession);
        }
        System.out.println(goodsComplex);
    }

    @Test
    public void selectPage() {
        SqlSession sqlSession = MyBatisTools.getSqlSession();
        try {
            GoodsMapper mapper = sqlSession.getMapper(GoodsMapper.class);
            //像ThreadLocal里存储分页信息
            PageHelper.startPage(1, 4, true);
            //执行查询前会先执行分页拦截器
            List<Goods> goods = mapper.selectByExample(null);

            PageInfo<Goods> pageInfo=new PageInfo<>(goods);
            System.out.println("_________________________");
            System.out.println(pageInfo);
//            System.out.println("\n"+goods);
//            System.out.println();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisTools.close(sqlSession);
        }

    }
}
