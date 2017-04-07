package service;

import beans.User;
import dao.UserDao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 查询相关的业务功能
 */
public class UpdateService {
    public int UpdateUserById(String id, String username, String passowrd, String age, String gender, String key, String headurl) throws SQLException, ClassNotFoundException {
        if (id != null && !"".equals(id.trim()) && username != null && !"".equals(username.trim()) &&
                passowrd != null && !"".equals(passowrd.trim()) && age != null && !"".equals(age.trim()) &&
                gender != null && !"".equals(gender.trim()) && key != null && !"".equals(key.trim()) && headurl != null && !"".equals(headurl.trim())) {
        }
        User user = new User();//id和key在这里是不能动的
        user.setUsername(username);
        user.setPassword(passowrd);
        user.setAge(Integer.parseInt(age));
        user.setGender(Integer.parseInt(gender));
        user.setHeadUrl(headurl);
        UserDao userDao = new UserDao();
        // 根据id值来更改用户信息
        return userDao.UpdateUserByUser(user,Integer.parseInt(id));
    }

}
