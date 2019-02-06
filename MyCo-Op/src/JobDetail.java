import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class JobDetail extends Document {
	private CoOpJob coOpJob;

	@OneToOne(optional = false)
	public CoOpJob getCoOpJob() {
		return this.coOpJob;
	}

	public void setCoOpJob(CoOpJob coOpJob) {
		this.coOpJob = coOpJob;
	}

}
