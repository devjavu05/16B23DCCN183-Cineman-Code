<%@ page contentType="text/html;charset=UTF-8" import="java.util.List,model.Branch3" %>
<%
String pageTitle = "Chọn chi nhánh và thời gian";
String outError = request.getAttribute("outError") == null ? "" : (String) request.getAttribute("outError");
String inFrom = (String) request.getAttribute("inFrom"), inTo = (String) request.getAttribute("inTo"), inBranch = (String) request.getAttribute("inBranch");
@SuppressWarnings("unchecked") List<Branch3> branches = (List<Branch3>) request.getAttribute("branches");
%>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="crumbs"><a href="<%= ctx %>/manager/home">Trang chính</a> › <a href="<%= ctx %>/manager/report">Xem báo cáo</a> › Thống kê phim theo doanh thu</div>
<h1>Thống kê phim theo doanh thu</h1>
<p class="sub">Chọn chi nhánh rạp và khoảng ngày chiếu cần thống kê.</p>
<div class="card narrow">
<% if (!outError.isEmpty()) { %><div class="msg err" id="outError"><%= Html3.esc(outError) %></div><% } %>
<form method="post" action="<%= ctx %>/manager/select">
  <label>Chi nhánh rạp</label>
  <select name="inBranch">
    <option value="0">Toàn hệ thống</option>
    <% if (branches != null) for (Branch3 b : branches) { %>
    <option value="<%= b.getId() %>" <%= inBranch.equals(String.valueOf(b.getId())) ? "selected" : "" %>><%= Html3.esc(b.getName()) %></option>
    <% } %>
  </select>
  <div class="row2">
    <div><label>Từ ngày <span class="req">*</span></label><input type="date" name="inFromDate" value="<%= Html3.esc(inFrom) %>"></div>
    <div><label>Đến ngày <span class="req">*</span></label><input type="date" name="inToDate" value="<%= Html3.esc(inTo) %>"></div>
  </div>
  <div class="actions"><button class="btn" type="submit" name="subView">Xem thống kê</button><a class="btn ghost" href="<%= ctx %>/manager/report">Quay lại</a></div>
</form>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
