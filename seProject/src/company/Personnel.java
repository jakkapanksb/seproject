package company;

public class Personnel {
	private String id;
	private String name;
	private String position;
	private String department;
	private int permission = 0;
	private float salary;

	public Personnel(String id, String name, String position, String department, int permission,float salary) {
		this.id = id;
		this.name = name;
		this.position = position;
		this.department = department;
		this.permission = permission;
		this.salary = salary;
	}

	public String getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPosition() {
		return position;
	}

	public String getDepartment() {
		return department;
	}

	public int getPermission() {
		return permission;
	}
	
	public void setID(String id) {
		this.id = id;
	}
}