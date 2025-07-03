package crm09.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm09.entity.Project;
import crm09.entity.Task;
import crm09.entity.User;
import crm09.service.ProjectService;
import crm09.service.TaskService;
import crm09.service.UserService;
import crm09.utility.VietnameseHelper;

@WebServlet(name = "TaskController", urlPatterns = {"/task", "/task-add"})
public class TaskController extends HttpServlet{
	private TaskService taskService = new TaskService();
	private ProjectService projectService = new ProjectService();
	private UserService userService = new UserService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		VietnameseHelper.setUTF8(req, resp);
		String serveletPath = req.getServletPath();
		if(serveletPath.equals("/task")) {
			List<Task> listTasks = taskService.getAllTasks();
			
			req.setAttribute("listTasks", listTasks);
			req.getRequestDispatcher("/task.jsp").forward(req, resp);
		} else if (serveletPath.equals("/task-add")) {
			List<Project> listProjects = projectService.getAllProjects();
			List<User> listUsers = userService.getAllUsers();
			req.setAttribute("listUsers", listUsers);
			req.setAttribute("listProjects", listProjects);
			req.getRequestDispatcher("/task-add.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		VietnameseHelper.setUTF8(req, resp);
		String name_task = req.getParameter("name_task");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");	
		String start_taskStr = req.getParameter("start_task");
		String end_taskStr = req.getParameter("end_task");
		LocalDate start_task = LocalDate.parse(start_taskStr, formatter);
		LocalDate end_task = LocalDate.parse(end_taskStr, formatter);
		
		int id_project = Integer.parseInt(req.getParameter("id_project"));
		int id_user = Integer.parseInt(req.getParameter("id_user"));
		String status = req.getParameter("status");
		
		Task task = new Task();
		taskService.insertTask(task, name_task, start_task, end_task, id_project, id_user, status);
		
		resp.sendRedirect("task?success=true");
		
	}
}
