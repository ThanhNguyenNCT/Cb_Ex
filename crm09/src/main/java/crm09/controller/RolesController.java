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
import crm09.utility.VietnameseHelper;

@WebServlet(name = "RolesController", urlPatterns = {"/role-table"})
public class RolesController extends HttpServlet{
	private RolesRepository rolesRp = new RolesRepository();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		VietnameseHelper.setUTF8(req, resp);
		
		List<Role> listRoles =  rolesRp.getAllRoles();
		req.setAttribute("listRoles", listRoles);
		
		req.getRequestDispatcher("/role-table.jsp").forward(req, resp);
	}
}
