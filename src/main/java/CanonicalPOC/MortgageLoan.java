package CanonicalPOC;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name="Mortgage_Loan")
public class MortgageLoan implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "qualifyingRatePercent")
    protected String qualifyingRatePercent;

    @XmlElement(name="buydownTemporarySubsidyFundingIndicator")
    protected String buydownTemporarySubsidyFundingIndicator;

    @XmlElement(name = "LoanLevelCreditScoreValue")
    protected Integer loanLevelCreditScoreValue;

    @XmlElement(name="Extensions")
    protected Extensions extensions;

    public String getQualifyingRatePercent() {
        return qualifyingRatePercent;
    }

    public void setQualifyingRatePercent(String qualifyingRatePercent) {
        this.qualifyingRatePercent = qualifyingRatePercent;
    }

    public String getBuydownTemporarySubsidyFundingIndicator() {
        return buydownTemporarySubsidyFundingIndicator;
    }

    public void setBuydownTemporarySubsidyFundingIndicator(String buydownTemporarySubsidyFundingIndicator) {
        this.buydownTemporarySubsidyFundingIndicator = buydownTemporarySubsidyFundingIndicator;
    }

    public Integer getLoanLevelCreditScoreValue() {
        return loanLevelCreditScoreValue;
    }

    public void setLoanLevelCreditScoreValue(Integer loanLevelCreditScoreValue) {
        this.loanLevelCreditScoreValue = loanLevelCreditScoreValue;
    }

    public Extensions getExtensions() {
        return extensions;
    }

    public void setExtensions(Extensions extensions) {
        this.extensions = extensions;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MortgageLoan{");
        sb.append("qualifyingRatePercent='").append(qualifyingRatePercent).append('\'');
        sb.append(", buydownTemporarySubsidyFundingIndicator='").append(buydownTemporarySubsidyFundingIndicator).append('\'');
        sb.append(", loanLevelCreditScoreValue=").append(loanLevelCreditScoreValue);
        sb.append(", extensions=").append(extensions);
        sb.append('}');
        return sb.toString();
    }
}
