
public class PDFFile {
   /**
    * <pre>
    *           1..1     1..1
    * PDFFile ------------------------- Document
    *           pdf        &lt;       document
    * </pre>
    */
   private Document document;
   
   public void setDocument(Document value) {
      this.document = value;
   }
   
   public Document getDocument() {
      return this.document;
   }
   
   }
