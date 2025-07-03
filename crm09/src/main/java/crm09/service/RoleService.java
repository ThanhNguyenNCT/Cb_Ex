package crm09.service;

import crm09.entity.Role;
import crm09.repsitory.RolesRepository;

public class RoleService {
	private RolesRepository rolesRp = new RolesRepository();
	public void insertRole(Role role,  String name, String description) {
		int rs = 0;
		role.setName(name);
		role.setDescription(description);
		rs = rolesRp.insert(role);
		if(rs > 0) {
			System.out.println("Insert role thành công !!!");
		} else {
			System.out.println("Insert role thất bại !!!");
		}
	}
}
