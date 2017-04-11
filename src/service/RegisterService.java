package service;

import beans.User;
import dao.UserDao;

import java.util.Random;

/**
 * 注册用户的业务功能
 */
public class RegisterService {

    public User insertUserByUser(String username, String password, int age, int gender, String headurl) {
        if ((username != null && !"".equals(username.trim())) && (password != null && !"".equals(password.trim())) && (!"".equals(age)) &&
                (!"".equals(gender))) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setAge(age);
            user.setGender(gender);
            if (headurl == null) {
                Random random = new Random();
                user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", random.nextInt(1000)));
            }
            user.setHeadUrl(headurl);
            UserDao userDao = new UserDao();
            return userDao.insertUserByUser(user);
        } else return null;
    }

}
