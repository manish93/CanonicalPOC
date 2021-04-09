package CanonicalPOC;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@XmlRootElement(name="Extension")
public class Extension implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "Type")
    protected String type;

    @XmlElement(name="Value")
    protected String value;

    @XmlElement(name="Nested_Extension")
    protected NestedExtensions nestedExtensions;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public NestedExtensions getNestedExtensions() {
        return nestedExtensions;
    }

    public void setNestedExtensions(NestedExtensions nestedExtensions) {
        this.nestedExtensions = nestedExtensions;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Extension{");
        sb.append("type='").append(type).append('\'');
        sb.append(", value='").append(value).append('\'');
        sb.append(", nestedExtensions=").append(nestedExtensions);
        sb.append('}');
        return sb.toString();
    }
}
