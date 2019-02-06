
public class ProgressReport extends Document {
   /**
    * <pre>
    *           0..*     1..1
    * ProgressReport ------------------------- CoOpJob
    *           progressReports        &lt;       coOpJob
    * </pre>
    */
   private CoOpJob coOpJob;
   
   public void setCoOpJob(CoOpJob value) {
      this.coOpJob = value;
   }
   
   public CoOpJob getCoOpJob() {
      return this.coOpJob;
   }
   
   }
