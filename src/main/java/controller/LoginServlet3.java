package controller;

import dao.UserDAO3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import model.Manager3;
import model.User3;

/** Dang nhap: Manager3 -> trang quan li; Member3 -> trang khach hang. */
@WebServlet("/login")
public class LoginServlet3 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/LoginPage3.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("inUsername") == null ? "" : req.getParameter("inUsername").trim();
        String password = req.getParameter("inPassword") == null ? "" : req.getParameter("inPassword");
        try {
            User3 u = (username.isEmpty() || password.isEmpty()) ? null : new UserDAO3().login(username, password);
            if (u == null) {
                req.setAttribute("outError", "Sai tên đăng nhập hoặc mật khẩu.");
                req.setAttribute("inUsername", username);
                req.getRequestDispatcher("/WEB-INF/view/LoginPage3.jsp").forward(req, resp);
                return;
            }
            req.getSession().invalidate();
            HttpSession s = req.getSession(true);
            s.setAttribute("user", u);
            resp.sendRedirect(req.getContextPath() + (u instanceof Manager3 ? "/manager/home" : "/customer/home"));
        } catch (IllegalStateException ex) {
            req.setAttribute("outError", "Lỗi hệ thống, vui lòng thử lại sau.");
            req.getRequestDispatcher("/WEB-INF/view/LoginPage3.jsp").forward(req, resp);
        }
    }
}
