import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

@Entity
public class Student extends UserRole {
	private Year year;

	private void setYear(Year value) {
		this.year = value;
	}

	private Year getYear() {
		return this.year;
	}

	private Faculty faculty;

	private void setFaculty(Faculty value) {
		this.faculty = value;
	}

	private Faculty getFaculty() {
		return this.faculty;
	}

	private Set<CoOpJob> coOpJobs;

	@OneToMany(mappedBy = "coOpStudent")
	public Set<CoOpJob> getCoOpJobs() {
		return this.coOpJobs;
	}

	public void setCoOpJobs(Set<CoOpJob> coOpJobss) {
		this.coOpJobs = coOpJobss;
	}

	private boolean allowCV = false;

	private void setAllowCV(boolean value) {
		this.allowCV = value;
	}

	private boolean isAllowCV() {
		return this.allowCV;
	}

	private boolean allowTranscript = false;

	private void setAllowTranscript(boolean value) {
		this.allowTranscript = value;
	}

	private boolean isAllowTranscript() {
		return this.allowTranscript;
	}

	private CV cV;

	@OneToOne(mappedBy = "student", cascade = { CascadeType.ALL })
	public CV getCV() {
		return this.cV;
	}

	public void setCV(CV cV) {
		this.cV = cV;
	}

	private Transcript transcript;

	@OneToOne(mappedBy = "student", cascade = { CascadeType.ALL })
	public Transcript getTranscript() {
		return this.transcript;
	}

	public void setTranscript(Transcript transcript) {
		this.transcript = transcript;
	}

}
