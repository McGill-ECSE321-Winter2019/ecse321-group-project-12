package ca.mcgill.ecse321.coop.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.Id;

@Entity
public class Message {
	private String content;

	public void setContent(String value) {
		this.content = value;
	}

	public String getContent() {
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

	public void setMessageId(String value) {
		this.messageId = value;
	}

	@Id
	public String getMessageId() {
		return this.messageId;
	}
}
