package CanonicalPOC;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name="Liability")
public class Liability implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "borrowerSeqNumber")
    protected String borrowerSeqNumber;

    @XmlElement(name="monthlyPaymentAmount")
    protected String monthlyPaymentAmount;

    @XmlElement(name = "Extensions")
    protected Extensions extensions;

    public String getBorrowerSeqNumber() {
        return borrowerSeqNumber;
    }

    public void setBorrowerSeqNumber(String borrowerSeqNumber) {
        this.borrowerSeqNumber = borrowerSeqNumber;
    }

    public String getMonthlyPaymentAmount() {
        return monthlyPaymentAmount;
    }

    public void setMonthlyPaymentAmount(String monthlyPaymentAmount) {
        this.monthlyPaymentAmount = monthlyPaymentAmount;
    }

    public Extensions getExtensions() {
        return extensions;
    }

    public void setExtensions(Extensions extensions) {
        this.extensions = extensions;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Liability{");
        sb.append("borrowerSeqNumber='").append(borrowerSeqNumber).append('\'');
        sb.append(", monthlyPaymentAmount='").append(monthlyPaymentAmount).append('\'');
        sb.append(", extensions=").append(extensions);
        sb.append('}');
        return sb.toString();
    }
}
