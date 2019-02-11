package ca.mcgill.ecse321.coop.model;

import javax.persistence.Entity;
import java.sql.Date;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class CoOpJob {
	private Student student;

	@ManyToOne(optional = false)
	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	private Date startDate;

	private void setStartDate(Date value) {
		this.startDate = value;
	}

	private Date getStartDate() {
		return this.startDate;
	}

	private Date endDate;

	private void setEndDate(Date value) {
		this.endDate = value;
	}

	private Date getEndDate() {
		return this.endDate;
	}

	private String name;

	public void setName(String value) {
		this.name = value;
	}

	public String getName() {
		return this.name;
	}

	private boolean studentHasStarted;

	public void setStudentHasStarted(boolean value) {
		this.studentHasStarted = value;
	}

	public boolean isStudentHasStarted() {
		return this.studentHasStarted;
	}

	private boolean studentHasEnded;

	public void setStudentHasEnded(boolean value) {
		this.studentHasEnded = value;
	}

	public boolean isStudentHasEnded() {
		return this.studentHasEnded;
	}

	private Employer employer;

	@ManyToOne(optional = false)
	public Employer getEmployer() {
		return this.employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	private boolean studentOfferedAJobLater;

	public void setStudentOfferedAJobLater(boolean value) {
		this.studentOfferedAJobLater = value;
	}

	public boolean isStudentOfferedAJobLater() {
		return this.studentOfferedAJobLater;
	}

	private String jobId;

	public void setJobId(String value) {
		this.jobId = value;
	}

	@Id
	public String getJobId() {
		return this.jobId;
	}

	private Contract contract;

	@OneToOne(mappedBy = "coOpJob", cascade = { CascadeType.ALL }, optional = false)
	public Contract getContract() {
		return this.contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	private Set<ProgressReport> progressReports;

	@OneToMany(mappedBy = "coOpJob", cascade = { CascadeType.ALL })
	public Set<ProgressReport> getProgressReports() {
		return this.progressReports;
	}

	public void setProgressReports(Set<ProgressReport> progressReportss) {
		this.progressReports = progressReportss;
	}

	private StudentEvaluationByEmployer studentEvaluationByEmployer;

	@OneToOne(mappedBy = "coOpJob", cascade = { CascadeType.ALL }, optional = false)
	public StudentEvaluationByEmployer getStudentEvaluationByEmployer() {
		return this.studentEvaluationByEmployer;
	}

	public void setStudentEvaluationByEmployer(StudentEvaluationByEmployer studentEvaluationByEmployer) {
		this.studentEvaluationByEmployer = studentEvaluationByEmployer;
	}

	private TaxCreditForm taxCreditForm;

	@OneToOne(mappedBy = "coOpJob", cascade = { CascadeType.ALL }, optional = false)
	public TaxCreditForm getTaxCreditForm() {
		return this.taxCreditForm;
	}

	public void setTaxCreditForm(TaxCreditForm taxCreditForm) {
		this.taxCreditForm = taxCreditForm;
	}

	private ProofOfPlacement proofOfPlacement;

	@OneToOne(mappedBy = "coOpJob", cascade = { CascadeType.ALL }, optional = false)
	public ProofOfPlacement getProofOfPlacement() {
		return this.proofOfPlacement;
	}

	public void setProofOfPlacement(ProofOfPlacement proofOfPlacement) {
		this.proofOfPlacement = proofOfPlacement;
	}

	private JobDetail jobDetail;

	@OneToOne(mappedBy = "coOpJob", cascade = { CascadeType.ALL }, optional = false)
	public JobDetail getJobDetail() {
		return this.jobDetail;
	}

	public void setJobDetail(JobDetail jobDetail) {
		this.jobDetail = jobDetail;
	}

	private Set<Other> others;

	@OneToMany(mappedBy = "coOpJob", cascade = { CascadeType.ALL })
	public Set<Other> getOthers() {
		return this.others;
	}

	public void setOthers(Set<Other> otherss) {
		this.others = otherss;
	}

}

