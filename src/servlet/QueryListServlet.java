package servlet;


import beans.User;
import service.QueryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 查询条件查询用户列表
 */
@WebServlet(name = "QueryListServlet", urlPatterns = "/servlet/QueryListServlet")
public class QueryListServlet extends HttpServlet {
    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
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
        String frontGender = request.getParameter("gender");
        int gender = 0;
        if ("男".equals(frontGender)) {
            gender = 0;
        } else if ("女".equals(frontGender))
            gender = 1;

        int frontage = Integer.parseInt(request.getParameter("frontage"));
        int backage = Integer.parseInt(request.getParameter("backage"));
        // 根据条件查询用户列并传给页面
        QueryService listService = new QueryService();

        List<User> userList = listService.queryUserListByC(gender, frontage, backage);
        if (userList != null) {
            //向页面传值
            request.setAttribute("userList", userList);
            //向页面跳转
            try {
                request.getRequestDispatcher("../WEB-INF/jsp/list.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            String message = "查询失败";
            request.setAttribute("message", message);
            try {
                request.getRequestDispatcher("../WEB-INF/jsp/list.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
}
