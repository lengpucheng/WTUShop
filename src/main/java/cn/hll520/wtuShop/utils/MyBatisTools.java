package cn.hll520.wtuShop.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * @author lpc
 * @create 2020-07-22-8:42
 */
public class MyBatisTools {
    private static SqlSessionFactory sqlSessionFactory = null;

    static {
        String resource = "mybatis/Configuration.xml";
        /* 以字符流读取 */
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取一个SQLSession
     * @return  返回一个SQL会话
     */
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }

    /**
     * 关闭SQLSession
     * @param sqlSession SQL会话对象
     */
    public static void close(SqlSession sqlSession){
        if(sqlSession!=null)
            sqlSession.close();
    }

}
