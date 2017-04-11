package servlet;

import service.DeleteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 批量删除控制层
 */
@WebServlet(name = "DeleteBatchServlet", urlPatterns = "/servlet/DeleteBatchServlet")
public class DeleteBatchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        // 接受页面的值
        String[] ids = request.getParameterValues("id");
        DeleteService deleteService = new DeleteService();
        try {
            deleteService.deleteBatchById(ids);
            // 向页面跳转
            request.getRequestDispatcher("../WEB-INF/jsp/list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
}