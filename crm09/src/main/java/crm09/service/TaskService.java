package crm09.service;

import java.time.LocalDate;
import java.util.List;

import crm09.entity.Task;
import crm09.repsitory.TaskRepository;

public class TaskService {
	public List<Task> getAllTasks() {
		TaskRepository taskRp = new TaskRepository();
		List<Task> listTasks = taskRp.getAllTask();
		if (listTasks == null || listTasks.isEmpty()) {
			System.out.println("Không có công việc nào, lấy thất bại!!");
		}else {
			System.out.println("Lấy " + listTasks.size() + " công việc thành công");
		}
		return listTasks;
	}
	
	public void insertTask(Task task, String name, LocalDate start_task, LocalDate end_task, int id_project, int id_user, String status) {
		TaskRepository taskRp = new TaskRepository();
		UserService userSv = new UserService();
		ProjectService projectSv = new ProjectService();
		task.setName(name);
		task.setStart_task(start_task);
		task.setEnd_task(end_task);
		task.setProject(projectSv.getProjectByID(id_project));
		task.setUser(userSv.getUserById(id_user));
		task.setStatus(status);
		
		int rs = taskRp.insert(task);
		if(rs > 0) {
			System.out.println("Insert task thành công !!!");
		} else {
			System.out.println("Insert task thất bại !!!");
		}
	}

}
