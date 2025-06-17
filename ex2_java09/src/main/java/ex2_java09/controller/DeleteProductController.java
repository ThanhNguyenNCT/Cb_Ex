package ex2_java09.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ex2_java09.config.MySQLConnection;
import ex2_java09.entity.Product;

@WebServlet("/delete-product")
public class DeleteProductController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		String query = "DELETE FROM product WHERE id = ?";
		
		Connection conn = MySQLConnection.getConnection();
		
		try {
			PreparedStatement deleteStmt = conn.prepareStatement(query);
			deleteStmt.setInt(1, id);
			int rs = deleteStmt.executeUpdate();
			deleteStmt.close();
			if(rs > 0) {
				System.out.println("Delete product successful !!!!!");
			}else {
				System.out.println("Delete product unsuccessful !!!!!");
			}
			
			java.sql.Statement stmt = conn.createStatement();
			stmt.execute("CREATE TABLE product_temp (\r\n"
					+ "  id INT PRIMARY KEY AUTO_INCREMENT,\r\n"
					+ "  name VARCHAR(255),\r\n"
					+ "  quantity INT,\r\n"
					+ "  price DOUBLE\r\n"
					+ ")");
			
			stmt.execute("INSERT INTO product_temp(name, quantity, price)\r\n"
					+ "SELECT name, quantity, price FROM product;");
			
			stmt.execute("DROP TABLE product");
			
			stmt.execute("RENAME TABLE product_temp TO product");
			
			stmt.close();
			System.out.println("Xóa và reset id thành công !!!");
		} catch (SQLException e) {
			System.out.println("Error Statement");
			e.printStackTrace();
		}
		
		resp.sendRedirect(req.getContextPath() + "/product");
	}
}
