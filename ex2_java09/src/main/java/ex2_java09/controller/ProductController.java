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

@WebServlet("/product")
public class ProductController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		List<Product> listProducts = new ArrayList<Product>();
		Connection connection = MySQLConnection.getConnection();
		
		String query_2 = "SELECT *\r\n"
				+ "FROM product p";
		PreparedStatement selectStmt;
		try {
			selectStmt = connection.prepareStatement(query_2);
			ResultSet rs = selectStmt.executeQuery();

			
			while(rs.next()) {
				listProducts.add(new Product(	rs.getInt("id"),
												rs.getString("name"),
												rs.getInt("quantity"),
												rs.getDouble("price")));
			}
			
			if(listProducts.size() > 0) {
				System.err.println("Truy xuat thanh cong !!!");
			} else {
				System.out.println("Truy xuat that bai");
			}
		} catch (SQLException e) {
			System.out.println("Error get statement: " + e.getMessage());
			e.printStackTrace();
		}
		req.setAttribute("listProducts", listProducts);
		req.setAttribute("editId", req.getParameter("editId"));
		req.getRequestDispatcher("/Product.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String name = req.getParameter("name");
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		double price = Double.parseDouble(req.getParameter("price"));
		
		String query_1 = "INSERT INTO product(name, quantity, price) VALUES (?, ?, ?)";
		
		Connection connection = MySQLConnection.getConnection();
//		System.out.println("Connecting to the database...");
//		if(connection == null) {
//			System.out.println("Error connecting to the database");
//			return;
//		}
		try {
			PreparedStatement insertStmt = connection.prepareStatement(query_1);
			insertStmt.setString(1, name);
			insertStmt.setInt(2, quantity);
			insertStmt.setDouble(3, price);
			
			int result = insertStmt.executeUpdate();
			if(result > 0) System.out.println("Thêm sản phẩm thành công!!!");
			
		} catch (SQLException e) {
			System.out.println("Error get statement: " + e.getMessage());
			e.printStackTrace();
		}		
		System.out.println("Sending direct....");
		resp.sendRedirect(req.getContextPath() + "/product");
	}
}
