package service;

import beans.User;
import dao.UserDao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 注册用户的业务功能
 */
public class RegisterService {

    public User insertUser(String username, String password, int age, int gender, String headurl) throws SQLException, ClassNotFoundException {
        if ( (username != null && !"".equals(username.trim())) && (password != null && !"".equals(password.trim())) && (!"".equals(age)) &&
                (!"".equals(gender)) ) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setAge(age);
            user.setGender(gender);
            user.setHeadUrl(headurl);
            UserDao userDao = new UserDao();
            return userDao.insertUser(user);
        }else return null;
    }

}
