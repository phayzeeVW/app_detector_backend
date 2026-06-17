package org.fusif.game_detector.controller;

import org.fusif.game_detector.entity.Application;
import org.fusif.game_detector.model.dto.ApplicationWithSessionsDto;
import org.fusif.game_detector.model.dto.ApplicationWithoutSessionsDto;
import org.fusif.game_detector.model.dto.DtoHelper;
import org.fusif.game_detector.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<List<ApplicationWithoutSessionsDto>> getAllApplications() {
        List<Application> applications = applicationService.findAll();
        List<ApplicationWithoutSessionsDto> applicationWithoutSessionsDtoDtos = DtoHelper.toApplicationWithoutSessionsList(applications);

        return ResponseEntity.ok(applicationWithoutSessionsDtoDtos);
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<ApplicationWithoutSessionsDto> getApplicationById(@PathVariable Integer id) {
        Optional<Application> application = applicationService.getApplicationById(id);

        if (application.isPresent()) {
            ApplicationWithSessionsDto applicationDto = DtoHelper.mapApplicationToApplicationWithSessions(application.get());

            return ResponseEntity.ok(applicationDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/path/{path}")
    public ResponseEntity<ApplicationWithoutSessionsDto> getApplicationByPath(@PathVariable String path) {
        Optional<Application> application = applicationService.getApplicationByPath(path);

        if (application.isPresent()) {
            ApplicationWithoutSessionsDto applicationWithoutSessionsDto = DtoHelper.mapApplicationToApplicationWithoutSessions(application.get());

            return ResponseEntity.ok(applicationWithoutSessionsDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/alias/{alias}")
    public ResponseEntity<ApplicationWithoutSessionsDto> getApplicationByAlias(@PathVariable String alias) {
        Optional<Application> application = applicationService.getApplicationByAlias(alias);

        if (application.isPresent()) {
            ApplicationWithoutSessionsDto applicationWithoutSessionsDto = DtoHelper.mapApplicationToApplicationWithoutSessions(application.get());

            return ResponseEntity.ok(applicationWithoutSessionsDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/title/{title}")
    public ResponseEntity<ApplicationWithoutSessionsDto> getApplicationByTitle(@PathVariable String title) {
        Optional<Application> application = applicationService.getApplicationByTitle(title);

        if (application.isPresent()) {
            ApplicationWithoutSessionsDto applicationWithoutSessionsDto = DtoHelper.mapApplicationToApplicationWithoutSessions(application.get());

            return ResponseEntity.ok(applicationWithoutSessionsDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/{applicationId}")
    public ResponseEntity<ApplicationWithoutSessionsDto> updateApplication(@PathVariable Integer applicationId, @RequestBody ApplicationWithoutSessionsDto applicationWithoutSessionsDto) {
        Optional<Application> application = applicationService.getApplicationById(applicationId);

        if (application.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        applicationWithoutSessionsDto.setId(applicationId);

        Application updatedApplication = applicationService.saveApplication(
                DtoHelper.mapApplicationWithoutSessionsToEntity(applicationWithoutSessionsDto)
        );

        ApplicationWithoutSessionsDto updatedApplicationWithoutSessionsDto = DtoHelper.mapApplicationToApplicationWithoutSessions(updatedApplication);

        return ResponseEntity.ok(updatedApplicationWithoutSessionsDto);
    }
}
