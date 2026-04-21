package org.fusif.game_detector.service;

import org.fusif.game_detector.exception.ApplicationDetectorTechnicalException;
import org.fusif.game_detector.model.dto.TableConfigDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ConfigServiceTest {
    @InjectMocks ConfigService configService;

    @TempDir Path tempDir;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(configService, "configPath", tempDir);
    }

    @Test
    void writeConfigToFileOverwriteIfExists() throws IOException {
        Path configFilePath = tempDir.resolve("tableName.json");
        Files.writeString(configFilePath, "oldContent");
        String tableColumnConfig = """
                {
                  "firstName": true,
                  "lastName": true,
                  "age": true,
                  "visits": true,
                  "status": true,
                  "progress": true
                }
                """;
        TableConfigDto tableConfig = new TableConfigDto("tableName", tableColumnConfig);

        configService.writeConfigToFile(tableConfig);

        assertEquals(tableColumnConfig, Files.readString(configFilePath));
    }

    @Test
    void writeConfigToFileCreateIfNotExists() throws IOException {
        String tableColumnConfig = """
                {
                  "firstName": true,
                  "lastName": true,
                  "age": true,
                  "visits": true,
                  "status": true,
                  "progress": true
                }
                """;
        TableConfigDto tableConfig = new TableConfigDto("tableName", tableColumnConfig);

        Path configFilePath = configService.writeConfigToFile(tableConfig);

        assertEquals(tableColumnConfig, Files.readString(configFilePath));
    }

    @Test
    void readConfigFromFileReturnsJsonifiedContent() throws IOException {
        String tableName = "tableName";
        String expectedTableConfigJson = """
                {
                  "firstName": true,
                  "lastName": true,
                  "age": true,
                  "visits": true,
                  "status": true,
                  "progress": true
                }
                """;
        Files.writeString(tempDir.resolve("tableName.json"), expectedTableConfigJson);

        String actualTableConfigJson = configService.readConfigFromFile(tableName);

        assertEquals(expectedTableConfigJson, actualTableConfigJson);
    }

    @Test
    void readConfigFromFileJsonNotValid() throws IOException {
        String tableName = "tableName";
        String expectedTableConfigJson = """
                {
                  "firstName": true,
                  "lastName" true
                  "age": true
                  "visits": true,
                  "status": true,
                  "progress": true
                }
                """;
        Files.writeString(tempDir.resolve("tableName.json"), expectedTableConfigJson);

        assertThrows(ApplicationDetectorTechnicalException.class, () -> configService.readConfigFromFile(tableName));
    }
}