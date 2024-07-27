package ru.gb.timesheet.service;

import org.springframework.stereotype.Service;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.ProjectRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public Optional<Project> getById(Long id){
        return repository.getById(id);
    }

    public List<Project> getAll(){
        return repository.getAll();
    }

    public Project create(Project project){
        return repository.create(project);
    }

    public void delete(int id){
        repository.delete(id);
    }

    public List<Timesheet> findProjectTimesheets(long idProject){
        return  repository.findProjectTimesheets(idProject);
    }
}
