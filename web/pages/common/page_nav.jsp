<%--
  Created by IntelliJ IDEA.
  User: 刘涛
  Date: 2024/9/5
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="page_nav">
    <a href="${requestScope.page.url}&pageNo=1">首页</a>
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>
    【${requestScope.page.pageNo}】
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
    </c:if>
    <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录 到第
    <input value="${param.pageNo}" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">

</div>