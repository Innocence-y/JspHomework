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
     *
     * @param user
     * @return
     */
    public User insertUserByUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int row = 0;
        try {
            connection = DbAccess.getConnection();
            String sql = "INSERT INTO USER(name,password,age,gender,headurl) VALUES(?,?,?,?,?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setInt(4, user.getGender());
            preparedStatement.setString(5, user.getHeadUrl());
            row = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (row != 0) {
            return user;
        } else {
            return null;
        }

    }

    /**
     * 登录用户
     *
     * @param user
     * @return
     */
    public User queryUserByC(User user) {
        Connection connection = null;
        try {
            connection = DbAccess.getConnection();
            String sql = "SELECT headurl FROM user WHERE username = '?'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();

            User currentUser = new User();
            if (resultSet != null) {
                while (resultSet.next()) {
                    currentUser.setUsername(resultSet.getString("username"));
                    currentUser.setPassword(resultSet.getString("password"));
                    currentUser.setHeadUrl(resultSet.getString("headurl"));
                }

                if (currentUser.getUsername().equals(user.getUsername()) && currentUser.getPassword().equals(user.getPassword())) {
                    return currentUser;
                }
            } else {
                return null;//没有该用户名
            }


            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 根据条件查询用户列表
     *
     * @param user
     * @param frontage
     * @param backage
     * @return
     */
    public List<User> queryUserListByC(User user, int frontage, int backage) {
        Connection connection = null;
        ArrayList<User> arrayList = null;

        try {
            connection = DbAccess.getConnection();
            String sql = "SELECT username,gender,age,headurl FROM USER WHERE gender = ? AND age BETWEEN ? AND ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getGender());
            preparedStatement.setInt(2, frontage);
            preparedStatement.setInt(3, backage);
            ResultSet resultSet = preparedStatement.executeQuery();

            arrayList = new ArrayList<User>();
            while (resultSet.next()) {
                User currentuser = new User();
                arrayList.add(currentuser);

                currentuser.setUsername(resultSet.getString("username"));
                currentuser.setGender(resultSet.getInt("gender"));
                currentuser.setAge(resultSet.getInt("age"));
                currentuser.setHeadUrl(resultSet.getString("username"));

                resultSet.close();
                preparedStatement.close();
                connection.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    /**
     * 根据id值单条删除用户
     *
     * @param id
     */
    public void deleteOneById(int id) {
        Connection connection = null;
        try {
            connection = DbAccess.getConnection();
            String sql = "DELETE FROM user WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量多条删除用户
     *
     * @param idList
     */
    public void deleteBatchById(List<Integer> idList) {
        Connection connection = null;
        try {
            connection = DbAccess.getConnection();
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
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            int rows = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新用户
     *
     * @param user
     * @param id
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int UpdateUserByUser(User user, int id) {
        Connection connection = null;
        int rows = 0;
        try {
            connection = DbAccess.getConnection();
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

            rows = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (rows != 0) {
            return 1;
        } else {
            return 0;
        }

    }

}
