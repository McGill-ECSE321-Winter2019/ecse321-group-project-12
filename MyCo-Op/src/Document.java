
public abstract class Document {
   private User author;
   
   public void setAuthor(User value) {
      this.author = value;
   }
   
   public User getAuthor() {
      return this.author;
   }
   
   /**
    * <pre>
    *           1..1     1..1
    * Document ------------------------- PDFFile
    *           document        &gt;       pdf
    * </pre>
    */
   private PDFFile pdf;
   
   public void setPdf(PDFFile value) {
      this.pdf = value;
   }
   
   public PDFFile getPdf() {
      return this.pdf;
   }
   
   private String documentId;
   
   private void setDocumentId(String value) {
      this.documentId = value;
   }
   
   private String getDocumentId() {
      return this.documentId;
   }
   
   }
