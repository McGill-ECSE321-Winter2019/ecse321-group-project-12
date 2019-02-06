
public class StudentEvaluationByEmployer extends Document {
   /**
    * <pre>
    *           1..1     1..1
    * StudentEvaluationByEmployer ------------------------- CoOpJob
    *           studentEvaluationByEmployer        &lt;       coOpJob
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
