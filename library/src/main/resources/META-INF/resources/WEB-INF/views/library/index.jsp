<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--加入JSTL标签库 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管首页</title>
<link href="/linn/library/css/main.css" rel="stylesheet" />
</head>
<body>
	<form action="" method="get">
		<input name="keyword" placeholder="关键字" value="${param.keyword}" />
		<button>搜索</button>
	</form>


	<%-- 循环输出查询得到的数据 --%>
	<%-- <%
		Page<Book> page = (Page<Book>) request.getAttribute("page");
		List<Book> content = page.getContent();
		for(Book book : content){
	%>
	<div>
		<img/>
		<span><%=book.getName() %></span>
	</div>
	<%} %> --%>

	<%-- ${page.content }是EL表达式，属于JSP 2.0的功能--%>
	<%-- 它首先从request里面获取名为page的对象，然后调用page对象里面的个体Content（）方法 --%>
	<c:forEach items="${page.content }" var="book">
		<div class="item">
			<div class="col-1">
				<img src="/linn/library/images/${book.image }" />
			</div>
			<%-- 此处是直接调用book.getName()方法 --%>
			<div class="col-10">
				<span>${book.name }</span>
			</div>
			<div class="col-1 buttons">
				<span class=button>+</span>
			</div>
		</div>

	</c:forEach>




	<%-- 分页按钮 --%>

	<div>
		<c:if test="${page.number <= 0 }">
			<a>上一页</a>
		</c:if>
		<c:if test="${page.number > 0 }">
			<a href="?pageNumber=${page.number - 1 }&keyword=${param.keyword}">上一页</a>
		</c:if>
		<%-- c:if没有else，通常要使用else的时候改用c:choose --%>
		<%-- c:choose类似于java里面的switch case语句  --%>
		<c:choose>
			<%-- 为什么要减一？因为页码从0开始，但是totalPages是总页数，从1开始。 --%>
			<c:when test="${page.number >= page.totalPages - 1 }">
				<a>下一页</a>
			</c:when>
			<c:otherwise>
				<a href="?pageNumber=${page.number + 1 }&keyword=${param.keyword}">下一页</a>
			</c:otherwise>
		</c:choose>
	</div>

</body>
</html>