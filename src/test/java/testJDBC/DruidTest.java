package testJDBC;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author lpc
 * @create 2020-07-29-13:00
 */
public class DruidTest {



    @Test
    public void Creat() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("Hll520!!!");
        dataSource.setUrl("jdbc:mysql://db.wtu.hll520.cn:10105/WTUShop");
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM goods");
        while (resultSet.next()){
            System.out.println(resultSet.getObject(1));
            System.out.println(resultSet.getObject(2));
            System.out.println(resultSet.getObject(3));
            System.out.println(resultSet.getObject(4));
            System.out.println(resultSet.getObject(5));
            System.out.println("\n");
        }

//        UserInfoService service=new UserInfoServiceImpl();
//        UserInfo userInfo=new UserInfo();
//        userInfo.setUsername("222");
//        userInfo.setPassword("dome");
//        UserInfo login = service.login(userInfo);
//        System.out.println(login);
    }
}
