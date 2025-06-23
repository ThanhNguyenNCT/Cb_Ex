package crm09.repsitory;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import crm09.config.MySQLConfig;
import crm09.entity.User;
import crm09.utility.MD5Helper;

public class UsersRespository {
	public int insert(User user) {
		int rs = 0;
		try(Connection conn = MySQLConfig.getConnection();) {
			
			String query = "INSERT INTO users (name, email, password, id_role, phone) VALUES (?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, MD5Helper.getMd5(user.getPassword()));
			ps.setInt(4, user.getId_role());
			ps.setString(5, user.getPhone());
			rs = ps.executeUpdate();
			
		} catch (ConnectException e) {
			System.out.println("Lỗi insert user" + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e1) {
			System.out.println("Lỗi kết nối khi insert user" + e1.getMessage());
			e1.printStackTrace();
		}
		
		return rs;
	}
}
