package controller;

import dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import model.*;

/** Module 1 - Khach hang dang ki thanh vien: /customer/home, /register, /otp, /result. */
@WebServlet({"/customer/home", "/customer/register", "/customer/otp", "/customer/result"})
public class CustomerServlet3 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String V = "/WEB-INF/view/customer/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        HttpSession session = req.getSession();
        switch (path) {
            case "/customer/register":
                req.setAttribute("formData", new Customer3());
                req.getRequestDispatcher(V + "RegisterFormPage3.jsp").forward(req, resp);
                break;
            case "/customer/otp":
                if (session.getAttribute("formData") == null) { resp.sendRedirect(req.getContextPath() + "/customer/register"); return; }
                req.getRequestDispatcher(V + "OtpPage3.jsp").forward(req, resp);
                break;
            case "/customer/result":
                if (session.getAttribute("newCard") == null || session.getAttribute("formData") == null) {
                    resp.sendRedirect(req.getContextPath() + "/customer/register"); return;
                }
                req.getRequestDispatcher(V + "RegisterResultPage3.jsp").forward(req, resp);
                session.removeAttribute("formData");
                session.removeAttribute("newCard");
                break;
            default:
                req.getRequestDispatcher(V + "CustomerMainPage3.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if ("/customer/register".equals(req.getServletPath())) submitRegisterForm(req, resp);
        else if ("/customer/otp".equals(req.getServletPath())) submitOtp(req, resp);
        else resp.sendError(405);
    }

    private static String p(HttpServletRequest r, String n) {
        return r.getParameter(n) == null ? "" : r.getParameter(n).trim();
    }

    /** Nhan form dang ki: kiem tra thong tin, gui OTP. */
    private void submitRegisterForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer3 formData = new Customer3();
        formData.setFullName(p(req, "inFullName"));
        formData.setPhone(p(req, "inPhone"));
        formData.setEmail(p(req, "inEmail"));
        formData.setGender(p(req, "inGender"));
        formData.setAddress(p(req, "inAddress"));
        String password = req.getParameter("inPassword") == null ? "" : req.getParameter("inPassword");
        String outError = "";
        try {
            if (!p(req, "inBirthDate").isEmpty()) formData.setBirthDate(Date.valueOf(p(req, "inBirthDate")));
        } catch (IllegalArgumentException ex) { outError = "Ngày sinh không đúng định dạng."; }
        if (outError.isEmpty()) {
            try {
                CustomerDAO3 dao = new CustomerDAO3();
                outError = dao.validateInfo(formData, password);
                if (outError.isEmpty()) {
                    String sentOtp = dao.sendOtp(formData.getEmail());
                    long otpExpire = dao.getOtpExpireAt();
                    HttpSession session = req.getSession();
                    session.setAttribute("formData", formData);
                    session.setAttribute("regPassword", password);
                    session.setAttribute("sentOtp", sentOtp);
                    session.setAttribute("otpExpire", otpExpire);
                    session.setAttribute("otpTries", 0);
                    resp.sendRedirect(req.getContextPath() + "/customer/otp");
                    return;
                }
            } catch (IllegalStateException ex) { outError = "Lỗi hệ thống, vui lòng thử lại sau."; }
        }
        req.setAttribute("formData", formData);
        req.setAttribute("outError", outError);
        req.getRequestDispatcher(V + "RegisterFormPage3.jsp").forward(req, resp);
    }

    /** Xac thuc OTP (verify) hoac gui lai ma (resend); dung thi luu Member3 va cap the. */
    private void submitOtp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Customer3 formData = (Customer3) session.getAttribute("formData");
        if (formData == null) { resp.sendRedirect(req.getContextPath() + "/customer/register"); return; }
        String outError = "", outInfo = "";
        try {
            if ("resend".equals(req.getParameter("action"))) {
                CustomerDAO3 dao = new CustomerDAO3();
                session.setAttribute("sentOtp", dao.sendOtp(formData.getEmail()));
                session.setAttribute("otpExpire", dao.getOtpExpireAt());
                session.setAttribute("otpTries", 0);
                outInfo = "Đã gửi lại mã OTP mới.";
            } else {
                String inOtp = p(req, "inOtp");
                String sentOtp = (String) session.getAttribute("sentOtp");
                long otpExpire = (Long) session.getAttribute("otpExpire");
                int tries = (Integer) session.getAttribute("otpTries");
                if (System.currentTimeMillis() > otpExpire) {
                    outError = "Mã OTP đã hết hạn, vui lòng bấm “Gửi lại mã”.";
                } else if (tries >= 5) {
                    outError = "Bạn đã nhập sai quá 5 lần, vui lòng bấm “Gửi lại mã”.";
                } else if (!inOtp.equals(sentOtp)) {
                    session.setAttribute("otpTries", tries + 1);
                    outError = "Mã OTP không đúng, vui lòng thử lại (" + (tries + 1) + "/5).";
                } else {
                    Member3 pendingMember = new Member3();
                    pendingMember.setUsername(formData.getEmail());
                    pendingMember.setPassword((String) session.getAttribute("regPassword"));
                    pendingMember.setStatus("ACTIVE");
                    pendingMember.setCustomer(formData);
                    pendingMember.setLoyaltyPoints(0);
                    if (!new MemberDAO3().register(pendingMember)) {
                        outError = "Không thể lưu thông tin (có thể email/số điện thoại vừa được đăng kí). Vui lòng thử lại.";
                    } else {
                        MembershipCard3 card = new MembershipCardDAO3().issueCard(formData);
                        if (card == null) {
                            outError = "Đã lưu tài khoản nhưng chưa cấp được thẻ, vui lòng liên hệ quầy.";
                        } else {
                            session.setAttribute("newCard", card);
                            session.removeAttribute("regPassword");
                            session.removeAttribute("sentOtp");
                            resp.sendRedirect(req.getContextPath() + "/customer/result");
                            return;
                        }
                    }
                }
            }
        } catch (IllegalStateException ex) { outError = "Lỗi hệ thống, vui lòng thử lại sau."; }
        req.setAttribute("outError", outError);
        req.setAttribute("outInfo", outInfo);
        req.getRequestDispatcher(V + "OtpPage3.jsp").forward(req, resp);
    }
}
