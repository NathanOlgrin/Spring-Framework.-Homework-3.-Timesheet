package ru.gb.timesheet.repository;

import org.springframework.stereotype.Repository;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ProjectRepository {
    private static Long sequence = 1L;
    private final List<Project> projects = new ArrayList<>();

    private final TimesheetRepository timesheetRepository;

    public ProjectRepository(TimesheetRepository timesheetRepository) {
        this.timesheetRepository = timesheetRepository;
    }

    public Optional<Project> getById(Long id){
        return projects.stream().filter(it -> Objects.equals(it.getId(), id)).findFirst();
    }

    public List<Project> getAll(){
        return List.copyOf(projects);
    }

    public Project create(Project project){
        project.setId(sequence++);

        projects.add(project);
        return project;
    }

    public void delete(int id){
        projects.stream().filter(it -> Objects.equals(it.getId(), id)).findFirst().ifPresent(projects::remove);
    }

    public List<Timesheet> findProjectTimesheets(long idProject){
         return timesheetRepository.findProjectTimesheet(idProject);
    }
}
