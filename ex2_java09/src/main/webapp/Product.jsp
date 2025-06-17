
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý sản phẩm</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f6f8;
            margin: 0;
            padding: 40px;
            display: flex;
            justify-content: center;
        }

        .container {
            background: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 6px 20px rgba(0,0,0,0.1);
            max-width: 800px;
            width: 100%;
        }

        h1 {
            font-size: 26px;
            margin-bottom: 25px;
            color: #333;
            border-left: 5px solid #007bff;
            padding-left: 10px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 6px;
            font-weight: 500;
            color: #555;
        }

        input[type="text"]:not(.input-small) {
            width: 100%;
            padding: 10px 12px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 15px;
        }
        .input-small {
    		width: 100px;
    		padding: 6px 8px;
    		font-size: 14px;
    		border-radius: 4px;
    		border: 1px solid #ccc;
    		box-sizing: border-box;
		}

        button {
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 6px;
            font-size: 15px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #0056b3;
        }

        table {
            margin-top: 30px;
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 14px 12px;
            border: 1px solid #e0e0e0;
        }

        th {
            background-color: #f2f4f7;
            color: #333;
            text-align: left;
        }

        tr:nth-child(even) {
            background-color: #fafafa;
        }
        
    </style>
</head>
<body>

<div class="container">
    <h1>QUẢN LÝ SẢN PHẨM</h1>

    <form method="post" action="product">
        <div class="form-group">
            <label>Tên sản phẩm</label>
            <input type="text" name="name" placeholder="Nhập tên sản phẩm...">
        </div>
        <div class="form-group">
            <label>Số lượng:</label>
            <input type="text" name="quantity" placeholder="VD: 50">
        </div>
        <div class="form-group">
            <label>Giá bán:</label>
            <input type="text" name="price" placeholder="VD: 1000000">
        </div>
        <button type="submit">Lưu lại</button>
    </form>
	
    <table>
        <thead>
            <tr>
                <th>STT</th>
                <th>Tên Sản Phẩm</th>
                <th>Số Lượng</th>
                <th>Giá Bán</th>
                <th>Thao tác</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var = "p" items="${listProducts}" varStatus="loop">
        		<tr>
        			<td>${loop.index + 1}</td>
        			 <c:choose>
            			<c:when test="${param.editId == p.id}">
            			    <!-- Form sửa tại dòng -->
            			    <form action="edit-product" method="post">
            			        <input type="hidden" name="id" value="${p.id}" />
            			        <td><input type="text" name="name" value="${p.name}" class="input-small"/></td>
            			        <td><input type="text" name="quantity" value="${p.quantity}" class="input-small"/></td>
            			        <td><input type="text" name="price" value="${p.price}" class="input-small"/></td>
            			        <td>
            			            <button type="submit">Lưu</button>
            			            <a href="product">Hủy</a>
            			        </td>
            			    </form>
            			</c:when>
            			<c:otherwise>
            			    <!-- Dòng bình thường -->
            			    <td>${p.name}</td>
            			    <td>${p.quantity}</td>
            			    <td><fmt:formatNumber value="${p.price}" type="number" maxFractionDigits="2"/></td>
            			    <td>
            			        <form action="product" method="get" style="display:inline;">
            			            <input type="hidden" name="editId" value="${p.id}" />
            			            <button type="submit">Sửa</button>
            			        </form>
			
            			        <form action="delete-product" method="post" style="display:inline;">
            			            <input type="hidden" name="id" value="${p.id}" />
            			            <button type="submit" onclick="return confirm('Bạn có chắc muốn xóa?');">Xóa</button>
            			        </form>
            			    </td>
            			</c:otherwise>
        			</c:choose>
        		</tr>
        	</c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>

