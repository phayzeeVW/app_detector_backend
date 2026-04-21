package org.fusif.game_detector.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableConfigDto {
    String tableName;
    String tableConfigJson;
}
