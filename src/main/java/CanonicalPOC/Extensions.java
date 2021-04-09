package CanonicalPOC;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "Extensions")
public class Extensions implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name="Extension")
    protected List<Extension> extension;

    public List<Extension> getExtension() {
        return extension;
    }

    public void setExtension(List<Extension> extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Extensions{");
        sb.append("extension=").append(extension);
        sb.append('}');
        return sb.toString();
    }
}
