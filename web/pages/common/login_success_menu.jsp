<%--
  Created by IntelliJ IDEA.
  User: 刘涛
  Date: 2024/8/28
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <c:if test="${not empty sessionScope.user}">
        <span>欢迎<span class="um_span">${sessionScope.user.username}<span>光临尚硅谷书城</span>
    </c:if>
    <a href="pages/order/order.jsp">我的订单</a>
    <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="index.jsp">返回</a>
</div>
