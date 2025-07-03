package crm09.repsitory;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm09.config.MySQLConfig;
import crm09.entity.User;
import crm09.utility.MD5Helper;

public class UsersRespository {
	public int insert(User user) {
		int rs = 0;
		try(Connection conn = MySQLConfig.getConnection();) {

			String query = "INSERT INTO users (last_name, first_name, email, password, id_role, phone) VALUES (?, ?, ?, ?, ?, ?)";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, user.getLast_name());
			ps.setString(2, user.getFirst_name());
			ps.setString(3, user.getEmail());
			ps.setString(4, MD5Helper.getMd5(user.getPassword()));
			ps.setInt(5, user.getId_role());
			ps.setString(6, user.getPhone());
			rs = ps.executeUpdate();

		} catch (ConnectException e) {
			System.out.println("Lỗi kết nối db khi insert user: " + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e1) {
			System.out.println("Lỗi câu truy vấn: " + e1.getMessage());
			e1.printStackTrace();
		}

		return rs;
	}

	public List<User> getAllUsers(){
		List<User> listUsers = new ArrayList<>();
		try(Connection conn = MySQLConfig.getConnection();){
			String qurey = "SELECT * FROM users";

			PreparedStatement ps = conn.prepareStatement(qurey);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				listUsers.add(new User( rs.getInt("id"),
										rs.getString("first_name"),
										rs.getString("last_name"),
										rs.getString("email"),
										rs.getString("password"),
										rs.getString("phone"),
										rs.getInt("id_role")));
			}
		} catch (ConnectException e) {
			System.out.println("Lỗi kết nối db khi lấy user: " + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e1) {
			System.out.println("Lỗi câu truy vấn: " + e1.getMessage());
			e1.printStackTrace();
		}

		return listUsers;
	}
	
	public User getUserById(int id) {
		User user = null;
		try(Connection conn = MySQLConfig.getConnection();){
			String query = "SELECT * FROM users u WHERE u.id = ?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				user = new User(rs.getInt("id"),
								rs.getString("first_name"),
								rs.getString("last_name"),
								rs.getString("email"),
								rs.getString("password"),
								rs.getString("phone"),
								rs.getInt("id_role"));
			}
		} catch (ConnectException e) {
			System.out.println("Lỗi kết nối db khi lấy user bằng id: " + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Lỗi câu truy vấn getUserById: " + e.getMessage());
			e.printStackTrace();
		}
		
		return user;
	}
}
