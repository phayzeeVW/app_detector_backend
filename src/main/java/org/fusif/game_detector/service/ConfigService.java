package org.fusif.game_detector.service;

import lombok.Getter;
import org.fusif.game_detector.exception.ApplicationDetectorTechnicalException;
import org.fusif.game_detector.model.dto.TableConfigDto;
import org.fusif.game_detector.utils.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ConfigService {
    @Value("${app.config}")
    @Getter
    private Path configPath;

    public Path writeConfigToFile(TableConfigDto tableConfigDto) {
        Path configFilePath = configPath.resolve(tableConfigDto.getTableName() + ".json");

        try {
            Files.writeString(configFilePath, tableConfigDto.getTableConfigJson());
        } catch (IOException e) {
            throw new ApplicationDetectorTechnicalException("Failed to write config file: " + configFilePath, e);
        }

        return configFilePath;
    }

    public String readConfigFromFile(String tableName) {
        Path configFilePath = configPath.resolve(tableName + ".json");

        try {
            String tableConfigJson = Files.readString(configFilePath);

            if (!Utils.jsonIsValid(tableConfigJson)) {
                throw new ApplicationDetectorTechnicalException("Invalid JSON in config file: " + configFilePath);
            }

            return tableConfigJson;
        } catch (IOException e) {
            throw new ApplicationDetectorTechnicalException("Failed to read config file: " + configFilePath, e);
        }
    }
}
