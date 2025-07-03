package crm09.repsitory;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm09.config.MySQLConfig;
import crm09.entity.Project;
import crm09.entity.Task;
import crm09.entity.User;
import crm09.service.ProjectService;
import crm09.service.UserService;

public class TaskRepository {
	private UserService userSv = new UserService();
	private ProjectService projecSv = new ProjectService();
	
	public List<Task> getAllTask() {
		List<Task> listTasks = new ArrayList<>();
		try(Connection conn = MySQLConfig.getConnection();){
			String query = "SELECT t.id, t.name_task, t.name_task, t.start_task, t.end_task, t.id_project, t.id_user, s.status_name FROM tasks t\r\n"
					+ "JOIN status s ON t.id_status = s.id;";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Task task = new Task();
				task.setId(rs.getInt("id"));
				task.setName(rs.getString("name_task"));
				task.setStart_task(rs.getDate("start_task").toLocalDate());
				task.setEnd_task(rs.getDate("end_task").toLocalDate());
				task.setStatus(rs.getString("status_name"));
				User user = userSv.getUserById(rs.getInt("id_user"));
				task.setUser(user);
				Project project = projecSv.getProjectByID(rs.getInt("id_project"));
				task.setProject(project);
				listTasks.add(task);
			}
			
		} catch (ConnectException e) {
			System.out.println("Lỗi kết nối db khi lấy task: " + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Lỗi truy xuất DB: " + e.getMessage());
			e.printStackTrace();
		}
		
		return listTasks;
	}
	
	public int insert(Task task) {
		int rs = 0;
		try(Connection conn = MySQLConfig.getConnection();) {
			String query = "INSERT INTO tasks (name_task, start_task, end_task, id_project, id_user, status) VALUES (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, task.getName());
			ps.setDate(2, java.sql.Date.valueOf(task.getStart_task()));
			ps.setDate(3, java.sql.Date.valueOf(task.getEnd_task()));
			ps.setInt(4, task.getProject().getId());
			ps.setInt(5, task.getUser().getId());
			ps.setString(6, task.getStatus());
			rs = ps.executeUpdate();
			
		} catch (ConnectException e) {
			System.out.println("Lỗi kết nối db khi insert task: " + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Lỗi câu truy vấn: " + e.getMessage());
			e.printStackTrace();
		}
		
		return rs;
	}
}
