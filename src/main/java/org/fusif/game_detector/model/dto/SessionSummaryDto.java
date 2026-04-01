package org.fusif.game_detector.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionSummaryDto implements Serializable {
    private Integer id;
    private Instant sessionStart;
    private Instant sessionStop;
    private String applicationTitle;
    private String applicationAlias;
    private String applicationPath;
}
