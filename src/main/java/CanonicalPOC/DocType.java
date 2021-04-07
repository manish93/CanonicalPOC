package CanonicalPOC;

import java.util.List;

public class DocType {

    private String docTypeNames;
    private List<FieldVO> fields;

    public String getDocTypeNames() {
        return docTypeNames;
    }

    public void setDocTypeNames(String docTypeNames) {
        this.docTypeNames = docTypeNames;
    }

    public List<FieldVO> getFields() {
        return fields;
    }

    public void setFields(List<FieldVO> fields) {
        this.fields = fields;
    }
}
