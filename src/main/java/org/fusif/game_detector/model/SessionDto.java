package org.fusif.game_detector.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionDto implements Serializable {
    private Integer id;
    private Instant sessionStart;
    private Instant sessionStop;
    private Integer applicationId;
}
