
public class JobOffer {
   private ProofOfPlacement proof;
   
   private void setProof(ProofOfPlacement value) {
      this.proof = value;
   }
   
   private ProofOfPlacement getProof() {
      return this.proof;
   }
   
   private Contract contract;
   
   private void setContract(Contract value) {
      this.contract = value;
   }
   
   private Contract getContract() {
      return this.contract;
   }
   
   private ProgressReport progress;
   
   private void setProgress(ProgressReport value) {
      this.progress = value;
   }
   
   private ProgressReport getProgress() {
      return this.progress;
   }
   
   private TaxCreditForm taxCreditForm;
   
   private void setTaxCreditForm(TaxCreditForm value) {
      this.taxCreditForm = value;
   }
   
   private TaxCreditForm getTaxCreditForm() {
      return this.taxCreditForm;
   }
   
   private StudentEvaluation studentEvaluation;
   
   private void setStudentEvaluation(StudentEvaluation value) {
      this.studentEvaluation = value;
   }
   
   private StudentEvaluation getStudentEvaluation() {
      return this.studentEvaluation;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * JobOffer ------------------------- Student
    *           jobOffers        &gt;       intern
    * </pre>
    */
   private Student intern;
   
   public void setIntern(Student value) {
      this.intern = value;
   }
   
   public Student getIntern() {
      return this.intern;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * JobOffer ------------------------- JobDescription
    *           jobOffers        &gt;       jobDescription
    * </pre>
    */
   private JobDescription jobDescription;
   
   public void setJobDescription(JobDescription value) {
      this.jobDescription = value;
   }
   
   public JobDescription getJobDescription() {
      return this.jobDescription;
   }
   
   }
