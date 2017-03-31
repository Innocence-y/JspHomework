package service;


import beans.User;
import dao.UserDao;

import java.sql.SQLException;

/**
 * 查询用户登录功能
 */
public class LoginService {

    public User doLogin(String username, String password) throws SQLException, ClassNotFoundException {
        if ( (username != null && !"".equals(username.trim())) && (password != null && !"".equals(password.trim())) ){
            // 组织用户对象
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            // 根据条件查询用户
            UserDao userDao = new UserDao();
            // 返回查询结果
            return userDao.queryUser(user);
        }else return null;
    }

}
