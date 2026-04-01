package org.fusif.game_detector.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.fusif.game_detector.entity.Application;
import org.fusif.game_detector.repository.IApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
@AllArgsConstructor
public class ApplicationService {
    IApplicationRepository iApplicationRepository;

    public Optional<Application> getApplicationById(Integer id) {
        return this.iApplicationRepository.findById(id);
    }

    public Optional<Application> getApplicationByTitle(String title) {
        return this.iApplicationRepository.findApplicationByTitle(title);
    }

    public Optional<Application> getApplicationByAlias(String alias) {
        return this.iApplicationRepository.findApplicationByAlias(alias);
    }

    public Optional<Application> getApplicationByPath(String path) {
        return this.iApplicationRepository.findApplicationByPath(path);
    }

    public List<Application> getApplicationsBySaveSessionFalse() {
        return this.iApplicationRepository.findApplicationsBySaveSessionFalse();
    }

    public List<Application> getApplicationsBySaveSessionTrue() {
        return this.iApplicationRepository.findApplicationsBySaveSessionTrue();
    }

    public List<Application> findAll() {
        return this.iApplicationRepository.findAll();
    }

    public void saveApplication(Application application) {
        this.iApplicationRepository.save(application);
    }

    public void saveApplications(List<Application> applications) {
        this.iApplicationRepository.saveAll(applications);
    }
}
