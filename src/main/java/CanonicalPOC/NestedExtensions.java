package CanonicalPOC;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name="NestedExceptions")
public class NestedExtensions implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name="nestedException")
    protected List<NestedExtension> nestedExtension;

    public List<NestedExtension> getNestedExtension() {
        return nestedExtension;
    }

    public void setNestedExtension(List<NestedExtension> nestedExtension) {
        this.nestedExtension = nestedExtension;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NestedExtensions{");
        sb.append("nestedExtension=").append(nestedExtension);
        sb.append('}');
        return sb.toString();
    }
}
