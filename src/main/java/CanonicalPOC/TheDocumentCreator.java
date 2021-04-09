package CanonicalPOC;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class TheDocumentCreator {

    private static final String extsAbsPath  = "CanonicalPOC.Extensions";

    public static void main(String args[]) throws IllegalAccessException, InstantiationException {

        Document doc = Document.class.newInstance();
        JsonToMap jtm = new JsonToMap();
        /*
            The Json file is converted to a key value map, where Key is the Document name and value is a list of
            FieldVO objects
        */
        Map<String, List<FieldVO>> canonicalMap = jtm.canonicalMapper("mapping.json");
        populateDataToDocument(canonicalMap, doc);
        System.out.println(jtm.ObjecToJson(doc));
    }

    /**
     * From the Absolute path it separated the Class name
     * CanonicalPOC.Liabilities -> liabilities
     * @param canonicalPath
     * @return
     */
    private static String getClassName(String canonicalPath) {

        return getCamelCase(canonicalPath.substring(canonicalPath.lastIndexOf(".")+1));
    }

    /**
     * This method converts the first letter of the string to lower case and returns the new String
     * For e.g. ManishPant -> manishPant
     * Since fields and methods are case sensitive, this is used in getting the correct name
     * @param val
     * @return
     */
    private static String getCamelCase(String val) {

        return val.toLowerCase().charAt(0)+val.substring(1,val.length());
    }

    /**
     * This method Sets the data to those elements of the Document which do not have any hierarchy
     * @param canonicalMap
     * @param doc
     */
    private static void populateDataToDocument(Map<String, List<FieldVO>> canonicalMap, Document doc) {
            canonicalMap.keySet().stream().forEach(docName -> {
                List<FieldVO> list = canonicalMap.get(docName);
                Class docClass = doc.getClass();
                list.stream().forEach(vo -> {
                    try {
                        if (vo.getHierarchy() != null) {
                            //Hierarchy is fetched and split on the basis of a regex
                            List<String> list2 = new LinkedList<>(Arrays.asList(vo.getHierarchy().split("#")));
                            //Data population based on hierarchy
                            populateDocumentObject(list2,doc, vo);
                        } else {//Data is added when the hierarchy is absent
                            //Field is extracted to which the data has to be added
                            Field objectField = docClass.getDeclaredField(getClassName(vo.getCanonicalPath()));
                           //Haven't checked for primitive data. If there is a possibility, a check can be added
                            if (objectField.get(doc) == null) {
                                objectField.set(doc, Class.forName(vo.getCanonicalPath()).newInstance());
                            }
                            //If the complete path to the final field is provided, setProperty will add the value there
                            BeanUtils.setProperty(doc, getClassName(vo.getCanonicalPath()) + "." + getCamelCase(vo.getElementName()), "12345");
                            addExtensionData(vo, doc.getClass().getDeclaredField(getClassName(vo.getCanonicalPath())).get(doc));
                        }
                    } catch (NoSuchFieldException | IllegalAccessException | ClassNotFoundException | InstantiationException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
            });
    }

    private static void addExtensionData(FieldVO vo, Object obj) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        Class objClass = obj.getClass();
        try {
            Field extField = objClass.getDeclaredField(getClassName(extsAbsPath));
            if (extField.get(obj) == null) {
                extField.set(obj, Class.forName(extsAbsPath).newInstance());
            }
            Extensions exts = (Extensions) extField.get(obj);
            Extension ext = new Extension();
            ext.setValue(vo.getElementName());
            ext.setType(vo.getFieldName());

            if (exts.getExtension() == null) {
                exts.setExtension(new ArrayList<>());
            }
            exts.getExtension().add(ext);

            //Add Nested Extension
            NestedExtensions nstdExts = new NestedExtensions();
            NestedExtension nstdExt1 = new NestedExtension();
            nstdExt1.setType("OriginalValue");
            nstdExt1.setValue("10345");
            nstdExts.setNestedExtension(new ArrayList<>());
            nstdExts.getNestedExtension().add(nstdExt1);

            NestedExtension nstdExt2 = new NestedExtension();
            nstdExt2.setType("conf");
            nstdExt2.setValue("11234");
            nstdExts.getNestedExtension().add(nstdExt2);

            ext.setNestedExtensions(nstdExts);
            extField.set(obj, exts);
        } catch (NoSuchFieldException e) {}

    }

    /**
     * This method populates the data for hierarchy elements
     * @param hrkeyData
     * @param obj
     */
    private static void populateDocumentObject(List<String> hrkeyData, Object obj, FieldVO vo) {
        if (hrkeyData.size() == 1) {
            //Checks if it reached the last element of the chain, if yes, it sets the data and returns
            Class objClass = obj.getClass();
            try {
                Field objectField = objClass.getDeclaredField(hrkeyData.get(0));
                objectField.set(obj,"454545");
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return;
        } else {
            try {
                String elmnt = hrkeyData.get(0);
                Class objClass = obj.getClass();
                Field objectField = null;
                //$ sign indicates it is an element of list, so list is created if it is null and then data is added
                if (elmnt.startsWith("$")) {
                    int i = getIterationNumber(elmnt);
                    elmnt = elmnt.substring(elmnt.lastIndexOf("-")+1);
                    objectField = objClass.getDeclaredField(getClassName(elmnt));
                    if (objectField.get(obj) == null) {

                        List l = ArrayList.class.newInstance();
                        l.add(Class.forName(elmnt).newInstance());
                        objectField.set(obj, l);
                        // after each level is done the element is removed from the list, and the process is repeated
                        addExtensionData(vo, ((List)objectField.get(obj)).get(i));
                        hrkeyData.remove(0);
                        populateDocumentObject(hrkeyData,((List)objectField.get(obj)).get(i), vo);
                    } else if (objectField.get(obj) != null && ((List)objectField.get(obj)).size() <= i) {
                        //Adding another element to the list
                        List l = ((List)objectField.get(obj));
                        l.add(Class.forName(elmnt).newInstance());
                        objectField.set(obj, l);
                        addExtensionData(vo, ((List)objectField.get(obj)).get(i));
                        hrkeyData.remove(0);
                        populateDocumentObject(hrkeyData,((List)objectField.get(obj)).get(i), vo);
                    }
                } else {
                    objectField = objClass.getDeclaredField(getClassName(elmnt));
                    if (objectField.get(obj) == null) {
                        if (objectField.getType() == List.class) {
                            objectField.set(obj, ArrayList.class.newInstance());
                        } else {
                            objectField.set(obj, Class.forName(elmnt).newInstance());
                        }
                    }
                    addExtensionData(vo, obj);
                    hrkeyData.remove(0);
                    populateDocumentObject(hrkeyData,objectField.get(obj), vo);
                }

            } catch (NoSuchFieldException | IllegalAccessException | ClassNotFoundException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method returns the element number
     * for eg :  $0-CanonicalPOC.Liability | $1-CanonicalPOC.Liability -> it returns the liability number (0/1)
     * @param elmnt
     * @return
     */
    private static int getIterationNumber(String elmnt) {
        return Integer.parseInt(elmnt.substring(elmnt.indexOf("$")+1,elmnt.indexOf("-")));
    }

}
