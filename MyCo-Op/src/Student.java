import java.util.Set;
import java.util.HashSet;

public class Student extends UserRole {
   private CV cv;
   
   private void setCv(CV value) {
      this.cv = value;
   }
   
   private CV getCv() {
      return this.cv;
   }
   
   private Transcript transcript;
   
   private void setTranscript(Transcript value) {
      this.transcript = value;
   }
   
   private Transcript getTranscript() {
      return this.transcript;
   }
   
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
    * Student ------------------------- Interview
    *           student        &gt;       interviews
    * </pre>
    */
   private Set<Interview> interviews;
   
   public Set<Interview> getInterviews() {
      if (this.interviews == null) {
         this.interviews = new HashSet<Interview>();
      }
      return this.interviews;
   }
   
   /**
    * <pre>
    *           1..1     0..*
    * Student ------------------------- JobOffer
    *           intern        &lt;       jobOffers
    * </pre>
    */
   private Set<JobOffer> jobOffers;
   
   public Set<JobOffer> getJobOffers() {
      if (this.jobOffers == null) {
         this.jobOffers = new HashSet<JobOffer>();
      }
      return this.jobOffers;
   }
   
   }
