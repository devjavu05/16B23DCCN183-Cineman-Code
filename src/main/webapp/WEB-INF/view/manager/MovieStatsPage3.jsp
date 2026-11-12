<%@ page contentType="text/html;charset=UTF-8" import="java.sql.Date,java.util.List,model.*" %>
<%
String pageTitle = "Thống kê phim theo doanh thu";
String outError = request.getAttribute("outError") == null ? "" : (String) request.getAttribute("outError");
@SuppressWarnings("unchecked") List<MovieStat3> movieStats = (List<MovieStat3>) request.getAttribute("movieStats");
int branchId = (Integer) request.getAttribute("branchId"); String branchName = (String) request.getAttribute("branchName");
Date fromDate = (Date) request.getAttribute("fromDate"), toDate = (Date) request.getAttribute("toDate");
double total = request.getAttribute("total") == null ? 0 : (Double) request.getAttribute("total");
int totalTickets = request.getAttribute("totalTickets") == null ? 0 : (Integer) request.getAttribute("totalTickets");
String qs = "branchId=" + branchId + "&from=" + fromDate + "&to=" + toDate;
%>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="crumbs"><a href="<%= ctx %>/manager/home">Trang chính</a> › <a href="<%= ctx %>/manager/report">Xem báo cáo</a> › <a href="<%= ctx %>/manager/select">Chọn thời gian</a> › Thống kê phim</div>
<h1>Thống kê doanh thu theo phim</h1>
<p class="sub"><%= Html3.esc(branchName) %> · ngày chiếu từ <b><%= fromDate %></b> đến <b><%= toDate %></b>. Bấm vào một phim để xem các suất chiếu.</p>
<% if (!outError.isEmpty()) { %><div class="msg err" id="outError"><%= Html3.esc(outError) %></div>
<% } else if (movieStats.isEmpty()) { %><div class="msg info">Không có dữ liệu phim trong khoảng thời gian này.</div>
<% } else { %>
<div class="summary"><div class="pill">Số phim <b><%= movieStats.size() %></b></div><div class="pill">Số vé bán <b><%= totalTickets %></b></div><div class="pill">Tổng doanh thu <b><%= Html3.money(total) %></b></div></div>
<table id="outMovieList">
  <thead><tr><th>#</th><th>Mã phim</th><th>Tên phim</th><th>Thể loại</th><th class="num">Số vé</th><th class="num">Doanh thu</th></tr></thead>
  <tbody>
  <% int rank = 1; for (MovieStat3 s : movieStats) { Movie3 m = s.getMovie(); %>
    <tr><td><%= rank++ %></td><td><%= Html3.code("MP", m.getId()) %></td>
      <td><a class="link" href="<%= ctx %>/manager/showtimes?movieId=<%= m.getId() %>&<%= qs %>"><%= Html3.esc(m.getTitle()) %></a></td>
      <td><%= Html3.esc(m.getGenre()) %></td><td class="num"><%= s.getTicketCount() %></td><td class="num"><%= Html3.money(s.getRevenue()) %></td></tr>
  <% } %>
  </tbody>
  <tfoot><tr><td colspan="4">Tổng cộng</td><td class="num"><%= totalTickets %></td><td class="num"><%= Html3.money(total) %></td></tr></tfoot>
</table>
<% } %>
<div class="actions"><a class="btn ghost" href="<%= ctx %>/manager/select">Chọn lại thời gian</a></div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
