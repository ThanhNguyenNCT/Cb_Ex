package crm09.controller;

import java.io.IOException; 
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm009.service.UserService;
import crm09.entity.Role;
import crm09.entity.User;
import crm09.repsitory.RolesRepository;
import crm09.utility.VietnameseHelper;

@WebServlet(name = "UserController", urlPatterns = {"/user-add"})
public class UserController extends HttpServlet {
	// Áp dụng Strategy Design Partern
	
	private RolesRepository rolesRp = new RolesRepository();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		VietnameseHelper.setUTF8(req, resp);
		List<Role> listRoles = rolesRp.getAllRoles();
		
		req.setAttribute("listRoles", listRoles);
		req.getRequestDispatcher("/user_add.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		VietnameseHelper.setUTF8(req, resp);
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String phone = req.getParameter("phone");
		int role_id = Integer.parseInt(req.getParameter("role_id"));
		
		User user = new User();		
		UserService userService = new UserService();
		
		userService.insertUser(user, name, email, password, phone, role_id);
		
		resp.sendRedirect("user-add?success=true");
		
	}
}
