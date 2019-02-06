import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ProgressReport extends Document {
	private CoOpJob coOpJob;

	@ManyToOne(optional = false)
	public CoOpJob getCoOpJob() {
		return this.coOpJob;
	}

	public void setCoOpJob(CoOpJob coOpJob) {
		this.coOpJob = coOpJob;
	}

}