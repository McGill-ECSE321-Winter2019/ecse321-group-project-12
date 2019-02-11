package ca.mcgill.ecse321.coop.model;

import javax.persistence.Entity;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import java.sql.Time;

@Entity
public class EventNotifcation extends Message {

	private String name;

	public void setName(String value) {
		this.name = value;
	}

	public String getName() {
		return this.name;
	}

	private Date date;

	private void setDate(Date value) {
		this.date = value;
	}

	private Date getDate() {
		return this.date;
	}

	private Address address;

	@OneToOne(mappedBy = "eventNotifcation", cascade = { CascadeType.ALL }, optional = false)
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	private Time startTime;

	private void setStartTime(Time value) {
		this.startTime = value;
	}

	private Time getStartTime() {
		return this.startTime;
	}

	private Time endTime;

	private void setEndTime(Time value) {
		this.endTime = value;
	}

	private Time getEndTime() {
		return this.endTime;
	}
}
