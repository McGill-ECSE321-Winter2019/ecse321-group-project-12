
public class Other extends Document {
   /**
    * <pre>
    *           0..*     1..1
    * Other ------------------------- CoOpJob
    *           others        &lt;       coOpJob
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
