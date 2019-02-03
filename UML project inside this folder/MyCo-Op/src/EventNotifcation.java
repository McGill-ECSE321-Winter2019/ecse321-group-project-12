
public class EventNotifcation extends Message {
   private String name;
   
   private void setName(String value) {
      this.name = value;
   }
   
   private String getName() {
      return this.name;
   }
   
   private int date;
   
   private void setDate(int value) {
      this.date = value;
   }
   
   private int getDate() {
      return this.date;
   }
   
   private Event type;
   
   private void setType(Event value) {
      this.type = value;
   }
   
   private Event getType() {
      return this.type;
   }
   
   /**
    * <pre>
    *           1..1     1..1
    * EventNotifcation ------------------------- Coordinates
    *           eventNotifcation        &gt;       coordinates
    * </pre>
    */
   private Coordinates coordinates;
   
   public void setCoordinates(Coordinates value) {
      this.coordinates = value;
   }
   
   public Coordinates getCoordinates() {
      return this.coordinates;
   }
   
   }
