<%@ page contentType="text/html;charset=UTF-8" import="java.sql.Date,java.util.List,model.*" %>
<%
String pageTitle = "Hóa đơn bán vé của suất chiếu";
String outError = request.getAttribute("outError") == null ? "" : (String) request.getAttribute("outError");
@SuppressWarnings("unchecked") List<InvoiceStat3> invoiceStats = (List<InvoiceStat3>) request.getAttribute("invoiceStats");
int showtimeId = (Integer) request.getAttribute("showtimeId"), movieId = (Integer) request.getAttribute("movieId"), branchId = (Integer) request.getAttribute("branchId");
Date fromDate = (Date) request.getAttribute("fromDate"), toDate = (Date) request.getAttribute("toDate");
double total = request.getAttribute("total") == null ? 0 : (Double) request.getAttribute("total");
int totalTickets = request.getAttribute("totalTickets") == null ? 0 : (Integer) request.getAttribute("totalTickets");
String back = request.getContextPath() + "/manager/showtimes?movieId=" + movieId + "&branchId=" + branchId + "&from=" + fromDate + "&to=" + toDate;
%>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="crumbs"><a href="<%= ctx %>/manager/home">Trang chính</a> › <a href="<%= ctx %>/manager/movies?branchId=<%= branchId %>&from=<%= fromDate %>&to=<%= toDate %>">Thống kê phim</a> › <a href="<%= back %>">Suất chiếu</a> › Hóa đơn</div>
<h1>Hóa đơn bán vé của suất chiếu <%= Html3.code("SC", showtimeId) %></h1>
<p class="sub">Các hóa đơn đã thanh toán có vé của suất chiếu này.</p>
<% if (!outError.isEmpty()) { %><div class="msg err" id="outError"><%= Html3.esc(outError) %></div>
<% } else if (invoiceStats.isEmpty()) { %><div class="msg info">Suất chiếu này chưa có hóa đơn bán vé nào.</div>
<% } else { %>
<div class="summary"><div class="pill">Số hóa đơn <b><%= invoiceStats.size() %></b></div><div class="pill">Số vé <b><%= totalTickets %></b></div><div class="pill">Tổng tiền vé <b><%= Html3.money(total) %></b></div></div>
<table id="outInvoiceList">
  <thead><tr><th>Mã hóa đơn</th><th>Ngày lập</th><th>Khách hàng</th><th>Thanh toán</th><th class="num">Số vé</th><th class="num">Tiền vé</th></tr></thead>
  <tbody>
  <% for (InvoiceStat3 s : invoiceStats) { Invoice3 inv = s.getInvoice(); %>
    <tr><td><%= Html3.esc(inv.getInvoiceCode()) %></td><td><%= new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(inv.getCreatedAt()) %></td>
      <td><%= inv.getCustomer() == null ? "Khách vãng lai" : Html3.esc(inv.getCustomer().getFullName()) %></td><td><%= Html3.esc(inv.getPaymentMethod().getName()) %></td>
      <td class="num"><%= s.getTicketCount() %></td><td class="num"><%= Html3.money(s.getTotalAmount()) %></td></tr>
  <% } %>
  </tbody>
  <tfoot><tr><td colspan="4">Tổng cộng</td><td class="num"><%= totalTickets %></td><td class="num"><%= Html3.money(total) %></td></tr></tfoot>
</table>
<% } %>
<div class="actions"><a class="btn ghost" href="<%= back %>">Quay lại danh sách suất chiếu</a></div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
