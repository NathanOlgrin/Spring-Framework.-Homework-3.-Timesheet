package ru.gb.timesheet.repository;


import org.springframework.stereotype.Repository;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.ProjectRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository //@Component для классов, работающих с данными
public class TimesheetRepository {

    private static Long sequence = 1L;
    private final List<Timesheet> timesheets = new ArrayList<>();
    private final ProjectRepository projectRepository;

    public TimesheetRepository(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Optional<Timesheet> getById(Long id){
        // select * from timesheets where id = $id
         return timesheets.stream().filter(it -> Objects.equals(it.getId(), id)).findFirst();
    }

    public List<Timesheet> getAll(){
        return List.copyOf(timesheets);
    }

    public Timesheet create(Timesheet timesheet){
        timesheet.setId(sequence++);
        timesheet.setCreatedAt(LocalDate.now());
        if(projectRepository.getById(timesheet.getProjectId()).isPresent()){
            timesheets.add(timesheet);
            return timesheet;
        } else {
            return null;
        }
    }

    public void delete(Long id){
        timesheets.stream().filter(it -> Objects.equals(it.getId(), id)).findFirst().ifPresent(timesheets::remove);
    }

}
