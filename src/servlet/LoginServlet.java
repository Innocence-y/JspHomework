package servlet;

import beans.User;
import service.LoginService;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * Created by JiYongGuang on 2017/2/27.
 */

@WebServlet(name = "LoginServlet", urlPatterns = "/servlet/LoginServlet")
public class LoginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        LoginService loginService = new LoginService();
        User user = loginService.doLoginByC(username, password);
        if (user != null) {
            request.setAttribute("user", user);
            try {
                request.getRequestDispatcher("../WEB-INF/jsp/login.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            String message = "用户或者密码不正确";
            request.setAttribute("message", message);
            try {
                request.getRequestDispatcher("../WEB-INF/jsp/login.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        this.doPost(request, response);
    }
}
