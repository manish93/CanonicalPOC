package CanonicalPOC;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name="Document")
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "documentClassificationType")
    protected String documentClassificationType;

    @XmlElement(name="documentCategoryType")
    protected String documentCategoryType;

    @XmlElement(name = "Liabilities")
    protected Liabilities liabilities;

    @XmlElement(name="mortgageLoan")
    protected MortgageLoan mortgageLoan;

    public String getDocumentClassificationType() {
        return documentClassificationType;
    }

    public void setDocumentClassificationType(String documentClassificationType) {
        this.documentClassificationType = documentClassificationType;
    }

    public String getDocumentCategoryType() {
        return documentCategoryType;
    }

    public void setDocumentCategoryType(String documentCategoryType) {
        this.documentCategoryType = documentCategoryType;
    }

    public Liabilities getLiabilities() {
        return liabilities;
    }

    public void setLiabilities(Liabilities liabilities) {
        this.liabilities = liabilities;
    }

    public MortgageLoan getMortgageLoan() {
        return mortgageLoan;
    }

    public void setMortgageLoan(MortgageLoan mortgageLoan) {
        this.mortgageLoan = mortgageLoan;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Document{");
        sb.append("documentClassificationType='").append(documentClassificationType).append('\'');
        sb.append(", documentCategoryType='").append(documentCategoryType).append('\'');
        sb.append(", liabilities=").append(liabilities);
        sb.append(", mortgageLoan=").append(mortgageLoan);
        sb.append('}');
        return sb.toString();
    }
}
