package servlet;

import service.DeleteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 批量删除控制层
 */
@WebServlet(name = "DeleteBatchServlet", urlPatterns = "/servlet/DeleteBatchServlet")
public class DeleteBatchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 接受页面的值
        String[] ids = request.getParameterValues("id");
        DeleteService deleteService = new DeleteService();
        try {
            deleteService.deleteBatch(ids);
            // 向页面跳转
            request.getRequestDispatcher("/List.action").forward(request, response);
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