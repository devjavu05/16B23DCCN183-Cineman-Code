<%@ page contentType="text/html;charset=UTF-8" %>
<% String pageTitle = "Trang chính khách hàng"; %>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<h1>Xin chào quý khách</h1>
<p class="sub">Chọn chức năng bạn muốn sử dụng.</p>
<div class="grid">
  <a class="tile" id="subRegister" href="<%= ctx %>/customer/register"><h3>Đăng kí thành viên</h3><p>Nhận thẻ thành viên và tích điểm khi mua vé.</p></a>
  <div class="tile off"><h3>Tìm kiếm phim</h3><p>Xem thông tin phim và lịch chiếu.</p><span class="tag">Ngoài phạm vi 2 module</span></div>
  <div class="tile off"><h3>Mua vé trực tuyến</h3><p>Chọn suất chiếu, ghế, combo và thanh toán.</p><span class="tag">Ngoài phạm vi 2 module</span></div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
