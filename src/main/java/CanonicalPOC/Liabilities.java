package CanonicalPOC;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="Liabilities")
public class Liabilities implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name="Liability")
    protected List<Liability> liability;

    public List<Liability> getLiability() {
        if(this.liability == null) {
            this.liability = new ArrayList<>();
        }
        return liability;
    }

    public void setLiability(List<Liability> liability) {
        this.liability = liability;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Liabilities{");
        sb.append("liability=").append(liability);
        sb.append('}');
        return sb.toString();
    }
}
