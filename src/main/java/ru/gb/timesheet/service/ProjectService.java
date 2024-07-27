package ru.gb.timesheet.service;

import org.springframework.stereotype.Service;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.ProjectRepository;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository repository;
    private final TimesheetRepository timesheetRepository;

    public ProjectService(ProjectRepository repository, TimesheetRepository timesheetRepository) {
        this.repository = repository;
        this.timesheetRepository = timesheetRepository;
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
        return timesheetRepository.findProjectTimesheet(idProject);
    }
}
