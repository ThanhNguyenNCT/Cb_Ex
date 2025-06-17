<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%int count = (Integer) request.getAttribute("count");%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nhập thông tin sinh viên</title>
</head>
<body>
	<form action="submit" method="post">
		<c:forEach var="i" begin="1" end="${count}">
            <div>
                <label for="name${i}">Họ và tên sinh viên ${i}:</label>
                <input type="text" id="name${i}" name="name${i}" required>
            </div>
            <div>
                <label for="score${i}">Điểm sinh viên ${i}:</label>
                <input type="number" id="score${i}" name="score${i}" step="0.01" min="0" max="10" required>
            </div>
            <div>
                <label for="major${i}">Chuyên ngành sinh viên ${i}:</label>
                <input type="text" id="major${i}" name="major${i}" required>
            </div>
            <hr/>
        </c:forEach>
        <input type="hidden" name="count" value="${count}">
        <button type="submit">Gửi thông tin</button>
	</form>
</body>
</html>