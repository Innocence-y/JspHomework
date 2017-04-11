package service;

import dao.UserDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 删除相关的业务功能
 */
public class DeleteService {

    /**
     * 单条删除
     */
    public void deleteOneById(String id) {
        if (id != null && !"".equals(id.trim())) {
            UserDao userDao = new UserDao();
            userDao.deleteOneById(Integer.valueOf(id));
        }
    }

    /**
     * 批量删除
     */
    public void deleteBatchById(String[] ids) {
        if (ids != null && ids.length != 0) {
            UserDao userDao = new UserDao();
            List<Integer> idList = new ArrayList<Integer>();
            for (String id : ids) {
                idList.add(Integer.valueOf(id));
            }
            userDao.deleteBatchById(idList);
        }
    }

}
