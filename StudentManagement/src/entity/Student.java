package entity;

public class Student {
	private long id;
	private String name;
	private String stuNumber;
	private String major;

	public Student() {

	}

	public Student(long id, String name, String stuNumber, String major) {
		super();
		this.id = id;
		this.name = name;
		this.stuNumber = stuNumber;
		this.major = major;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStuNumber() {
		return stuNumber;
	}

	public void setStuNumber(String stuNumber) {
		this.stuNumber = stuNumber;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

}
