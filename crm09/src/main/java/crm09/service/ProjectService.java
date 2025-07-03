package crm09.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import crm09.entity.Project;
import crm09.repsitory.ProjectsRespository;

public class ProjectService {
	private ProjectsRespository projectRp = new ProjectsRespository();
	public void insertProject(Project project, String name, LocalDate startDay, LocalDate endDay, String status) {
		project.setName(name);
		project.setStartDay(startDay);
		project.setEndDay(endDay);
		project.setStatus(status);
		int rs = projectRp.insert(project);
		if(rs > 0) {
			System.out.println("Insert project thành công !!!");
		} else {
			System.out.println("Insert project thất bại !!!");
		}
	}
	
	public Project getProjectByID(int id) {
		Project project = projectRp.getProjectByID(id);
		if(project != null) {
			System.out.println("Lấy thông tin project bằng ID thành công !!!");
		} else {
			System.out.println("Lấy thông tin project bằng ID thất bại !!!");
		}
		return project;
	}
	
	public List<Project> getAllProjects() {
		List<Project> listProjects = projectRp.getAllProjects();
		if(listProjects.size() > 0) {
			System.out.println("Lấy thông tin " + listProjects.size() + " project thành công !!!");
		} else {
			System.out.println("Lấy tất cả project thất bại !!!");
		}
		return listProjects;
	}
}
