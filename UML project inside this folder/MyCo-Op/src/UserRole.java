
public abstract class UserRole {
   private String password;
   
   private void setPassword(String value) {
      this.password = value;
   }
   
   private String getPassword() {
      return this.password;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * UserRole ------------------------- MyCoOp
    *           roles        &lt;       myCoop
    * </pre>
    */
   private MyCoOp myCoop;
   
   public void setMyCoop(MyCoOp value) {
      this.myCoop = value;
   }
   
   public MyCoOp getMyCoop() {
      return this.myCoop;
   }
   
   }
