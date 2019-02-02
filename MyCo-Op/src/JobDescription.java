import java.util.Set;
import java.util.HashSet;

public class JobDescription {
   /**
    * <pre>
    *           0..*     1..1
    * JobDescription ------------------------- Employer
    *           jobDescriptions        &lt;       employer
    * </pre>
    */
   private Employer employer;
   
   public void setEmployer(Employer value) {
      this.employer = value;
   }
   
   public Employer getEmployer() {
      return this.employer;
   }
   
   private String name;
   
   private void setName(String value) {
      this.name = value;
   }
   
   private String getName() {
      return this.name;
   }
   
   private Contact contact;
   
   private void setContact(Contact value) {
      this.contact = value;
   }
   
   private Contact getContact() {
      return this.contact;
   }
   
   private boolean isRestricted = false;
   
   private void setIsRestricted(boolean value) {
      this.isRestricted = value;
   }
   
   private boolean isIsRestricted() {
      return this.isRestricted;
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
   
   private int salaryPerHour;
   
   private void setSalaryPerHour(int value) {
      this.salaryPerHour = value;
   }
   
   private int getSalaryPerHour() {
      return this.salaryPerHour;
   }
   
   private JobDetail details;
   
   private void setDetails(JobDetail value) {
      this.details = value;
   }
   
   private JobDetail getDetails() {
      return this.details;
   }
   
   /**
    * <pre>
    *           1..1     0..*
    * JobDescription ------------------------- Interview
    *           jobDescription        &gt;       interviews
    * </pre>
    */
   private Set<Interview> interviews;
   
   public Set<Interview> getInterviews() {
      if (this.interviews == null) {
         this.interviews = new HashSet<Interview>();
      }
      return this.interviews;
   }
   
   private int deadline;
   
   private void setDeadline(int value) {
      this.deadline = value;
   }
   
   private int getDeadline() {
      return this.deadline;
   }
   
   /**
    * <pre>
    *           1..1     0..*
    * JobDescription ------------------------- JobOffer
    *           jobDescription        &lt;       jobOffers
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
