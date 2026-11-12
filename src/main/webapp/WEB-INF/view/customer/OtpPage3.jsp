<%@ page contentType="text/html;charset=UTF-8" import="model.*" %>
<%
String pageTitle = "Xác thực OTP";
Customer3 formData = (Customer3) session.getAttribute("formData");
String outError = request.getAttribute("outError") == null ? "" : (String) request.getAttribute("outError");
String outInfo = request.getAttribute("outInfo") == null ? "" : (String) request.getAttribute("outInfo");
String sentOtpShow = (String) session.getAttribute("sentOtp");
%>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="crumbs"><a href="<%= ctx %>/customer/home">Trang chính</a> › <a href="<%= ctx %>/customer/register">Đăng kí thành viên</a> › Xác thực OTP</div>
<h1>Xác thực OTP</h1>
<p class="sub">Mã gồm 6 chữ số đã được gửi tới <b><%= Html3.esc(formData.getEmail()) %></b>. Mã có hiệu lực 5 phút.</p>
<div class="card narrow">
<% if (!outError.isEmpty()) { %><div class="msg err" id="outError"><%= Html3.esc(outError) %></div><% } %>
<% if (!outInfo.isEmpty()) { %><div class="msg ok"><%= Html3.esc(outInfo) %></div><% } %>
<div class="msg info">Bản demo: hệ thống không gửi email thật, mã OTP hiện tại là <b><%= Html3.esc(sentOtpShow) %></b>.</div>
<form method="post" action="<%= ctx %>/customer/otp" autocomplete="off">
  <label>Mã OTP <span class="req">*</span></label>
  <input type="text" name="inOtp" maxlength="6" inputmode="numeric" placeholder="______" autofocus>
  <div class="actions">
    <button class="btn" type="submit" name="action" value="verify">Xác nhận</button>
    <button class="btn ghost" type="submit" name="action" value="resend" formnovalidate>Gửi lại mã</button>
  </div>
</form>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
