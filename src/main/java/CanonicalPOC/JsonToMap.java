package CanonicalPOC;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonToMap {

    public Map<String, List<FieldVO>> canonicalMapper(String filePath) {
        Map<String, List<FieldVO>> map = new HashMap<>();
        File newPath = new File(".");
        System.out.println(newPath.getAbsolutePath());
        try {
            String fileData = new String(Files.readAllBytes(Paths.get(filePath)));
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            CanonicalMapping mappings = mapper.readValue(fileData, CanonicalMapping.class);
            map = mappings.getDocTypes().stream().collect(Collectors.toMap(DocType::getDocTypeNames,DocType::getFields));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
