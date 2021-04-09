package CanonicalPOC;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name="Nested_Extension")
public class NestedExtension implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "Type")
    protected String type;

    @XmlElement(name = "Value")
    protected String value;

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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NestedExtension{");
        sb.append("type='").append(type).append('\'');
        sb.append(", value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
