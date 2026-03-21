package org.fusif.game_detector.ui.session;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SessionGridHeaders {
    ID("ID"),
    APPLICATION_NAME("Application Alias"),
    APPLICATION_PATH("Application Path"),
    START_TIME("Start Time"),
    END_TIME("End Time"),
    DURATION("Duration");

    private final String value;
}
