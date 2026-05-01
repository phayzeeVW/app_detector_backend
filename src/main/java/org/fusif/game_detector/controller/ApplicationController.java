package org.fusif.game_detector.controller;

import org.fusif.game_detector.entity.Application;
import org.fusif.game_detector.model.dto.ApplicationDto;
import org.fusif.game_detector.model.dto.ApplicationWithSessionsDto;
import org.fusif.game_detector.model.dto.DtoHelper;
import org.fusif.game_detector.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
    ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping()
    public ResponseEntity<List<ApplicationDto>> getAllApplications() {
        List<Application> applications = applicationService.findAll();
        List<ApplicationDto> applicationDtos = DtoHelper.toApplicationDtoList(applications);

        return ResponseEntity.ok(applicationDtos);
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<ApplicationWithSessionsDto> getApplicationById(@PathVariable Integer id) {
        Optional<Application> application = applicationService.getApplicationById(id);

        if (application.isPresent()) {
            ApplicationWithSessionsDto applicationDto = DtoHelper.mapApplicationToApplicationWithSessions(application.get());

            return ResponseEntity.ok(applicationDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/path/{path}")
    public ResponseEntity<ApplicationDto> getApplicationByPath(@PathVariable String path) {
        Optional<Application> application = applicationService.getApplicationByPath(path);

        if (application.isPresent()) {
            ApplicationDto applicationDto = DtoHelper.mapApplicationToApplicationDto(application.get());

            return ResponseEntity.ok(applicationDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/alias/{alias}")
    public ResponseEntity<ApplicationDto> getApplicationByAlias(@PathVariable String alias) {
        Optional<Application> application = applicationService.getApplicationByAlias(alias);

        if (application.isPresent()) {
            ApplicationDto applicationDto = DtoHelper.mapApplicationToApplicationDto(application.get());

            return ResponseEntity.ok(applicationDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/title/{title}")
    public ResponseEntity<ApplicationDto> getApplicationByTitle(@PathVariable String title) {
        Optional<Application> application = applicationService.getApplicationByTitle(title);

        if (application.isPresent()) {
            ApplicationDto applicationDto = DtoHelper.mapApplicationToApplicationDto(application.get());

            return ResponseEntity.ok(applicationDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
