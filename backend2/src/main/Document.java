import java.sql.Time;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.persistence.Id;

@Entity
public abstract class Document {
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

	private User author;

	private void setAuthor(User value) {
		this.author = value;
	}

	private User getAuthor() {
		return this.author;
	}

	private PDFFile pdf;

	@OneToOne(mappedBy = "document", cascade = { CascadeType.ALL }, optional = false)
	public PDFFile getPdf() {
		return this.pdf;
	}

	public void setPdf(PDFFile pdf) {
		this.pdf = pdf;
	}

	private String documentId;

	private void setDocumentId(String value) {
		this.documentId = value;
	}

	@Id
	private String getDocumentId() {
		return this.documentId;
	}
}
