package ru.gb.timesheet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.Employee;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> get(@PathVariable Long id){
        Optional<Employee> employee = service.getById(id);
        if(employee.isPresent()){
            return  ResponseEntity.status(HttpStatus.OK).body(employee.get());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping()
    public ResponseEntity<List<Employee>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @PostMapping()
    public ResponseEntity<Employee> create(@RequestBody Employee employee){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(employee));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/timesheets")
    public ResponseEntity<List<Timesheet>> findByEmployeeId(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findByEmployeeId(id));
    }
}
