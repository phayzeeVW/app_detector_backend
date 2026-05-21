package org.fusif.game_detector.controller;

import org.fusif.game_detector.entity.Application;
import org.fusif.game_detector.model.dto.ApplicationSummary;
import org.fusif.game_detector.model.dto.ApplicationWithSessionsDto;
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
    public ResponseEntity<List<ApplicationSummary>> getAllApplications() {
        List<Application> applications = applicationService.findAll();
        List<ApplicationSummary> applicationSummaryDtos = DtoHelper.toApplicationSummaryList(applications);

        return ResponseEntity.ok(applicationSummaryDtos);
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
    public ResponseEntity<ApplicationSummary> getApplicationByPath(@PathVariable String path) {
        Optional<Application> application = applicationService.getApplicationByPath(path);

        if (application.isPresent()) {
            ApplicationSummary applicationSummaryDto = DtoHelper.mapApplicationToApplicationSummary(application.get());

            return ResponseEntity.ok(applicationSummaryDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/alias/{alias}")
    public ResponseEntity<ApplicationSummary> getApplicationByAlias(@PathVariable String alias) {
        Optional<Application> application = applicationService.getApplicationByAlias(alias);

        if (application.isPresent()) {
            ApplicationSummary applicationSummaryDto = DtoHelper.mapApplicationToApplicationSummary(application.get());

            return ResponseEntity.ok(applicationSummaryDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/title/{title}")
    public ResponseEntity<ApplicationSummary> getApplicationByTitle(@PathVariable String title) {
        Optional<Application> application = applicationService.getApplicationByTitle(title);

        if (application.isPresent()) {
            ApplicationSummary applicationSummaryDto = DtoHelper.mapApplicationToApplicationSummary(application.get());

            return ResponseEntity.ok(applicationSummaryDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/{applicationId}")
    public ResponseEntity<ApplicationSummary> updateApplication(@PathVariable Integer applicationId, @RequestBody ApplicationSummary applicationSummary) {
        Optional<Application> application = applicationService.getApplicationById(applicationId);

        if (application.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        applicationSummary.setId(applicationId);

        Application updatedApplication = applicationService.saveApplication(
                DtoHelper.mapApplicationSummaryToEntity(applicationSummary)
        );

        ApplicationSummary updatedApplicationSummary = DtoHelper.mapApplicationToApplicationSummary(updatedApplication);

        return ResponseEntity.ok(updatedApplicationSummary);
    }
}
