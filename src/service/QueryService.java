package service;


import beans.User;
import dao.UserDao;

import java.sql.SQLException;
import java.util.*;

/**
 * 查询相关的业务功能
 */
public class QueryService {

    public List<User> queryUserList(int gender, int frontage, int backage) throws SQLException, ClassNotFoundException {
        if (!"".equals(gender) && !"".equals(frontage) && !"".equals(backage)){
            // 组织消息对象
            User user = new User();
            user.setGender(gender);
            // 根据条件查询条数
            UserDao userDao = new UserDao();
            // 查询并返回结果
            return userDao.queryUserList(user, frontage, backage);
        }else return null;
    }

}