<%@ page contentType="text/html;charset=UTF-8" %>
<% String pageTitle = "Trang chính quản lí"; %>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<h1>Xin chào, quản lí</h1>
<p class="sub">Chọn chức năng.</p>
<div class="grid">
  <a class="tile" id="subReport" href="<%= ctx %>/manager/report"><h3>Xem báo cáo</h3><p>Các loại thống kê: phim, khách hàng, doanh thu.</p></a>
  <div class="tile off"><h3>Lên lịch chiếu</h3><p>Sắp xếp suất chiếu cho các phòng.</p><span class="tag">Ngoài phạm vi 2 module</span></div>
  <div class="tile off"><h3>Quản lí phim / phòng chiếu</h3><p>Thêm, sửa, xóa thông tin.</p><span class="tag">Ngoài phạm vi 2 module</span></div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
