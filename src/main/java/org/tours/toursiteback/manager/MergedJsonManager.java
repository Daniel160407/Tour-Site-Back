package org.tours.toursiteback.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public class MergedJsonManager {
    public static ObjectNode getMergedJsonArray(List<?> objectList1, List<?> objectList2, String arrayName1, String arrayName2) throws JsonProcessingException {
        JsonNode data = new ObjectMapper().readTree(new ObjectMapper().writeValueAsString(objectList1));
        JsonNode additionData = new ObjectMapper().readTree(new ObjectMapper().writeValueAsString(objectList2));

        ObjectNode mergedJsonNode = new ObjectMapper().createObjectNode();

        mergedJsonNode.set(arrayName1, data);
        mergedJsonNode.set(arrayName2, additionData);

        return mergedJsonNode;
    }
}
