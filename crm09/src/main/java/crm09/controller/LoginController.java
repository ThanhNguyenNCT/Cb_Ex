package crm09.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm09.config.MySQLConfig;
import crm09.entity.Role;
import crm09.entity.User;
import crm09.repsitory.RolesRepository;
import crm09.utility.MD5Helper;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet{
	private RolesRepository rolesRp = new RolesRepository();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Chuyển hướng đến trang đăng nhập
		Cookie[] cookies = req.getCookies();
		String email = "";
		String password = "";
		for (Cookie cookie : cookies) {
			String name = cookie.getName();
			String value = cookie.getValue();
			System.out.println("Cookie Name: " + name + ", Value: " + value);

			switch (name) {
				case "sUserEmail":
					email = value;
					break;
				case "sUserPassword":
					password = value;
					break;
			}

		}
		req.setAttribute("email", email);
		req.setAttribute("password", password);
		req.getRequestDispatcher("/login.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Lấy tham số email và password bên UI login.jsp khi
		// người dùng nhấn nút đăng nhập
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		// Chuẩn bị câu truy vấn SQL để kiểm tra thông tin đăng nhập
		String query =
				"SELECT * \r\n"
				+ "FROM users u\r\n"
				+ "JOIN roles r ON u.id_role = r.id\r\n"
				+ "WHERE u.email = ? AND u.password = ?";

		// Mở kết nối đến Database
		Connection connection = MySQLConfig.getConnection();

		//Truyền query vào connection mới vừa mở để truyền xuống
		// database thông qua PreparedStatement do JDBC cung cấp
		try {
			PreparedStatement prepareStatement =  connection.prepareStatement(query);
			// Thiết lập các tham số cho câu truy vấn(truyền vào dấy "?"
			// trong câu truy vấn SQL)
			prepareStatement.setString(1, email);
			prepareStatement.setString(2, MD5Helper.getMd5(password));


			// Thực thi câu truy vấn
			//Vì là SELECT nên sẽ trả về kết quả nên dùng excuteQuery()
			ResultSet resultSet = prepareStatement.executeQuery();
			List<User> listUsers = new ArrayList<>();

			// Duyệt qua kết quả trả về từ câu truy vấn
			//Map từng dòng của resultSet thành đối tượng User
			while (resultSet.next()) {
				int userId = resultSet.getInt("id");
				String userEmail = resultSet.getString("email");
				Role role = new Role();
				role = rolesRp.getRoleByID(resultSet.getInt("id_role"));

				User user = new User();

				user.setId(userId);
				user.setEmail(userEmail);
				user.setRole(role);

				listUsers.add(user);
			}

			if(listUsers.size() > 0) {
				// Tạo cookie
				Cookie cookieEmail = new Cookie("sUserEmail", email);
				// Thiết lập thời gian sống của cookie
				cookieEmail.setMaxAge(5*60*10);

				Cookie cookiePassword = new Cookie("sUserPassword", password);
				cookiePassword.setMaxAge(5*60*10);

				String roleName = listUsers.get(0).getRole().getName();
				Cookie cookieRole = new Cookie("sUserRole", roleName);
				cookieRole.setMaxAge(5*60*10);

				resp.addCookie(cookieEmail);
				resp.addCookie(cookiePassword);
				resp.addCookie(cookieRole);
				System.out.println("Đăng nhập thành công!");
				resp.sendRedirect("index.jsp");
			} else {
//				req.setAttribute("errorMessage", "Email hoặc mật khẩu không đúng!"); login_1
//				req.getRequestDispatcher("/login_1.jsp").forward(req, resp);
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
				System.out.println("Đăng nhập thất bại!");
			}

		} catch (Exception e) {
			System.out.println("Lỗi truyền query xuống database: " + e.getMessage());
			e.printStackTrace();
		}

	}
}
