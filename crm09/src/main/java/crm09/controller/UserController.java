package crm09.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm09.entity.Role;
import crm09.entity.User;
import crm09.repsitory.RolesRepository;
import crm09.service.UserService;
import crm09.utility.VietnameseHelper;

@WebServlet(name = "UserController", urlPatterns = {"/user-add", "/user-table", "/user-edit", "/user-delete", "/user-detail"})
public class UserController extends HttpServlet {
	// Áp dụng Strategy Design Partern
	private UserService userService = new UserService();
	private RolesRepository rolesRp = new RolesRepository();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		VietnameseHelper.setUTF8(req, resp);
		String servletPath = req.getServletPath();
		if (servletPath.equals("/user-table")) {
			List<User> listUsers = userService.getAllUsers();

			listUsers.forEach(u ->{
				int role_id = u.getId_role();
			    Role r = rolesRp.getRoleByID(role_id);
//			    System.out.println("User: " + u.getFirst_name() + ", Role: " + (r != null ? r.getDescription() : "null"));
			    u.setRole(r);
			});

			req.setAttribute("listUsers", listUsers);
			req.getRequestDispatcher("user-table.jsp").forward(req, resp);
		} else if (servletPath.equals("/user-add")) {
			List<Role> listRoles = rolesRp.getAllRoles();

			req.setAttribute("listRoles", listRoles);
			req.getRequestDispatcher("/user_add.jsp").forward(req, resp);
		}

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

		resp.sendRedirect("user-table?success=true");

	}
}
