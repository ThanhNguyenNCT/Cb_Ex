package new_servlet09;

public class Students {
	private String name;
	private double score;
	private String major;
	
	public Students(String name, double score, String major) {
		this.name = name;
		this.score = score;
		this.major = major;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
}