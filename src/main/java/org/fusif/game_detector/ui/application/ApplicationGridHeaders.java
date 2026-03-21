package org.fusif.game_detector.ui.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplicationGridHeaders {
    ID("ID"),
    ALIAS("Alias"),
    PATH("Path"),
    TITLE("Title"),
    SAVE_SESSION("Save Session");

    private final String value;
}
