package ru.gb.timesheet.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Employees", description = "API Для работы с сотрудниками")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @Operation(summary = "Get Employee", description = "Получить данные о сотруднике по его идентификатору")
    @GetMapping("/{id}")
    public ResponseEntity<Employee> get(@PathVariable @Parameter(description = "Идентификатор сотрудника") Long id){
        Optional<Employee> employee = service.getById(id);
        if(employee.isPresent()){
            return  ResponseEntity.status(HttpStatus.OK).body(employee.get());
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get All Employees", description = "Получить данные обо всех сотрудниках")
    @GetMapping()
    public ResponseEntity<List<Employee>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @Operation(summary = "Create Employees", description = "Создать запись о сотруднике")
    @PostMapping()
    public ResponseEntity<Employee> create(@RequestBody @Parameter(description = "Данные сотрудника") Employee employee){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(employee));
    }

    @Operation(summary = "Delete Employee", description = "Удалить данные о сотруднике по его идентификатору")
    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete(@PathVariable @Parameter(description = "Идентификатор сотрудника") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get Employee Timesheets", description = "Получить все таймшиты сотрудника по его идентификатору")
    @GetMapping("/{id}/timesheets")
    public ResponseEntity<List<Timesheet>> findByEmployeeId(@PathVariable @Parameter(description = "Идентификатор сотрудника") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findByEmployeeId(id));
    }
}
