package ru.gb.timesheet.repository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.Timesheet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository //@Component для классов, работающих с данными
public class TimesheetRepository {

    private static Long sequence = 1L;
    private final List<Timesheet> timesheets = new ArrayList<>();

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
        timesheets.add(timesheet);
        return timesheet;
    }

    public void delete(Long id){
        timesheets.stream().filter(it -> Objects.equals(it.getId(), id)).findFirst().ifPresent(timesheets::remove);
    }

}
