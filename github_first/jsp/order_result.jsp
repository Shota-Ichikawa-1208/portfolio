<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文結果</title>
</head>
<body>
<h1>注文結果</h1>
<% 
	String msg = (String)session.getAttribute("msg");
%>

<p><%= msg %></p>
</body>
</html>