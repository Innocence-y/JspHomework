package servlet;


import beans.User;
import service.QueryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 查询已注册所有用户列表
 */
@WebServlet(name = "QueryListServlet", urlPatterns = "/servlet/QueryListServlet")
public class QueryListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*  创建分页对象
        Page page = new Page();
		Pattern pattern = Pattern.compile("[0-9]{1,9}");
		if(currentPage == null ||  !pattern.matcher(currentPage).matches()) {
			page.setCurrentPage(1);
		} else {
			page.setCurrentPage(Integer.valueOf(currentPage));
		}
		 */
        //接收页面的值
        int gender = Integer.parseInt(request.getParameter("gender"));
        int frontage = Integer.parseInt(request.getParameter("frontage"));
        int backage = Integer.parseInt(request.getParameter("backage"));
        // 根据条件查询用户列并传给页面
        QueryService listService = new QueryService();
        try {
            List<User> userList = listService.queryUserList(gender, frontage, backage);
            if (userList!=null){
                //向页面传值
                request.setAttribute("userList", userList);
                //向页面跳转
                request.getRequestDispatcher("../WEB-INF/jsp/login.jsp").forward(request, response);
            }else {
                request.getRequestDispatcher("../WEB-INF/jsp/fail.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
}
