package ex2_java09.controller;

import java.io.IOException; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ex2_java09.config.MySQLConnection;
import ex2_java09.entity.Product;

@WebServlet("/edit-product")
public class EditProductController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		double price = Double.parseDouble(req.getParameter("price"));
		
		String query = "UPDATE product SET name = ?, quantity = ?, price = ? WHERE id = ?";
		Connection conn = MySQLConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, name);
			ps.setInt(2, quantity);
			ps.setDouble(3, price);
			ps.setInt(4, id);
			
			 int result = ps.executeUpdate();
	         if (result > 0) {
	             System.out.println("Cập nhật sản phẩm thành công");
	         } else {
	             System.out.println("Không tìm thấy sản phẩm để cập nhật");
	         }
		} catch (SQLException e) {
			System.out.println("Error Statement!!!");
			e.printStackTrace();
		}
		
		resp.sendRedirect(req.getContextPath() + "/product");
	}
}
