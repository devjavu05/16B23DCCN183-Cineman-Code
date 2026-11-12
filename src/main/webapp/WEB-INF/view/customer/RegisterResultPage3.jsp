<%@ page contentType="text/html;charset=UTF-8" import="model.*" %>
<%
String pageTitle = "Đăng kí thành công";
MembershipCard3 card = (MembershipCard3) session.getAttribute("newCard");
Customer3 formData = (Customer3) session.getAttribute("formData");
%>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<h1>Đăng kí thành viên thành công</h1>
<p class="sub">Chào mừng <b><%= Html3.esc(formData.getFullName()) %></b> đã trở thành thành viên CineMan.</p>
<div class="msg ok">Đăng kí thành viên thành công. Mã thẻ của bạn: <b><%= Html3.esc(card.getCardNumber()) %></b></div>
<div class="cardmember">
  <small>THẺ THÀNH VIÊN CINEMAN</small>
  <div class="n"><%= Html3.esc(card.getCardNumber()) %></div>
  <div><b><%= Html3.esc(formData.getFullName()) %></b></div>
  <small>Hạng <%= Html3.esc(card.getTier().getTierName()) %> · Ngày cấp <%= card.getIssueDate() %> · <%= Html3.esc(card.getCardStatus()) %></small>
</div>
<div class="actions"><a class="btn" id="subHome" href="<%= ctx %>/customer/home">Về trang chính</a></div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
