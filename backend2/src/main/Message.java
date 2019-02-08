import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.Id;

@Entity
public class Message {
	private String content;

	private void setContent(String value) {
		this.content = value;
	}

	private String getContent() {
		return this.content;
	}

	private User sender;

	@ManyToOne(optional = false)
	public User getSender() {
		return this.sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	private User reciever;

	@ManyToOne(optional = false)
	public User getReciever() {
		return this.reciever;
	}

	public void setReciever(User reciever) {
		this.reciever = reciever;
	}

	private Set<Document> attachements;

	@OneToMany
	public Set<Document> getAttachements() {
		return this.attachements;
	}

	public void setAttachements(Set<Document> attachementss) {
		this.attachements = attachementss;
	}

	private String messageId;

	private void setMessageId(String value) {
		this.messageId = value;
	}

	@Id
	private String getMessageId() {
		return this.messageId;
	}
}
