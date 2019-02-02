import java.util.Set;
import java.util.HashSet;

public class Employer extends UserRole {
   /**
    * <pre>
    *           1..1     0..*
    * Employer ------------------------- JobDescription
    *           employer        &gt;       jobDescriptions
    * </pre>
    */
   private Set<JobDescription> jobDescriptions;
   
   public Set<JobDescription> getJobDescriptions() {
      if (this.jobDescriptions == null) {
         this.jobDescriptions = new HashSet<JobDescription>();
      }
      return this.jobDescriptions;
   }
   
   /**
    * <pre>
    *           0..*     0..*
    * Employer ------------------------> Student
    *           employer        &gt;       interestingStudents
    * </pre>
    */
   private Set<Student> interestingStudents;
   
   public Set<Student> getInterestingStudents() {
      if (this.interestingStudents == null) {
         this.interestingStudents = new HashSet<Student>();
      }
      return this.interestingStudents;
   }
   
   }
