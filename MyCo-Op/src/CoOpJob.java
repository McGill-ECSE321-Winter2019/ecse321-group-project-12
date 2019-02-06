import java.util.Set;
import java.util.HashSet;

public class CoOpJob {
   private String name;
   
   private void setName(String value) {
      this.name = value;
   }
   
   private String getName() {
      return this.name;
   }
   
   private boolean studentHasStarted;
   
   private void setStudentHasStarted(boolean value) {
      this.studentHasStarted = value;
   }
   
   private boolean isStudentHasStarted() {
      return this.studentHasStarted;
   }
   
   private boolean studentHasEnded;
   
   private void setStudentHasEnded(boolean value) {
      this.studentHasEnded = value;
   }
   
   private boolean isStudentHasEnded() {
      return this.studentHasEnded;
   }
   
   private int startDate;
   
   private void setStartDate(int value) {
      this.startDate = value;
   }
   
   private int getStartDate() {
      return this.startDate;
   }
   
   private int endDate;
   
   private void setEndDate(int value) {
      this.endDate = value;
   }
   
   private int getEndDate() {
      return this.endDate;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * CoOpJob ------------------------- Student
    *           coOpJobs        &gt;       coOpStudent
    * </pre>
    */
   private Student coOpStudent;
   
   public void setCoOpStudent(Student value) {
      this.coOpStudent = value;
   }
   
   public Student getCoOpStudent() {
      return this.coOpStudent;
   }
   
   /**
    * <pre>
    *           1..*     1..1
    * CoOpJob ------------------------- Employer
    *           coOpJobs        &lt;       employer
    * </pre>
    */
   private Employer employer;
   
   public void setEmployer(Employer value) {
      this.employer = value;
   }
   
   public Employer getEmployer() {
      return this.employer;
   }
   
   private boolean studentOfferedAJobLater;
   
   private void setStudentOfferedAJobLater(boolean value) {
      this.studentOfferedAJobLater = value;
   }
   
   private boolean isStudentOfferedAJobLater() {
      return this.studentOfferedAJobLater;
   }
   
   private String jobId;
   
   private void setJobId(String value) {
      this.jobId = value;
   }
   
   private String getJobId() {
      return this.jobId;
   }
   
   /**
    * <pre>
    *           1..1     1..1
    * CoOpJob ------------------------- Contract
    *           coOpJob        &gt;       contract
    * </pre>
    */
   private Contract contract;
   
   public void setContract(Contract value) {
      this.contract = value;
   }
   
   public Contract getContract() {
      return this.contract;
   }
   
   /**
    * <pre>
    *           1..1     0..*
    * CoOpJob ------------------------- ProgressReport
    *           coOpJob        &gt;       progressReports
    * </pre>
    */
   private Set<ProgressReport> progressReports;
   
   public Set<ProgressReport> getProgressReports() {
      if (this.progressReports == null) {
         this.progressReports = new HashSet<ProgressReport>();
      }
      return this.progressReports;
   }
   
   /**
    * <pre>
    *           1..1     1..1
    * CoOpJob ------------------------- StudentEvaluationByEmployer
    *           coOpJob        &gt;       studentEvaluationByEmployer
    * </pre>
    */
   private StudentEvaluationByEmployer studentEvaluationByEmployer;
   
   public void setStudentEvaluationByEmployer(StudentEvaluationByEmployer value) {
      this.studentEvaluationByEmployer = value;
   }
   
   public StudentEvaluationByEmployer getStudentEvaluationByEmployer() {
      return this.studentEvaluationByEmployer;
   }
   
   /**
    * <pre>
    *           1..1     1..1
    * CoOpJob ------------------------- TaxCreditForm
    *           coOpJob        &gt;       taxCreditForm
    * </pre>
    */
   private TaxCreditForm taxCreditForm;
   
   public void setTaxCreditForm(TaxCreditForm value) {
      this.taxCreditForm = value;
   }
   
   public TaxCreditForm getTaxCreditForm() {
      return this.taxCreditForm;
   }
   
   /**
    * <pre>
    *           1..1     1..1
    * CoOpJob ------------------------- ProofOfPlacement
    *           coOpJob        &gt;       proofOfPlacement
    * </pre>
    */
   private ProofOfPlacement proofOfPlacement;
   
   public void setProofOfPlacement(ProofOfPlacement value) {
      this.proofOfPlacement = value;
   }
   
   public ProofOfPlacement getProofOfPlacement() {
      return this.proofOfPlacement;
   }
   
   /**
    * <pre>
    *           1..1     1..1
    * CoOpJob ------------------------- JobDetail
    *           coOpJob        &gt;       jobDetail
    * </pre>
    */
   private JobDetail jobDetail;
   
   public void setJobDetail(JobDetail value) {
      this.jobDetail = value;
   }
   
   public JobDetail getJobDetail() {
      return this.jobDetail;
   }
   
   /**
    * <pre>
    *           1..1     0..*
    * CoOpJob ------------------------- Other
    *           coOpJob        &gt;       others
    * </pre>
    */
   private Set<Other> others;
   
   public Set<Other> getOthers() {
      if (this.others == null) {
         this.others = new HashSet<Other>();
      }
      return this.others;
   }
   
   }
