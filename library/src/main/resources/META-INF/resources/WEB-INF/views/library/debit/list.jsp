<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--加入JSTL标签库 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>借阅列表</title>
<link href="/linn/library/css/main.css" rel="stylesheet" />
</head>
<body>
	<c:forEach items="${debitList.books }" var="book">
	<div class="item">
		${book.name }
		<a href="/linn/library/debit/remove/${book.id }" class="remove">删除</a>	
	</div>
	</c:forEach>
</body>
</html>