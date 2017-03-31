package db;

import beans.User;
import service.RegisterService;
import servlet.RegisterServlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by JiYongGuang on 2017/2/28.
 */
public class DbAccess {

    private static Connection connection = null;
    private static final String url = "jdbc:mysql://localhost:3306/jsphomework?useUnicode=true&characterEncoding=utf8";
    private static final String username = "root";
    private static final String password = "jiyongguang.";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = (Connection) DriverManager.getConnection(url, username, password);
        return connection;
    }

}
