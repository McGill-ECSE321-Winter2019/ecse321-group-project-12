
public class Message {
   private String content;
   
   private void setContent(String value) {
      this.content = value;
   }
   
   private String getContent() {
      return this.content;
   }
   
   private Document attachement;
   
   private void setAttachement(Document value) {
      this.attachement = value;
   }
   
   private Document getAttachement() {
      return this.attachement;
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
   
   }
