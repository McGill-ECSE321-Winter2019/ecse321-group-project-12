package ca.mcgill.ecse321.coop.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Other extends Document {
	private CoOpJob coOpJob;

	@ManyToOne(optional = false)
	public CoOpJob getCoOpJob() {
		return this.coOpJob;
	}

	public void setCoOpJob(CoOpJob coOpJob) {
		this.coOpJob = coOpJob;
	}

}
