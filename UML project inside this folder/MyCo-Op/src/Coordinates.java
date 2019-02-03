
public class Coordinates {
   private int streetNumber;
   
   private void setStreetNumber(int value) {
      this.streetNumber = value;
   }
   
   private int getStreetNumber() {
      return this.streetNumber;
   }
   
   private String streetName;
   
   private void setStreetName(String value) {
      this.streetName = value;
   }
   
   private String getStreetName() {
      return this.streetName;
   }
   
   private String city;
   
   private void setCity(String value) {
      this.city = value;
   }
   
   private String getCity() {
      return this.city;
   }
   
   private String postalCode;
   
   private void setPostalCode(String value) {
      this.postalCode = value;
   }
   
   private String getPostalCode() {
      return this.postalCode;
   }
   
   private String province;
   
   private void setProvince(String value) {
      this.province = value;
   }
   
   private String getProvince() {
      return this.province;
   }
   
   private String country;
   
   private void setCountry(String value) {
      this.country = value;
   }
   
   private String getCountry() {
      return this.country;
   }
   
   private String email;
   
   private void setEmail(String value) {
      this.email = value;
   }
   
   private String getEmail() {
      return this.email;
   }
   
   private int telephone;
   
   private void setTelephone(int value) {
      this.telephone = value;
   }
   
   private int getTelephone() {
      return this.telephone;
   }
   
   /**
    * <pre>
    *           1..1     1..1
    * Coordinates ------------------------- User
    *           contact        &lt;       user
    * </pre>
    */
   private User user;
   
   public void setUser(User value) {
      this.user = value;
   }
   
   public User getUser() {
      return this.user;
   }
   
   /**
    * <pre>
    *           1..1     1..1
    * Coordinates ------------------------- EventNotifcation
    *           coordinates        &lt;       eventNotifcation
    * </pre>
    */
   private EventNotifcation eventNotifcation;
   
   public void setEventNotifcation(EventNotifcation value) {
      this.eventNotifcation = value;
   }
   
   public EventNotifcation getEventNotifcation() {
      return this.eventNotifcation;
   }
   
   }
