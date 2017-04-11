package servlet;

import beans.User;
import service.RegisterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 列表页面初始化和查询控制
 */
@WebServlet(name = "RegisterServlet", urlPatterns = "/servlet/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        // 接受页面的值
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int age = Integer.parseInt(request.getParameter("age"));
        int gender = Integer.parseInt(request.getParameter("gender"));
//        int key = Integer.parseInt(request.getParameter("key"));
        String headurl = request.getParameter("headurl");
        //根据逻辑来判断是管理员还是普通用户  ——————————————————————

        // 传递数据
        RegisterService registerService = new RegisterService();

        User user = registerService.insertUserByUser(username, password, age, gender, headurl);
        if (user != null) {
            // 插入数据并传给页面
            request.setAttribute("user", user);
            // 跳转页面
            try {
                request.getRequestDispatcher("../WEB-INF/jsp/login.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            String message = "注册失败";
            request.setAttribute("message", message);
            try {
                request.getRequestDispatcher("../WEB-INF/jsp/login.jsp").forward(request, response);
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