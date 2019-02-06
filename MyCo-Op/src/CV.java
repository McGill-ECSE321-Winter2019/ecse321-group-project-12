
public class CV extends Document {
   /**
    * <pre>
    *           0..1     1..1
    * CV ------------------------- Student
    *           cV        &lt;       student
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
