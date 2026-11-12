package controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;
import model.Manager3;

/** Bao ve cac trang cua quan li: chua dang nhap thi chuyen ve /login. */
@WebFilter("/manager/*")
public class AuthFilter3 implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest rq = (HttpServletRequest) req;
        HttpServletResponse rs = (HttpServletResponse) res;
        HttpSession s = rq.getSession(false);
        if (s == null || !(s.getAttribute("user") instanceof Manager3)) {
            rs.sendRedirect(rq.getContextPath() + "/login");
            return;
        }
        chain.doFilter(req, res);
    }
}
