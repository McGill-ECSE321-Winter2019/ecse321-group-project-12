import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class User {
	private String username;

	private void setUsername(String value) {
		this.username = value;
	}

	@Id
	private String getUsername() {
		return this.username;
	}

	private MyCoOp myCoOp;

	@ManyToOne(optional = false)
	public MyCoOp getMyCoOp() {
		return this.myCoOp;
	}

	public void setMyCoOp(MyCoOp myCoOp) {
		this.myCoOp = myCoOp;
	}

	private Address contact;

	@OneToOne(mappedBy = "user", cascade = { CascadeType.ALL }, optional = false)
	public Address getContact() {
		return this.contact;
	}

	public void setContact(Address contact) {
		this.contact = contact;
	}

	private Set<UserRole> roles;

	@OneToMany
	public Set<UserRole> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<UserRole> roless) {
		this.roles = roless;
	}

	private Set<Message> sentMessages;

	@OneToMany(mappedBy = "sender")
	public Set<Message> getSentMessages() {
		return this.sentMessages;
	}

	public void setSentMessages(Set<Message> sentMessagess) {
		this.sentMessages = sentMessagess;
	}

	private Set<Message> receivedMessages;

	@OneToMany(mappedBy = "reciever")
	public Set<Message> getReceivedMessages() {
		return this.receivedMessages;
	}

	public void setReceivedMessages(Set<Message> receivedMessagess) {
		this.receivedMessages = receivedMessagess;
	}

}
