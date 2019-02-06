
public class JobDetail extends Document {
   /**
    * <pre>
    *           1..1     1..1
    * JobDetail ------------------------- CoOpJob
    *           jobDetail        &lt;       coOpJob
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
