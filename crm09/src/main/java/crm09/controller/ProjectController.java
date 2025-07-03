package crm09.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm09.entity.Project;
import crm09.repsitory.ProjectsRespository;
import crm09.service.ProjectService;
import crm09.utility.VietnameseHelper;
@WebServlet(name = "ProjactController", urlPatterns = {"/groupwork", "/groupwork-add"})
public class ProjectController extends HttpServlet{
	private ProjectService projectService = new ProjectService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		VietnameseHelper.setUTF8(req, resp);
		String servletPath = req.getServletPath();
		if(servletPath.equals("/groupwork")) {
			List<Project> listProjects = projectService.getAllProjects();

			req.setAttribute("listProjects", listProjects);
			req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
		}
		else if(servletPath.equals("/groupwork-add")) {
			req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String status = req.getParameter("status");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");	
		
		String startDayStr = req.getParameter("startDay");
		LocalDate startDay = LocalDate.parse(startDayStr, formatter);
		
		String endDayStr = req.getParameter("endDay");
		LocalDate endDay = LocalDate.parse(endDayStr, formatter);
		
		Project project = new Project();
		projectService.insertProject(project, name, startDay, endDay, status);
		
		resp.sendRedirect("groupwork?success=true");
		
		
	}
}
