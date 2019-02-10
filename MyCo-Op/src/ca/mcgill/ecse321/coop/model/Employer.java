package ca.mcgill.ecse321.coop.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Employer extends User {
	private Set<Student> archivedIntern;

	@OneToMany
	public Set<Student> getArchivedIntern() {
		return this.archivedIntern;
	}

	public void setArchivedIntern(Set<Student> archivedInterns) {
		this.archivedIntern = archivedInterns;
	}

	private Set<CoOpJob> coOpJobs;

	@OneToMany(mappedBy = "employer")
	public Set<CoOpJob> getCoOpJobs() {
		return this.coOpJobs;
	}

	public void setCoOpJobs(Set<CoOpJob> coOpJobss) {
		this.coOpJobs = coOpJobss;
	}

}
