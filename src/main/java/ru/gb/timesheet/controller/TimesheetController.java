package ru.gb.timesheet.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.service.TimesheetService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/timesheets")
@Tag(name = "Timesheets", description = "API Для работы с таймшитами")
public class TimesheetController {

    //GET - метод на получение - никогда не содержит тела (body)
    //POST - метод на создание
    //PUT - метод на полное изменение
    //PATH - метод на выборочное изменение
    //DELETE - метод на удаление


    //@GetMapping("/timesheets/{id}")    // получить конкретную запись по id
    //@DeleteMapping("/timesheets/{id}")    // удалить конкретную запись по id
    //@PutMapping("/timesheets/{id}")    // обновить конкретную запись по id

private final TimesheetService service;

public TimesheetController(TimesheetService service){
    this.service = service;
}

    @Operation(summary = "Get Timesheet", description = "Получить таймшит по его идентификатору")
    @GetMapping("/{id}")    // получить все записи
    public ResponseEntity<Timesheet> get(@PathVariable @Parameter(description = "Идентификатор таймшита") Long id){
        Optional<Timesheet> ts = service.getById(id);
        if(ts.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(ts.get());
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get All Timesheet", description = "Получить все таймшиты")
    @GetMapping()    // получить все записи
    public ResponseEntity<List<Timesheet>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @Operation(summary = "Get Timesheets After ?", description = "Получить все таймшиты, созданные после какой-то определенной даты")
    @GetMapping("?createAtAfter")
    public ResponseEntity <List<Timesheet>> getCreateAtAfter(@PathVariable @Parameter(description = "Дата, после которой нужны таймшиты") LocalDate createAtAfter) {
        if (!service.getCreateAtAfter(createAtAfter).isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body((service.getCreateAtAfter(createAtAfter)));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Get Timesheets Before ?", description = "Получить все таймшиты, созданные до какой-то определенной даты")
    @GetMapping("?createAtBefore")
    public ResponseEntity <List<Timesheet>> getCreateAtBefore(@PathVariable @Parameter(description = "Дата, до которой нужны таймшиты") LocalDate createAtBefore) {
        if (!service.getCreateAtBefore(createAtBefore).isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body((service.getCreateAtBefore(createAtBefore)));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Create Timesheet", description = "Создать таймшит")
    @PostMapping()  //создание нового ресурса
    public ResponseEntity<Timesheet> create(@RequestBody @Parameter(description = "Таймшит") Timesheet timesheet){
        // Location: /timesheets/sequence
        if(!service.create(timesheet).equals(null)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(timesheet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete Timesheet", description = "Удалить таймшит по его идентификатору")
    @DeleteMapping("/{id}")  //удаление конкретного ресурса
    public ResponseEntity<Void> delete(@PathVariable @Parameter(description = "Идентификатор таймшита") Long id){
        service.delete(id); // если нет - иногда посылают 404 Not Found

        // 204 No Content
        return ResponseEntity.noContent().build();
    }
}
