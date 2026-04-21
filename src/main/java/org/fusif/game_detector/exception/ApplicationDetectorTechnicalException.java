package org.fusif.game_detector.exception;

public class ApplicationDetectorTechnicalException extends RuntimeException {
    public ApplicationDetectorTechnicalException(String message) {
        super(message);
    }

    public ApplicationDetectorTechnicalException(String message, Throwable cause) {
        super(message, cause);
    }
}
