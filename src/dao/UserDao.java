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

    public int UpdateUserByUser(User user, int id) throws SQLException, ClassNotFoundException {
        Connection connection = DbAccess.getConnection();
        String select_Sql = "SELECT username,password,age,gender,headurl FROM USER WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(select_Sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        User currentUser = new User();
        while (resultSet.next()) {
            currentUser.setId(resultSet.getInt("id"));
            currentUser.setUsername(resultSet.getString("username"));
            currentUser.setPassword(resultSet.getString("password"));
            currentUser.setAge(resultSet.getInt("age"));
            currentUser.setGender(resultSet.getInt("gender"));
            currentUser.setHeadUrl(resultSet.getString("headurl"));
        }

        resultSet.close();
        preparedStatement.close();

        int amount = 0;

        if (user.getUsername() != currentUser.getUsername()) {
            amount++;
        }
        if (user.getPassword() != currentUser.getPassword()) {
            amount++;
        }
        if (user.getAge() != currentUser.getAge()) {
            amount++;
        }
        if (user.getGender() != currentUser.getGender()) {
            amount++;
        }
        if (user.getHeadUrl() != currentUser.getHeadUrl()) {
            amount++;
        }

        StringBuffer stringBuffer = new StringBuffer();
        if (amount != 0) {
            stringBuffer.append("UPDATE USER SET `?` = ?");

            for (int i = 0; i < amount; i++) {
                stringBuffer.append(",");
                stringBuffer.append("`?` = ?");
            }
            stringBuffer.append(" WHERE id = ?");
        }//UPDATE USER SET ? = ? where id = ?
        //UPDATE USER SET ? = ?,? = ? where id = ?

        PreparedStatement preparedStatement1 = connection.prepareStatement(stringBuffer.toString());

        int variable = 0;
        if (user.getUsername() != currentUser.getUsername() && variable == 0) {
            preparedStatement1.setString(1, "username");
            preparedStatement1.setString(2, user.getUsername());
            variable++;
        }

        if (user.getPassword() != currentUser.getPassword() && variable == 1) {
            preparedStatement1.setString(3, "password");
            preparedStatement1.setString(4, user.getPassword());
            variable++;
        } else if (user.getPassword() != currentUser.getPassword() && variable == 0) {
            preparedStatement1.setString(1, "password");
            preparedStatement1.setString(2, user.getPassword());
            variable++;
        }

        if (user.getAge() != currentUser.getAge() && variable == 2) {
            preparedStatement1.setString(5, "age");
            preparedStatement1.setInt(6, user.getAge());
            variable++;
        } else if (user.getAge() != currentUser.getAge() && variable == 1) {
            preparedStatement1.setString(3, "age");
            preparedStatement1.setInt(4, user.getAge());
            variable++;
        } else if (user.getAge() != currentUser.getAge() && variable == 0) {
            preparedStatement1.setString(1, "age");
            preparedStatement1.setInt(2, user.getAge());
            variable++;
        }

        if (user.getGender() != currentUser.getGender() && variable == 3) {
            preparedStatement1.setString(7, "gender");
            preparedStatement1.setInt(8, user.getGender());
            variable++;
        } else if (user.getGender() != currentUser.getGender() && variable == 2) {
            preparedStatement1.setString(5, "gender");
            preparedStatement1.setInt(6, user.getGender());
            variable++;
        } else if (user.getGender() != currentUser.getGender() && variable == 1) {
            preparedStatement1.setString(3, "gender");
            preparedStatement1.setInt(4, user.getGender());
            variable++;
        } else if (user.getGender() != currentUser.getGender() && variable == 0) {
            preparedStatement1.setString(1, "gender");
            preparedStatement1.setInt(2, user.getGender());
            variable++;
        }

        if (user.getHeadUrl() != currentUser.getHeadUrl() && variable == 4) {
            preparedStatement1.setString(1, "headurl");
            preparedStatement1.setString(2, user.getHeadUrl());
            variable++;
        } else if (user.getHeadUrl() != currentUser.getHeadUrl() && variable == 3) {
            preparedStatement1.setString(1, "headurl");
            preparedStatement1.setString(2, user.getHeadUrl());
            variable++;
        } else if (user.getHeadUrl() != currentUser.getHeadUrl() && variable == 2) {
            preparedStatement1.setString(1, "headurl");
            preparedStatement1.setString(2, user.getHeadUrl());
            variable++;
        } else if (user.getHeadUrl() != currentUser.getHeadUrl() && variable == 1) {
            preparedStatement1.setString(1, "headurl");
            preparedStatement1.setString(2, user.getHeadUrl());
            variable++;
        } else if (user.getHeadUrl() != currentUser.getHeadUrl() && variable == 0) {
            preparedStatement1.setString(1, "headurl");
            preparedStatement1.setString(2, user.getHeadUrl());
            variable++;
        }

        /*===============  id设值  ===============*/
        preparedStatement1.setInt((variable * 2 + 1), id);

        int rows = preparedStatement.executeUpdate();
        if (rows != 0) {
            preparedStatement.close();
            connection.close();
            return 1;
        } else {
            preparedStatement.close();
            connection.close();
            return 0;
        }

    }


}
