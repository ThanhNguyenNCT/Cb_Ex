package crm09.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm09.entity.Role;
import crm09.repsitory.RolesRepository;
import crm09.service.RoleService;
import crm09.utility.VietnameseHelper;

@WebServlet(name = "AddRoleController", urlPatterns = {"/role-add", "/role-table"})
public class RoleController extends HttpServlet{
	private RolesRepository rolesRp = new RolesRepository();
	private RoleService roleService = new RoleService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		VietnameseHelper.setUTF8(req, resp);

		String servletPath = req.getServletPath();
		if(servletPath.equals("/role-add")) {
			req.getRequestDispatcher("role-add.jsp").forward(req, resp);
		} else if(servletPath.equals("/role-table")) {
			List<Role> listRoles = rolesRp.getAllRoles();

			req.setAttribute("listRoles", listRoles);
			req.getRequestDispatcher("/role-table.jsp").forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		VietnameseHelper.setUTF8(req, resp);

		String name = req.getParameter("name");
		String description = req.getParameter("description");

		Role role = new Role();
		roleService.insertRole(role, name, description);

		resp.sendRedirect("role-table?success=true");

	}
}
