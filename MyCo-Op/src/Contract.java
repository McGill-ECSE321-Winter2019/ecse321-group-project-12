
public class Contract extends Document {
   private Student student;
   
   private void setStudent(Student value) {
      this.student = value;
   }
   
   private Student getStudent() {
      return this.student;
   }
   
   private JobOffer job;
   
   private void setJob(JobOffer value) {
      this.job = value;
   }
   
   private JobOffer getJob() {
      return this.job;
   }
   
   }
