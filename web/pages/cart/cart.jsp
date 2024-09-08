<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<%@include file="/pages/common/head.jsp"%>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%@ include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:if test="${not empty sessionScope.cart.cartItems}">
				<c:forEach items="${sessionScope.cart.cartItems}" var="entry">
					<tr>
						<td>${entry.value.name}</td>
						<td>
							<input style="width : 80px" type="text" value="${entry.value.count}">
						</td>
						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td><a href="cartServlet?action=delete&id=${entry.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty sessionScope.cart.cartItems}">
				<tr>
					<td colspan="5"><a href="index.jsp">亲，目前购物车中没有商品，请跟随我去购物吧</a></td>
					<
				</tr>
			</c:if>

			
		</table>

		<c:if test="${not empty sessionScope.cart.cartItems}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.getTotalCount()}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.getTotalPrice()}</span>元</span>
				<span class="cart_span"><a href="#">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>
	
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>