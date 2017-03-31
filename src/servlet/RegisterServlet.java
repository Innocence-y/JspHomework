package servlet;

import beans.User;
import service.RegisterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

/**
 * 列表页面初始化和查询控制
 */
@WebServlet(name = "RegisterServlet", urlPatterns = "/servlet/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 接受页面的值
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int age = Integer.parseInt(request.getParameter("age"));
        int gender = Integer.parseInt(request.getParameter("gender"));
        String headurl = request.getParameter("headurl");
        //根据逻辑来判断是管理员还是普通用户  ——————————————————————

        // 传递数据
        RegisterService registerService = new RegisterService();

        try {
            User user = registerService.insertUser(username, password, age, gender, headurl);
            if (user != null) {
                // 插入数据并传给页面
                request.setAttribute("user", user);
                // 跳转页面
                request.getRequestDispatcher("../WEB-INF/jsp/login.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("../WEB-INF/jsp/fail.jsp").forward(request, response);
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