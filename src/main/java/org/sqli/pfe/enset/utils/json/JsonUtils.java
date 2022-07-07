package org.sqli.pfe.enset.utils.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static org.sqli.pfe.enset.utils.constants.CommonConstants.EMPTY_STRING;

@Slf4j
public final class JsonUtils {

    public static Optional<JsonNode> convertJsonStringToJsonNode(String json) {
        try {
            return Optional.ofNullable(new ObjectMapper().readTree(json));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return Optional.empty();
        }
    }

    public static String getNodeValueAtPath(JsonNode node, String path) {
        return Optional.ofNullable(node)
                .map(n -> n.at(path).asText())
                .orElse(EMPTY_STRING);
    }

    public static String getNodeValueAtPath(String json, String path) {
        return convertJsonStringToJsonNode(json)
                .map(node -> getNodeValueAtPath(node, path))
                .orElse(EMPTY_STRING);
    }

}
