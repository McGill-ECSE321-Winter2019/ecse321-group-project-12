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
   
   private boolean allowCV = false;
   
   private void setAllowCV(boolean value) {
      this.allowCV = value;
   }
   
   private boolean isAllowCV() {
      return this.allowCV;
   }
   
   private boolean allowTranscript = false;
   
   private void setAllowTranscript(boolean value) {
      this.allowTranscript = value;
   }
   
   private boolean isAllowTranscript() {
      return this.allowTranscript;
   }
   
   /**
    * <pre>
    *           1..1     0..1
    * Student ------------------------- CV
    *           student        &gt;       cV
    * </pre>
    */
   private CV cV;
   
   public void setCV(CV value) {
      this.cV = value;
   }
   
   public CV getCV() {
      return this.cV;
   }
   
   /**
    * <pre>
    *           1..1     0..1
    * Student ------------------------- Transcript
    *           student        &gt;       transcript
    * </pre>
    */
   private Transcript transcript;
   
   public void setTranscript(Transcript value) {
      this.transcript = value;
   }
   
   public Transcript getTranscript() {
      return this.transcript;
   }
   
   }
