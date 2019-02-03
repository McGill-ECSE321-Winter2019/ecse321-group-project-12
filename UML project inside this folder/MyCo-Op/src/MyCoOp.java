import java.util.Set;
import java.util.HashSet;

public class MyCoOp {
   /**
    * <pre>
    *           1..1     0..*
    * MyCoOp ------------------------- User
    *           myCoOp        &gt;       users
    * </pre>
    */
   private Set<User> users;
   
   public Set<User> getUsers() {
      if (this.users == null) {
         this.users = new HashSet<User>();
      }
      return this.users;
   }
   
   /**
    * <pre>
    *           1..1     0..*
    * MyCoOp ------------------------- UserRole
    *           myCoop        &gt;       roles
    * </pre>
    */
   private Set<UserRole> roles;
   
   public Set<UserRole> getRoles() {
      if (this.roles == null) {
         this.roles = new HashSet<UserRole>();
      }
      return this.roles;
   }
   
   }
