package crm09.entity;

import java.sql.Date;
import java.time.LocalDate;

public class Task {
	private int id;
	private String name;
	private LocalDate start_task;
	private LocalDate end_task;
	private Project project;
	private User user;
	private String status;
	
	public Task () {}
	
	public Task(int id, String name, LocalDate start_task, LocalDate end_task, Project project, User user,
			String status) {
		this.id = id;
		this.name = name;
		this.start_task = start_task;
		this.end_task = end_task;
		this.project = project;
		this.user = user;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getStart_task() {
		return start_task;
	}

	public void setStart_task(LocalDate start_task) {
		this.start_task = start_task;
	}

	public LocalDate getEnd_task() {
		return end_task;
	}

	public void setEnd_task(LocalDate end_task) {
		this.end_task = end_task;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	//Getter định dạng cho JSP
    public Date getStartDateSQL() {
        return Date.valueOf(start_task);
    }

    public Date getEndDateSQL() {
        return Date.valueOf(end_task);
    }
}
