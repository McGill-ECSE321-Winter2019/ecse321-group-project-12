
public class Transcript extends Document {
   /**
    * <pre>
    *           0..1     1..1
    * Transcript ------------------------- Student
    *           transcript        &lt;       student
    * </pre>
    */
   private Student student;
   
   public void setStudent(Student value) {
      this.student = value;
   }
   
   public Student getStudent() {
      return this.student;
   }
   
   }
