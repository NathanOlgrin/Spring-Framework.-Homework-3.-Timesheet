package ru.gb.timesheet.service;

import org.springframework.stereotype.Service;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.ProjectRepository;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.util.List;
import java.util.Optional;

@Service  // тоже самое, что и Component
public class TimesheetService {
    private final TimesheetRepository repository;

    public TimesheetService(TimesheetRepository timesheetRepository){
        this.repository = timesheetRepository;
    }

    public Optional<Timesheet> getById(Long id){
        return repository.getById(id);
    }

    public List<Timesheet> getAll(){
        return repository.getAll();
    }

    public Timesheet create(Timesheet timesheet){
            return repository.create(timesheet);
        }

    public void delete(Long id){
        repository.delete(id);
    }
}
