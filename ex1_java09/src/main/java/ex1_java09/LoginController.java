package ex1_java09;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Forward the request to the login.jsp page
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		if(username.equals("admin") && password.equals("123456")) {
			System.out.println("Đăng nhập thành công!!!");
			req.getRequestDispatcher("/welcome.jsp").forward(req, resp);
		} else {
			System.out.println("Đăng nhập thất bại!!!");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}
}
