package controller;

import dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import model.*;

/** Module 2 - Quan li xem thong ke phim theo doanh thu (da qua AuthFilter3). */
@WebServlet({"/manager/home", "/manager/report", "/manager/select", "/manager/movies", "/manager/showtimes", "/manager/invoices"})
public class ManagerServlet3 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String V = "/WEB-INF/view/manager/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        switch (req.getServletPath()) {
            case "/manager/report": forward(req, resp, "ReportMenuPage3.jsp"); break;
            case "/manager/select": showSelect(req, resp); break;
            case "/manager/movies": showMovies(req, resp); break;
            case "/manager/showtimes": showShowtimes(req, resp); break;
            case "/manager/invoices": showInvoices(req, resp); break;
            default: forward(req, resp, "ManagerMainPage3.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if ("/manager/select".equals(req.getServletPath())) submitSelect(req, resp);
        else resp.sendError(405);
    }

    private void forward(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        req.getRequestDispatcher(V + jsp).forward(req, resp);
    }

    private static String p(HttpServletRequest r, String n, String def) {
        return r.getParameter(n) == null ? def : r.getParameter(n);
    }

    private void showSelect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try { req.setAttribute("branches", new BranchDAO3().getAllBranches()); }
        catch (IllegalStateException ex) { req.setAttribute("outError", "Lỗi truy vấn cơ sở dữ liệu, vui lòng thử lại."); }
        req.setAttribute("inFrom", p(req, "inFromDate", ""));
        req.setAttribute("inTo", p(req, "inToDate", ""));
        req.setAttribute("inBranch", p(req, "inBranch", "0"));
        forward(req, resp, "SelectBranchTimePage3.jsp");
    }

    /** Kiem tra khoang thoi gian; hop le thi chuyen sang danh sach phim. */
    private void submitSelect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String inFrom = p(req, "inFromDate", ""), inTo = p(req, "inToDate", ""), inBranch = p(req, "inBranch", "0");
        String outError = "";
        if (inFrom.isEmpty() || inTo.isEmpty()) {
            outError = "Vui lòng chọn đầy đủ khoảng thời gian.";
        } else {
            Date from = null, to = null;
            try { from = Date.valueOf(inFrom); to = Date.valueOf(inTo); }
            catch (IllegalArgumentException ex) { outError = "Định dạng ngày không hợp lệ."; }
            if (outError.isEmpty()) {
                if (from.after(to)) outError = "Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày kết thúc.";
                else if (to.after(new Date(System.currentTimeMillis()))) outError = "Ngày kết thúc không được vượt quá ngày hiện tại.";
            }
        }
        if (outError.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/manager/movies?branchId=" + (inBranch.matches("\\d+") ? inBranch : "0")
                    + "&from=" + inFrom + "&to=" + inTo);
            return;
        }
        req.setAttribute("outError", outError);
        showSelect(req, resp);
    }

    private void showMovies(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int branchId; Date from, to;
        try {
            branchId = Integer.parseInt(req.getParameter("branchId"));
            from = Date.valueOf(req.getParameter("from"));
            to = Date.valueOf(req.getParameter("to"));
        } catch (Exception ex) { resp.sendRedirect(req.getContextPath() + "/manager/select"); return; }
        String branchName = "Toàn hệ thống";
        try {
            for (Branch3 b : new BranchDAO3().getAllBranches()) if (b.getId() == branchId) branchName = b.getName();
            List<MovieStat3> movieStats = new MovieStatDAO3().getRevenueByMovie(branchId, from, to);
            double total = 0; int tickets = 0;
            for (MovieStat3 s : movieStats) { total += s.getRevenue(); tickets += s.getTicketCount(); }
            req.setAttribute("movieStats", movieStats);
            req.setAttribute("total", total);
            req.setAttribute("totalTickets", tickets);
        } catch (IllegalStateException ex) {
            req.setAttribute("outError", "Lỗi truy vấn cơ sở dữ liệu khi lấy dữ liệu thống kê, vui lòng thử lại.");
        }
        req.setAttribute("branchId", branchId); req.setAttribute("branchName", branchName);
        req.setAttribute("fromDate", from); req.setAttribute("toDate", to);
        forward(req, resp, "MovieStatsPage3.jsp");
    }

    private void showShowtimes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int movieId, branchId; Date from, to;
        try {
            movieId = Integer.parseInt(req.getParameter("movieId"));
            branchId = Integer.parseInt(req.getParameter("branchId"));
            from = Date.valueOf(req.getParameter("from"));
            to = Date.valueOf(req.getParameter("to"));
        } catch (Exception ex) { resp.sendRedirect(req.getContextPath() + "/manager/select"); return; }
        String movieTitle = "";
        try {
            List<ShowtimeStat3> showtimeStats = new ShowtimeStatDAO3().getRevenueByShowtime(movieId, branchId, from, to);
            for (MovieStat3 ms : new MovieStatDAO3().getRevenueByMovie(branchId, from, to))
                if (ms.getMovie().getId() == movieId) movieTitle = ms.getMovie().getTitle();
            double total = 0; int tickets = 0;
            for (ShowtimeStat3 s : showtimeStats) { total += s.getRevenue(); tickets += s.getTicketCount(); }
            req.setAttribute("showtimeStats", showtimeStats);
            req.setAttribute("total", total);
            req.setAttribute("totalTickets", tickets);
        } catch (IllegalStateException ex) {
            req.setAttribute("outError", "Lỗi truy vấn cơ sở dữ liệu khi lấy dữ liệu suất chiếu.");
        }
        req.setAttribute("movieId", movieId); req.setAttribute("branchId", branchId); req.setAttribute("movieTitle", movieTitle);
        req.setAttribute("fromDate", from); req.setAttribute("toDate", to);
        forward(req, resp, "ShowtimeStatsPage3.jsp");
    }

    private void showInvoices(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int showtimeId, movieId, branchId; Date from, to;
        try {
            showtimeId = Integer.parseInt(req.getParameter("showtimeId"));
            movieId = Integer.parseInt(req.getParameter("movieId"));
            branchId = Integer.parseInt(req.getParameter("branchId"));
            from = Date.valueOf(req.getParameter("from"));
            to = Date.valueOf(req.getParameter("to"));
        } catch (Exception ex) { resp.sendRedirect(req.getContextPath() + "/manager/select"); return; }
        try {
            List<InvoiceStat3> invoiceStats = new InvoiceStatDAO3().getInvoicesByShowtime(showtimeId);
            double total = 0; int tickets = 0;
            for (InvoiceStat3 s : invoiceStats) { total += s.getTotalAmount(); tickets += s.getTicketCount(); }
            req.setAttribute("invoiceStats", invoiceStats);
            req.setAttribute("total", total);
            req.setAttribute("totalTickets", tickets);
        } catch (IllegalStateException ex) {
            req.setAttribute("outError", "Lỗi truy vấn cơ sở dữ liệu khi lấy dữ liệu hóa đơn.");
        }
        req.setAttribute("showtimeId", showtimeId); req.setAttribute("movieId", movieId); req.setAttribute("branchId", branchId);
        req.setAttribute("fromDate", from); req.setAttribute("toDate", to);
        forward(req, resp, "InvoiceStatsPage3.jsp");
    }
}
