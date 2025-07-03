package crm09.repsitory;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm09.config.MySQLConfig;
import crm09.entity.Role;

public class RolesRepository {
	public List<Role> getAllRoles() throws ConnectException{
		List<Role> listRoles = new ArrayList<>();
		Connection conn = MySQLConfig.getConnection();
		String query = "SELECT * FROM roles";
		try {
			PreparedStatement ps  = conn.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				listRoles.add(new Role(rs.getInt("id"), rs.getString("name"), rs.getString("description")));
			}

		} catch (SQLException e) {
			System.out.println("Lỗi findAllRoles" + e.getMessage());
			e.printStackTrace();
		}

		return listRoles;
	}
	public int insert(Role role) {
		int rs = 0;
		try(Connection conn = MySQLConfig.getConnection();) {

			String query = "INSERT INTO roles (name, description) VALUES (?, ?)";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, role.getName());
			ps.setString(2, role.getDescription());
			rs = ps.executeUpdate();

		} catch (ConnectException e) {
			System.out.println("Lỗi kết nối khi insert role" + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e1) {
			System.out.println("Lỗi insert role" + e1.getMessage());
			e1.printStackTrace();
		}
		return rs;
	}

	public Role getRoleByID(int id) {
		Role role = new Role();
		try(Connection conn = MySQLConfig.getConnection();){
			String query = "SELECT * FROM roles r WHERE r.id = ?";
			PreparedStatement ps = conn.prepareStatement(query);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
			    role = new Role();
			    role.setId(rs.getInt("id"));
			    role.setName(rs.getString("name"));
			    role.setDescription(rs.getString("description"));
			}

		} catch (ConnectException e) {
			System.out.println("Lỗi kết nối khi tìm role" + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e1) {
			System.out.println("Lỗi câu truy vấn tìm role " + e1.getMessage());
			e1.printStackTrace();
		}

		return role;
	}

}
