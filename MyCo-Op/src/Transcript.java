import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Transcript extends Document {
	private Student student;

	@OneToOne(optional = false)
	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
