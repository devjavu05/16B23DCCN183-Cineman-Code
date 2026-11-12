<%@ page contentType="text/html;charset=UTF-8" import="java.sql.Date,java.util.List,model.*" %>
<%
String pageTitle = "Thống kê suất chiếu";
String outError = request.getAttribute("outError") == null ? "" : (String) request.getAttribute("outError");
@SuppressWarnings("unchecked") List<ShowtimeStat3> showtimeStats = (List<ShowtimeStat3>) request.getAttribute("showtimeStats");
int movieId = (Integer) request.getAttribute("movieId"), branchId = (Integer) request.getAttribute("branchId");
String movieTitle = (String) request.getAttribute("movieTitle");
Date fromDate = (Date) request.getAttribute("fromDate"), toDate = (Date) request.getAttribute("toDate");
double total = request.getAttribute("total") == null ? 0 : (Double) request.getAttribute("total");
int totalTickets = request.getAttribute("totalTickets") == null ? 0 : (Integer) request.getAttribute("totalTickets");
String qs = "movieId=" + movieId + "&branchId=" + branchId + "&from=" + fromDate + "&to=" + toDate;
String back = request.getContextPath() + "/manager/movies?branchId=" + branchId + "&from=" + fromDate + "&to=" + toDate;
%>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="crumbs"><a href="<%= ctx %>/manager/home">Trang chính</a> › <a href="<%= back %>">Thống kê phim</a> › Suất chiếu</div>
<h1>Suất chiếu của phim <%= Html3.esc(movieTitle) %> <small style="color:#6b7280;font-weight:400">(<%= Html3.code("MP", movieId) %>)</small></h1>
<p class="sub">Ngày chiếu từ <b><%= fromDate %></b> đến <b><%= toDate %></b>. Bấm vào một suất chiếu để xem các hóa đơn bán vé.</p>
<% if (!outError.isEmpty()) { %><div class="msg err" id="outError"><%= Html3.esc(outError) %></div>
<% } else if (showtimeStats.isEmpty()) { %><div class="msg info">Phim này không có suất chiếu nào trong khoảng thời gian đã chọn.</div>
<% } else { %>
<div class="summary"><div class="pill">Số suất <b><%= showtimeStats.size() %></b></div><div class="pill">Số vé bán <b><%= totalTickets %></b></div><div class="pill">Doanh thu phim <b><%= Html3.money(total) %></b></div></div>
<table id="outShowtimeList">
  <thead><tr><th>Mã suất</th><th>Ngày chiếu</th><th>Giờ</th><th>Phòng</th><th>Lấp đầy</th><th class="num">Số vé</th><th class="num">Doanh thu</th></tr></thead>
  <tbody>
  <% for (ShowtimeStat3 s : showtimeStats) { Showtime3 st = s.getShowtime(); %>
    <tr><td><a class="link" href="<%= ctx %>/manager/invoices?showtimeId=<%= st.getId() %>&<%= qs %>"><%= Html3.code("SC", st.getId()) %></a></td>
      <td><%= st.getShowDate() %></td><td><%= st.getStartTime().toString().substring(0,5) %></td><td><%= Html3.esc(st.getAuditorium().getName()) %></td>
      <td><span class="bar"><i style="width:<%= Math.round(s.getOccupancyRate()) %>%"></i></span><%= Math.round(s.getOccupancyRate()) %>% (<%= s.getTicketCount() %>/<%= st.getAuditorium().getCapacity() %>)</td>
      <td class="num"><%= s.getTicketCount() %></td><td class="num"><%= Html3.money(s.getRevenue()) %></td></tr>
  <% } %>
  </tbody>
  <tfoot><tr><td colspan="5">Tổng cộng</td><td class="num"><%= totalTickets %></td><td class="num"><%= Html3.money(total) %></td></tr></tfoot>
</table>
<% } %>
<div class="actions"><a class="btn ghost" href="<%= back %>">Quay lại danh sách phim</a></div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
