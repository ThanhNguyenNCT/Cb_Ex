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
		List<Role> listRoles = new ArrayList<Role>();
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
	
}
