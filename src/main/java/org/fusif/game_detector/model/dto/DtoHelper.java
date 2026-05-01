package org.fusif.game_detector.model.dto;

import org.fusif.game_detector.entity.Application;
import org.fusif.game_detector.entity.Session;

import java.util.List;

public final class DtoHelper {
    private DtoHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static ApplicationWithSessionsDto mapApplicationToApplicationWithSessions(Application application) {
        if (application == null) {
            return null;
        }

        return ApplicationWithSessionsDto.builder()
                .id(application.getId())
                .rawgGameId(application.getRawgGame() != null ? application.getRawgGame().getId() : null)
                .path(application.getPath())
                .title(application.getTitle())
                .alias(application.getAlias())
                .saveSession(application.getSaveSession())
                .sessions(application.getSessions() != null
                        ? application.getSessions().stream().map(DtoHelper::mapSessionToSessionDto).toList()
                        : null)
                .build();
    }

    public static ApplicationDto mapApplicationToApplicationDto(Application application) {
        if (application == null) {
            return null;
        }

        return new ApplicationDto(
                application.getId(),
                application.getRawgGame() != null ? application.getRawgGame().getId() : null,
                application.getPath(),
                application.getTitle(),
                application.getAlias(),
                application.getSaveSession(),
                application.getSessions().size()
        );
    }

    public static Application mapApplicationDtoToEntity(ApplicationDto dto) {
        if (dto == null) {
            return null;
        }

        Application application = new Application();
        application.setId(dto.getId());
        application.setPath(dto.getPath());
        application.setTitle(dto.getTitle());
        application.setAlias(dto.getAlias());
        application.setSaveSession(dto.getSaveSession());

        return application;
    }

    public static SessionSummaryDto mapSessionToSummaryDto(Session session) {
        if (session == null) {
            return null;
        }

        return new SessionSummaryDto(
                session.getId(),
                session.getSessionStart(),
                session.getSessionStop(),
                session.getApplication().getTitle(),
                session.getApplication().getAlias(),
                session.getApplication().getPath()
        );
    }

    public static SessionDto mapSessionToSessionDto(Session session) {
        if (session == null) {
            return null;
        }

        return new SessionDto(
                session.getId(),
                session.getSessionStart(),
                session.getSessionStop(),
                session.getApplication() != null ? session.getApplication().getId() : null
        );
    }

    public static Session mapSessionDtoToEntity(SessionDto dto) {
        if (dto == null) {
            return null;
        }

        Session session = new Session();
        session.setId(dto.getId());
        session.setSessionStart(dto.getSessionStart());
        session.setSessionStop(dto.getSessionStop());

        return session;
    }

    public static List<SessionSummaryDto> toSessionSummaryDtoList(List<Session> sessions) {
        return sessions == null ? null : sessions.stream().map(DtoHelper::mapSessionToSummaryDto).toList();
    }

    public static List<SessionDto> toSessionDtoList(List<Session> sessions) {
        return sessions == null ? null : sessions.stream().map(DtoHelper::mapSessionToSessionDto).toList();
    }

    public static List<ApplicationDto> toApplicationDtoList(List<Application> applications) {
        return applications == null ? null : applications.stream().map(DtoHelper::mapApplicationToApplicationDto).toList();
    }
}