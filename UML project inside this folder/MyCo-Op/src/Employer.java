import java.util.Set;
import java.util.HashSet;

public class Employer extends UserRole {
   /**
    * <pre>
    *           1..1     1..*
    * Employer ------------------------- CoOpJob
    *           employer        &gt;       coOpJobs
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
