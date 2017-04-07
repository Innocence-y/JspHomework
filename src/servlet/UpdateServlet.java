package servlet;


import beans.User;
import service.UpdateService;

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

        int variable = 0;
        UpdateService updateService = new UpdateService();
        try {
            variable = updateService.UpdateUserById(id, username, password, age, gender, key, headurl);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 向页面跳转
        if (variable == 1) {
            String message = "modify successfully！";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
        } else {
            String message = "modify Failure！";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
}