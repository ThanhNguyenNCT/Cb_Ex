package crm09.entity;

public class User {
	private int id;
	private String last_name;
	private String first_name;
	private String email;
	private int id_role;
	private String password;
	private String phone;
	private Role role;
	public User() {
	}

	public User(int id, String first_name, String last_name, String email, String password, String phone, int id_role) {
		this.id = id;
		this.last_name = last_name;
		this.first_name = first_name;
		this.email = email;
		this.id_role = id_role;
		this.password = password;
		this.phone = phone;
	}

	public User(int userId, String userEmail) {
		this.id = userId;
		this.email = userEmail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId_role() {
		return id_role;
	}

	public void setId_role(int id_role) {
		this.id_role = id_role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public String getFullName() {
		return this.last_name + " " + this.first_name;
	}

}
