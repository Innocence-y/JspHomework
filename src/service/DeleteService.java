package service;

import dao.UserDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 删除相关的业务功能
 */
public class DeleteService {

    /**
     * 单条删除
     */
    public void deleteOne(String id) throws SQLException, ClassNotFoundException {
        if (id != null && !"".equals(id.trim())) {
            UserDao userDao = new UserDao();
            userDao.deleteOne(Integer.valueOf(id));
        }
    }

    /**
     * 批量删除
     */
    public void deleteBatch(String[] ids) throws SQLException, ClassNotFoundException {
        if (ids != null && ids.length != 0) {
            UserDao userDao = new UserDao();
            List<Integer> idList = new ArrayList<Integer>();
            for (String id : ids) {
                idList.add(Integer.valueOf(id));
            }
            userDao.deleteBatch(idList);
        }
    }

}
