package ca.mcgill.ecse321.coop.model;

import ca.mcgill.ecse321.coop.model.User;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

@Entity
public class Student extends User {

	private boolean allowCV = false;

	public void setAllowCV(boolean value) {
		this.allowCV = value;
	}

	public boolean isAllowCV() {
		return this.allowCV;
	}

	private boolean allowTranscript = false;

	public void setAllowTranscript(boolean value) {
		this.allowTranscript = value;
	}

	public boolean isAllowTranscript() {
		return this.allowTranscript;
	}

}
