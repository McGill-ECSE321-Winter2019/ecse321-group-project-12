import java.util.Set;
import java.util.HashSet;

public class Student extends UserRole {
   private Year year;
   
   private void setYear(Year value) {
      this.year = value;
   }
   
   private Year getYear() {
      return this.year;
   }
   
   private Faculty faculty;
   
   private void setFaculty(Faculty value) {
      this.faculty = value;
   }
   
   private Faculty getFaculty() {
      return this.faculty;
   }
   
   /**
    * <pre>
    *           1..1     0..*
    * Student ------------------------- CoOpJob
    *           coOpStudent        &lt;       coOpJobs
    * </pre>
    */
   private Set<CoOpJob> coOpJobs;
   
   public Set<CoOpJob> getCoOpJobs() {
      if (this.coOpJobs == null) {
         this.coOpJobs = new HashSet<CoOpJob>();
      }
      return this.coOpJobs;
   }
   
   }
