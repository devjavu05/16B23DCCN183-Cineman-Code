<%@ page contentType="text/html;charset=UTF-8" %>
<% String pageTitle = "Trang chủ"; %>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<h1>Hệ thống quản lí rạp chiếu phim CineMan</h1>
<p class="sub">Chọn cổng truy cập để thử hai module của đề 16.</p>
<div class="grid">
  <a class="tile" href="<%= ctx %>/customer/home"><h3>Cổng khách hàng</h3><p>Module 1: Khách hàng đăng kí thành viên.</p></a>
  <a class="tile" href="<%= ctx %>/login"><h3>Cổng nhân viên quản lí</h3><p>Đăng nhập, sau đó dùng Module 2: Xem thống kê phim theo doanh thu.</p></a>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
