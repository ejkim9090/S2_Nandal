<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>alert</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.6.1.js"></script>
</head>
<body>
<script>
	var message = "<%=request.getAttribute("msg")%>";
	var messageName = "<%=request.getAttribute("msgName")%>";
	alert(message);
	if(messageName == "memberUpdate") {
		location.href = "<%=request.getContextPath()%>/my";
	} else {
		history.back();
	}
</script>
</body>
</html>