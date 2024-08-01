package ru.gb.timesheet.service;

import org.springframework.stereotype.Service;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service  // тоже самое, что и Component
public class TimesheetService {
    private final TimesheetRepository repository;

    public TimesheetService(TimesheetRepository timesheetRepository){
        this.repository = timesheetRepository;
    }

    public Optional<Timesheet> getById(Long id){
        return repository.findById(id);
    }

    public List<Timesheet> getAll(){
        return repository.findAll();
    }

    public Timesheet create(Timesheet timesheet){
            return repository.save(timesheet);
        }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public List<Timesheet> getCreateAtAfter(LocalDate createAtAfter) {
        return repository.findByCreatedAtGreaterThan(createAtAfter);
    }

    public List<Timesheet> getCreateAtBefore(LocalDate createAtBefore) {
        throw new UnsupportedOperationException();
        //return repository.createAtBefore(createAtBefore);
    }
}
