package ca.mcgill.ecse321.coop.model;

import javax.persistence.ManyToOne;

import javax.persistence.ManyToMany;
import java.util.Set;

import java.sql.Time;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.persistence.Id;

@Entity
public abstract class Document {
	private User author;

	@ManyToOne(optional = false)
	public User getAuthor() {
		return this.author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	private int size;

	public void setSize(int value) {
		this.size = value;
	}

	public int getSize() {
		return this.size;
	}

	private Date submissionDate;

	private void setSubmissionDate(Date value) {
		this.submissionDate = value;
	}

	private Date getSubmissionDate() {
		return this.submissionDate;
	}

	private Time submissionTime;

	private void setSubmissionTime(Time value) {
		this.submissionTime = value;
	}

	private Time getSubmissionTime() {
		return this.submissionTime;
	}

	private String documentId;

	public void setDocumentId(String value) {
		this.documentId = value;
	}

	@Id
	public String getDocumentId() {
		return this.documentId;
	}
}
