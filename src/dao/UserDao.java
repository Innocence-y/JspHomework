package dao;

import beans.User;
import db.DbAccess;
import org.junit.Test;
import service.DeleteService;
import service.LoginService;
import service.QueryService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 和user表相关的数据库操作
 */
public class UserDao {

    /**
     * 注册用户
     */
    public User insertUser(User user) throws SQLException, ClassNotFoundException {
        Connection connection = DbAccess.getConnection();
        String sql = "INSERT INTO USER(name,password,age,gender,headurl) VALUES(?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setInt(3, user.getAge());
        preparedStatement.setInt(4, user.getGender());
        preparedStatement.setString(5, user.getHeadUrl());
        int row = preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
        if (row != 0) {
            return user;
        } else {
            return null;
        }
    }

    /**
     * 登录用户
     */
    public User queryUser(User user) throws SQLException, ClassNotFoundException {
        Connection connection = DbAccess.getConnection();
        String sql = "SELECT username,password,headurl FROM user";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        ArrayList<User> arrayList = new ArrayList<User>();
        while (resultSet.next()) {
            User users = new User();
            arrayList.add(users);
            users.setUsername(resultSet.getString("username"));
            users.setPassword(resultSet.getString("password"));
            users.setHeadUrl(resultSet.getString("headurl"));
        }

        for (User tableuser : arrayList) {
            if (tableuser.getUsername().equals(user.getUsername()) && tableuser.getPassword().equals(user.getPassword())) {
                return tableuser;
            }
        }
        resultSet.close();
        statement.close();
        connection.close();
        return null;
    }

    /**
     * 根据条件查询用户列表
     */
    public List<User> queryUserList(User user, int frontage, int backage) throws SQLException, ClassNotFoundException {
        Connection connection = DbAccess.getConnection();
        String sql = "SELECT username,gender,age,headurl FROM USER WHERE gender = ? AND age BETWEEN ? AND ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user.getGender());
        preparedStatement.setInt(2, frontage);
        preparedStatement.setInt(3, backage);
        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<User> arrayList = new ArrayList<User>();
        while (resultSet.next()) {
            User currentuser = new User();
            arrayList.add(currentuser);
            currentuser.setUsername(resultSet.getString("username"));
            currentuser.setGender(resultSet.getInt("gender"));
            currentuser.setAge(resultSet.getInt("age"));
            currentuser.setHeadUrl(resultSet.getString("username"));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return arrayList;
    }

    /**
     * 根据id值单挑删除用户
     */
    public void deleteOne(int id) throws SQLException, ClassNotFoundException {
        Connection connection = DbAccess.getConnection();
        String sql = "DELETE FROM user WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    /**
     * 批量多条删除用户
     */
    public void deleteBatch(List<Integer> idList) throws SQLException, ClassNotFoundException {
        Connection connection = DbAccess.getConnection();
        StringBuilder sql = new StringBuilder("DELETE FROM user WHERE 1=1 AND id IN");
        for (int i = 1; i <= idList.size(); i++) {
            if (i == 1) {
                sql.append("(");
                sql.append(idList.get(i - 1));
                sql.append(",");
            } else if (i == idList.size()) {
                sql.append(idList.get(i - 1));
                sql.append(")");
            } else {
                sql.append(idList.get(i - 1));
                sql.append(",");
            }
        }
        Statement statement = connection.createStatement();
        int rows = statement.executeUpdate(sql.toString());
        statement.close();
        connection.close();
    }

    public User UpdateUserByUser(User user, int id) throws SQLException, ClassNotFoundException {
        Connection connection = DbAccess.getConnection();
        String update_Sql = "UPDATE user SET ? = ? WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(update_Sql);

        int rows = preparedStatement.executeUpdate();

        String select_Sql = "SELECT username,password,age,gender,headurl FROM USER WHERE id = ?";



        if (rows != 0) {
            preparedStatement.close();
            connection.close();

        }

    }


}
