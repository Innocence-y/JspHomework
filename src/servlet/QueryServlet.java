package servlet;

import beans.User;
import service.LoginService;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by JiYongGuang on 2017/2/27.
 */

@WebServlet(name = "QueryServlet", urlPatterns = "/servlet/QueryServlet")
public class QueryServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        LoginService loginService = new LoginService();
        try {
            User user = loginService.doLogin(username, password);
            if (user != null) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                String message = "请先注册";
                request.setAttribute("message", message);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        this.doPost(request, response);
    }
}
