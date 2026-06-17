package org.fusif.game_detector.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ApplicationWithoutSessionsDto implements Serializable {
    Integer id;
    Integer rawgGameId;
    String path;
    String title;
    String alias;
    Boolean saveSession;
    Integer numberOfSessions;
}