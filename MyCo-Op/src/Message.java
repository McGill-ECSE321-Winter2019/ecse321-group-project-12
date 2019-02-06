import java.util.Set;
import java.util.HashSet;

public class Message {
   private String content;
   
   private void setContent(String value) {
      this.content = value;
   }
   
   private String getContent() {
      return this.content;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * Message ------------------------- User
    *           sentMessages        &lt;       sender
    * </pre>
    */
   private User sender;
   
   public void setSender(User value) {
      this.sender = value;
   }
   
   public User getSender() {
      return this.sender;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * Message ------------------------- User
    *           receivedMessages        &lt;       reciever
    * </pre>
    */
   private User reciever;
   
   public void setReciever(User value) {
      this.reciever = value;
   }
   
   public User getReciever() {
      return this.reciever;
   }
   
   /**
    * <pre>
    *           1..1     0..*
    * Message ------------------------> Document
    *           message        &gt;       attachements
    * </pre>
    */
   private Set<Document> attachements;
   
   public Set<Document> getAttachements() {
      if (this.attachements == null) {
         this.attachements = new HashSet<Document>();
      }
      return this.attachements;
   }
   
   private String messageId;
   
   private void setMessageId(String value) {
      this.messageId = value;
   }
   
   private String getMessageId() {
      return this.messageId;
   }
   
   }
