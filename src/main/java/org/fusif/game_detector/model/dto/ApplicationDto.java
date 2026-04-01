package org.fusif.game_detector.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDto implements Serializable {
    Integer id;
    Integer rawgGameId;
    String path;
    String title;
    String alias;
    Boolean saveSession;
    List<SessionDto> sessions;
}
