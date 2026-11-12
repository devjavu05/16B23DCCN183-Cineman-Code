<%@ page contentType="text/html;charset=UTF-8" import="model.Customer3" %>
<%
String pageTitle = "Đăng kí thành viên";
Customer3 formData = (Customer3) request.getAttribute("formData");
String outError = request.getAttribute("outError") == null ? "" : (String) request.getAttribute("outError");
%>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="crumbs"><a href="<%= ctx %>/customer/home">Trang chính</a> › Đăng kí thành viên</div>
<h1>Đăng kí thành viên</h1>
<p class="sub">Điền thông tin cá nhân để nhận thẻ thành viên CineMan.</p>
<div class="card narrow">
<% if (!outError.isEmpty()) { %><div class="msg err" id="outError"><%= Html3.esc(outError) %></div><% } %>
<form method="post" action="<%= ctx %>/customer/register" autocomplete="off">
  <label>Họ tên <span class="req">*</span></label>
  <input type="text" name="inFullName" value="<%= Html3.esc(formData.getFullName()) %>">
  <div class="row2">
    <div><label>Ngày sinh <span class="req">*</span></label>
    <input type="date" name="inBirthDate" value="<%= formData.getBirthDate() == null ? "" : formData.getBirthDate() %>"></div>
    <div><label>Số điện thoại <span class="req">*</span></label>
    <input type="text" name="inPhone" value="<%= Html3.esc(formData.getPhone()) %>" placeholder="0xxxxxxxxx"></div>
  </div>
  <label>Giới tính <span class="req">*</span></label>
  <select name="inGender">
    <option value="">-- Chọn --</option>
    <% for (String g : new String[]{"Nam", "Nữ", "Khác"}) { %><option value="<%= g %>"<%= g.equals(formData.getGender()) ? " selected" : "" %>><%= g %></option><% } %>
  </select>
  <label>Email <span class="req">*</span></label>
  <input type="email" name="inEmail" value="<%= Html3.esc(formData.getEmail()) %>" placeholder="ten@example.com">
  <label>Địa chỉ</label>
  <input type="text" name="inAddress" value="<%= Html3.esc(formData.getAddress()) %>">
  <label>Mật khẩu <span class="req">*</span></label>
  <input type="password" name="inPassword" placeholder="Ít nhất 8 ký tự">
  <div class="actions"><button class="btn" type="submit" name="subRegister">Đăng kí</button><a class="btn ghost" href="<%= ctx %>/customer/home">Hủy</a></div>
</form>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
