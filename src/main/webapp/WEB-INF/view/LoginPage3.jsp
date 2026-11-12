<%@ page contentType="text/html;charset=UTF-8" %>
<% String pageTitle = "Đăng nhập"; String outError = (String) request.getAttribute("outError");
   String inUsername = request.getAttribute("inUsername") == null ? "" : (String) request.getAttribute("inUsername"); %>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="crumbs"><a href="<%= ctx %>/index.jsp">Trang chủ</a> › Đăng nhập</div>
<h1>Đăng nhập</h1>
<p class="sub">Dành cho nhân viên quản lí và thành viên. (Demo: manager1 / Manager@123)</p>
<div class="card narrow">
<% if (outError != null) { %><div class="msg err" id="outError"><%= Html3.esc(outError) %></div><% } %>
<form method="post" action="<%= ctx %>/login" autocomplete="off">
  <label>Tên đăng nhập <span class="req">*</span></label>
  <input type="text" name="inUsername" value="<%= Html3.esc(inUsername) %>" autofocus>
  <label>Mật khẩu <span class="req">*</span></label>
  <input type="password" name="inPassword">
  <div class="actions"><button class="btn" type="submit" name="subLogin">Đăng nhập</button></div>
</form>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
