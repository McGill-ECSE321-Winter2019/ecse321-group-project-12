import javax.persistence.Entity;

@Entity
public class ContractNotificationByStudent extends Message {
	private String employerEmail;

	private void setEmployerEmail(String value) {
		this.employerEmail = value;
	}

	private String getEmployerEmail() {
		return this.employerEmail;
	}
}
