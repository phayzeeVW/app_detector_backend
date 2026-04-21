package org.fusif.game_detector.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.fusif.game_detector.exception.ApplicationDetectorTechnicalException;

@Slf4j
public class Utils {
    public static boolean jsonIsValid(String json) throws ApplicationDetectorTechnicalException {
        ObjectMapper objectMapper = new ObjectMapper()
                .enable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS);

        try {
            objectMapper.readTree(json);
        } catch (JsonProcessingException e) {
            log.error("Error parsing JSON: ", e);

            return false;
        }

        return true;
    }
}
