package ru.gb.timesheet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.service.TimesheetService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/timesheets")
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

    @GetMapping("/{id}")    // получить все записи
    public ResponseEntity<Timesheet> get(@PathVariable Long id){
        Optional<Timesheet> ts = service.getById(id);
        if(ts.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(ts.get());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping()    // получить все записи
    public ResponseEntity<List<Timesheet>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @PostMapping()  //создание нового ресурса
    public ResponseEntity<Timesheet> create(@RequestBody Timesheet timesheet){
        // Location: /timesheets/sequence
        if(!service.create(timesheet).equals(null)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(timesheet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")  //удаление конкретного ресурса
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id); // если нет - иногда посылают 404 Not Found

        // 204 No Content
        return ResponseEntity.noContent().build();
    }
}
