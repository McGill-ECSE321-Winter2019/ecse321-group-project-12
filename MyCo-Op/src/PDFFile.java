import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class PDFFile {
	private Document document;

	@OneToOne(optional = false)
	public Document getDocument() {
		return this.document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

}
