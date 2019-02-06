import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Employer extends UserRole {
	private Set<CoOpJob> coOpJobs;

	@OneToMany(mappedBy = "employer")
	public Set<CoOpJob> getCoOpJobs() {
		return this.coOpJobs;
	}

	public void setCoOpJobs(Set<CoOpJob> coOpJobss) {
		this.coOpJobs = coOpJobss;
	}

	private Set<Student> archivedInterns;

	@OneToMany
	public Set<Student> getArchivedInterns() {
		return this.archivedInterns;
	}

	public void setArchivedInterns(Set<Student> archivedInternss) {
		this.archivedInterns = archivedInternss;
	}

}
