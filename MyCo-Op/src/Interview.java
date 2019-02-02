
public class Interview {
   private boolean hireStudent = false;
   
   private void setHireStudent(boolean value) {
      this.hireStudent = value;
   }
   
   private boolean isHireStudent() {
      return this.hireStudent;
   }
   
   private int interviewDate;
   
   private void setInterviewDate(int value) {
      this.interviewDate = value;
   }
   
   private int getInterviewDate() {
      return this.interviewDate;
   }
   
   private int interiewTime;
   
   private void setInteriewTime(int value) {
      this.interiewTime = value;
   }
   
   private int getInteriewTime() {
      return this.interiewTime;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * Interview ------------------------- Student
    *           interviews        &lt;       student
    * </pre>
    */
   private Student student;
   
   public void setStudent(Student value) {
      this.student = value;
   }
   
   public Student getStudent() {
      return this.student;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * Interview ------------------------- JobDescription
    *           interviews        &lt;       jobDescription
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
