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

public class ProjectsRespository {
	public List<Project> getAllProjects() {
		List<Project> listProjects = new ArrayList<>();
		try(Connection conn = MySQLConfig.getConnection();){
			String query = "SELECT p.id, p.name, p.start_day, p.end_day, s.status_name FROM projects p\r\n"
					+ "JOIN status s ON p.id_status = s.id";

			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				listProjects.add(new Project(	rs.getInt("id"),
												rs.getString("name"),
												rs.getDate("start_day").toLocalDate(),
												rs.getDate("end_day").toLocalDate(),
												rs.getString("status_name")));
			}
		} catch (ConnectException e) {
			System.out.println("Lỗi kết nối :" + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Lỗi truy xuất DB: " + e.getMessage());
			e.printStackTrace();
		}
		return listProjects;
	}
	
	public int insert(Project project) {
		int rs = -1;
		try(Connection conn = MySQLConfig.getConnection();){
			String query = "INSERT INTO projects (name, start_day, end_day, status)VALUES (?, ?, ?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, project.getName());
			ps.setDate(2, java.sql.Date.valueOf(project.getStartDay()));
			ps.setDate(3, java.sql.Date.valueOf(project.getEndDay()));
			ps.setString(4, project.getStatus());
			rs = ps.executeUpdate();
		} catch (ConnectException e) {
			System.out.println("Lỗi kết nối :" + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Lỗi truy xuất DB: " + e.getMessage());
			e.printStackTrace();
		}
		return rs;
	}
	
	public Project getProjectByID(int id) {
		Project project = null;
		try(Connection conn = MySQLConfig.getConnection();){
			String query = "SELECT * FROM projects WHERE id = ?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				project = new Project(rs.getInt("id"),
									  rs.getString("name"),
									  rs.getDate("start_day").toLocalDate(),
									  rs.getDate("end_day").toLocalDate(),
									  rs.getString("status"));
			}
		} catch (ConnectException e) {
			System.out.println("Lỗi kết nối :" + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Lỗi truy xuất DB: " + e.getMessage());
			e.printStackTrace();
		}
		return project;
	}
}
