package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by JiYongGuang on 2017/3/8.
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = {"/*"}, initParams = {
        @WebInitParam(name = "charset", value = "UTF-8")
})
public class LoginFilter implements Filter {
    private FilterConfig config;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        HttpServletResponse response = (HttpServletResponse) resp;
        String charset = config.getInitParameter("charset");
        if (charset == null) {
            charset = "UTF-8";
        }
        request.setCharacterEncoding(charset);

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

}
