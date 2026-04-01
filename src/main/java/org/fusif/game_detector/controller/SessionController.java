package org.fusif.game_detector.controller;

import org.fusif.game_detector.entity.Session;
import org.fusif.game_detector.model.dto.DtoHelper;
import org.fusif.game_detector.model.dto.SessionSummaryDto;
import org.fusif.game_detector.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {
    SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping()
    public ResponseEntity<List<SessionSummaryDto>> getAllSessions() {
        List<Session> sessions = sessionService.findAll();
        List<SessionSummaryDto> sessionDtos = DtoHelper.toSessionSummaryDtoList(sessions);

        return ResponseEntity.ok(sessionDtos);
    }
}
