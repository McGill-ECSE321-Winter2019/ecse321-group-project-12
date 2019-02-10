package ca.mcgill.ecse321.coop.model;

import javax.persistence.Entity;

@Entity
public class ContractNotificationByStudent extends Message {
	private String employerEmail;

	public void setEmployerEmail(String value) {
		this.employerEmail = value;
	}

	public String getEmployerEmail() {
		return this.employerEmail;
	}
}
