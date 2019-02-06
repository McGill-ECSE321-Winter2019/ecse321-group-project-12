import java.util.Set;
import java.util.HashSet;

public class User {
   private String username;
   
   private void setUsername(String value) {
      this.username = value;
   }
   
   private String getUsername() {
      return this.username;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * User ------------------------- MyCoOp
    *           users        &lt;       myCoOp
    * </pre>
    */
   private MyCoOp myCoOp;
   
   public void setMyCoOp(MyCoOp value) {
      this.myCoOp = value;
   }
   
   public MyCoOp getMyCoOp() {
      return this.myCoOp;
   }
   
   /**
    * <pre>
    *           1..1     1..1
    * User ------------------------- Address
    *           user        &gt;       contact
    * </pre>
    */
   private Address contact;
   
   public void setContact(Address value) {
      this.contact = value;
   }
   
   public Address getContact() {
      return this.contact;
   }
   
   /**
    * <pre>
    *           1..1     1..2
    * User ------------------------> UserRole
    *           user        &gt;       roles
    * </pre>
    */
   private Set<UserRole> roles;
   
   public Set<UserRole> getRoles() {
      if (this.roles == null) {
         this.roles = new HashSet<UserRole>();
      }
      return this.roles;
   }
   
   /**
    * <pre>
    *           1..1     0..*
    * User ------------------------- Message
    *           sender        &gt;       sentMessages
    * </pre>
    */
   private Set<Message> sentMessages;
   
   public Set<Message> getSentMessages() {
      if (this.sentMessages == null) {
         this.sentMessages = new HashSet<Message>();
      }
      return this.sentMessages;
   }
   
   /**
    * <pre>
    *           1..1     0..*
    * User ------------------------- Message
    *           reciever        &gt;       receivedMessages
    * </pre>
    */
   private Set<Message> receivedMessages;
   
   public Set<Message> getReceivedMessages() {
      if (this.receivedMessages == null) {
         this.receivedMessages = new HashSet<Message>();
      }
      return this.receivedMessages;
   }
   
   }
