import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 传统的JDBC
 */

public class JDBCTest {
    public static void main(String[] args) throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            String url = "jdbc:mysql://localhost:3306/ssmdemo";
            String user = "root";
            String password = "123456";
            connection = DriverManager.getConnection(url,user,password);
            //获取statement,preparedStatement
            String sql = "select * from tb_user where id=?";
            preparedStatement = connection.prepareStatement(sql);
            //设置参数
            preparedStatement.setLong(1,2);
            //执行查询
            resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);
            //处理结果集
            while(resultSet.next()){
                System.out.println(resultSet.getString("user_name"));
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getString("age"));
                System.out.println(resultSet.getString("birthday"));
            }
        } finally {
            //关闭连接，释放资源
            if(resultSet != null)resultSet.close();
            if(preparedStatement != null)preparedStatement.close();
            if(connection != null)connection.close();
        }

    }
}
