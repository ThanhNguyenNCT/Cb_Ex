package crm09.entity;

import java.sql.Date;
import java.time.LocalDate;


public class Project {

	private int id;
	private String name;
	private LocalDate startDay;
	private LocalDate endDay;
	private String status;

	public Project() {}

	public Project(int id, String name, LocalDate startDay, LocalDate endDay, String status) {
		this.id = id;
		this.name = name;
		this.startDay = startDay;
		this.endDay = endDay;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getStartDay() {
		return startDay;
	}

	public void setStartDay(LocalDate startDay) {
		this.startDay = startDay;
	}

	public LocalDate getEndDay() {
		return endDay;
	}

	public void setEndDay(LocalDate endDay) {
		this.endDay = endDay;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	//Getter định dạng cho JSP
    public Date getStartDateSQL() {
        return Date.valueOf(startDay);
    }

    public Date getEndDateSQL() {
        return Date.valueOf(endDay);
    }
}
