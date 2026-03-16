package org.fusif.game_detector.utils;

import org.springframework.format.annotation.DurationFormat;
import org.springframework.format.datetime.standard.DurationFormatterUtils;

import java.time.Duration;
import java.time.Instant;

public class Utils {
    public static Duration getDurationBetweenInstants(Instant start, Instant end)  {
        return Duration.between(start, end);
    }

    public static String prettyPrintTimeBetweenInstants(Instant start, Instant end) {
        Duration duration = getDurationBetweenInstants(start, end);

        return DurationFormatterUtils.print(duration, DurationFormat.Style.COMPOSITE);
    }
}
