<%@page import="new_servlet09.Students"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
	
	<table border="1" style="width:100%">
		<thead>Danh sách sinh viên</thead>
		<tbody>
			<tr>
            	<th>Họ và tên</th>
            	<th>Điểm</th>
            	<th>Chuyên ngành</th>
            	<th>Xếp loại</th>
        	</tr>
        	<%-- <%
           	List<Students> studentsList = (List<Students>) request.getAttribute("students");
        	%>
        	<%
            	for (Students student : studentsList) {
                	String name = student.getName();
                	double score = student.getScore();
                	String major = student.getMajor();
                	String classification;
                
                	if (score >= 9.0) {
                    classification = "Giỏi";
                	} else if (score >= 7.0) {
                    classification = "Khá";
                	} else if (score >= 5.0) {
                    classification = "Trung bình";
                	} else {
                    classification = "Yếu/kém";
                	}
        	%>
        	<tr>
            	<td><%= name %></td>
            	<td><%= score %></td>
            	<td><%= major %></td>
            	<td><%= classification %></td>
        	</tr>
        	<%
        	    }
        	%> --%>
        	<c:forEach var="s" items="${students}">
        	<tr>
        		<td>${s.name}</td>
        		<td>${s.score}</td>
        		<td>${s.major}</td>
        		<td>
        			<c:choose>
        			    <c:when test="${s.score >= 9.0}">Giỏi</c:when>
        				<c:when test="${s.score >= 7.0}">Khá</c:when>
        				<c:when test="${s.score >= 5.0}">Trung bình</c:when>
        				<c:otherwise>Yếu/kém</c:otherwise>
        			</c:choose>
        	    </td>
        	</tr>
        	</c:forEach>
		</tbody>
    </table>
</body>
</html>
