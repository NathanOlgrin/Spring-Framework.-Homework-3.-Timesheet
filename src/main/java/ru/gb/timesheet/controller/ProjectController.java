package ru.gb.timesheet.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.service.ProjectService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
@Tag(name = "Projects", description = "API Для работы с проектами")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @Operation(summary = "Get Project", description = "Получить проект по его идентификатору")
    @GetMapping("/{id}")
    public ResponseEntity<Project> get(@PathVariable @Parameter(description = "Идентификатор проекта") Long id){
        Optional<Project> project = service.getById(id);
        if(project.isPresent()){
            return  ResponseEntity.status(HttpStatus.OK).body(project.get());
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get All Project", description = "Получить все проекты")
    @GetMapping()
    public ResponseEntity<List<Project>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @Operation(summary = "Create Project", description = "Создать проект")
    @PostMapping()
    public ResponseEntity<Project> create(@RequestBody @Parameter(description = "Проект") Project project){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(project));
    }

    @Operation(summary = "Delete Project", description = "Удалить проект по его идентификатору")
    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete(@PathVariable @Parameter(description = "Идентификатор проекта") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get Project Timesheets", description = "Получить все таймшиты по идентификатору проекта")
    @GetMapping("/{id}/timesheets")
    public ResponseEntity<List<Timesheet>> findProjectTimesheets(@PathVariable @Parameter(description = "Идентификатор проекта") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findProjectTimesheets(id));
    }
}
