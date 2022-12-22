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
	var msg = "<%=request.getAttribute("msg")%>";
	alert(msg);
	<%-- location.href = "<%=request.getContextPath()%>/main"; --%>
	history.back();
</script>
</body>
</html>