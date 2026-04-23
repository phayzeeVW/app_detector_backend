package org.fusif.game_detector.controller;


import org.fusif.game_detector.model.dto.TableConfigDto;
import org.fusif.game_detector.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tableConfig")
public class ConfigController {
    ConfigService configService;

    @Autowired
    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    @GetMapping(value = "/read/{tableName}")
    public ResponseEntity<TableConfigDto> readTableConfig(@PathVariable String tableName) {
        String tableConfigJson = configService.readConfigFromFile(tableName);

        return ResponseEntity.ok(new TableConfigDto(tableName, tableConfigJson));
    }

    @PostMapping(value = "/write")
    public ResponseEntity<TableConfigDto> writeTableConfig(@RequestBody TableConfigDto tableConfigDto) {
        configService.writeConfigToFile(tableConfigDto);

        return ResponseEntity.ok(tableConfigDto);
    }
}

