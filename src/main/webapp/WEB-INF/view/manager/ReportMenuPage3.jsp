<%@ page contentType="text/html;charset=UTF-8" %>
<% String pageTitle = "Chọn loại báo cáo"; %>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="crumbs"><a href="<%= ctx %>/manager/home">Trang chính</a> › Xem báo cáo</div>
<h1>Chọn loại báo cáo</h1>
<p class="sub">Chọn loại thống kê muốn xem.</p>
<div class="grid">
  <a class="tile" id="subMovieRevenue" href="<%= ctx %>/manager/select"><h3>Thống kê phim theo doanh thu</h3><p>Doanh thu từng phim, từng suất chiếu và hóa đơn bán vé.</p></a>
  <div class="tile off"><h3>Thống kê khách hàng</h3><p>Thành viên mới, lượt mua vé.</p><span class="tag">Ngoài phạm vi 2 module</span></div>
  <div class="tile off"><h3>Thống kê doanh thu chi nhánh</h3><p>So sánh các chi nhánh rạp.</p><span class="tag">Ngoài phạm vi 2 module</span></div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
