package servlet;


import beans.User;
import service.UpdateService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * 列表页面初始化和查询控制
 */
@WebServlet(name = "UpdateServlet", urlPatterns = "/servlet/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 接受页面的值
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String age = request.getParameter("age");
        String gender = request.getParameter("gender");
        String key = request.getParameter("key");
        String headurl = request.getParameter("headurl");

        UpdateService updateService = new UpdateService();
        User user = updateService.UpdateUserById(id, username, password, age, gender, key, headurl);

        // 向页面传参并跳转
        request.setAttribute("user",user);
        request.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
}