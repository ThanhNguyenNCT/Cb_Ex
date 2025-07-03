package crm09.service;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import crm09.entity.Role;
import crm09.entity.User;
import crm09.repsitory.RolesRepository;
import crm09.repsitory.UsersRespository;

public class UserService {
	private RolesRepository rolesRp = new RolesRepository();
	private UsersRespository usersRp = new UsersRespository();
	public List<Role> getAllRoles(){
		List<Role> listRoles = new ArrayList<>();
		try {
			listRoles = rolesRp.getAllRoles();

			if(listRoles.size() > 0) {
				System.out.println("Truy xuất roles thành công !!!");
			} else {
				System.out.println("Truy xuất roles thất bại !!!!!!!");
			}
		} catch (ConnectException e) {
			System.out.println("List rỗng " + e.getMessage());
			e.printStackTrace();
		}

		return listRoles;
	}

	public void insertUser(User user, String name, String email, String password, String phone,
			int role_id) {

		String [] names = name.split(" ");
		String first_name = names[names.length-1];
		String last_name = "";
		for(int i = 0; i < names.length - 1 ; i ++) {
			last_name = last_name + names[i] + " ";
		}
		last_name = last_name.trim();
		user.setFirst_name(first_name);
		user.setLast_name(last_name);
		user.setEmail(email);
		user.setPassword(password);
		user.setPhone(phone);
		user.setId_role(role_id);
		int rs = usersRp.insert(user);
		if(rs > 0) {
			System.out.println("Insert user thành công !!!");
		} else {
			System.out.println("Insert user thất bại !!!");
		}
	}

	public List<User> getAllUsers() {
		List<User> listUsers = new ArrayList<>();
		listUsers = usersRp.getAllUsers();

		if(listUsers.size() > 0) {
			System.out.println("Truy xuất " + listUsers.size() + " users thành công !!!");
		} else {
			System.out.println("Truy xuất users thất bại !!!!!!!");
		}

		return listUsers;
	}
	
	public User getUserById(int id) {
		User user = usersRp.getUserById(id);
		if(user != null) {
			System.out.println("Truy xuất user bằng ID thành công !!!");
		} else {
			System.out.println("Truy xuất user bằng ID thất bại !!!!!!!");
		}
		return user;
	}

}
