
public class Application {
   private String/*No type specified!*/ coverLetter;
   
   private void setCoverLetter(String/*No type specified!*/ value) {
      this.coverLetter = value;
   }
   
   private String/*No type specified!*/ getCoverLetter() {
      return this.coverLetter;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * Application ------------------------- Student
    *           applications        &gt;       applicant
    * </pre>
    */
   private Student applicant;
   
   public void setApplicant(Student value) {
      this.applicant = value;
   }
   
   public Student getApplicant() {
      return this.applicant;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * Application ------------------------- JobDescription
    *           applications        &lt;       jobDescription
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
