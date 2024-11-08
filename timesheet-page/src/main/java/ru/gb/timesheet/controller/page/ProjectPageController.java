//package ru.gb.timesheet.controller.page;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import ru.gb.timesheet.controller.dto.ProjectPageDto;
//import ru.gb.timesheet.service.page.ProjectPageService;
//
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//@RequestMapping("/home/projects")
//@Tag(name = "Projects", description = "API Для работы с проектами")
//@RequiredArgsConstructor
//public class ProjectPageController {
//
//    private final ProjectPageService service;
//
//    @Operation(summary = "Get All Project", description = "Получить все проекты в отдельной html-странице")
//    @GetMapping
//    public String getAllProjects(Model model){
//        List<ProjectPageDto> projects = service.findAll();
//        model.addAttribute("projects", projects);
//        return "projects-page.html";
//    }
//
//    // GET /home/projects/{id}
//    @Operation(summary = "Get Project", description = "Получить проект по его идентификатору в отдельной html-странице")
//    @GetMapping("{id}")
//    public String getProjectPage(@PathVariable @Parameter(description = "Идентификатор проекта") Long id, Model model){
//        Optional<ProjectPageDto> projectsOpt = service.findById(id);
//        if(projectsOpt.isEmpty()){
//            return "not-found.html";
//        }
//        model.addAttribute("project", projectsOpt.get());
//        return "project-page.html";
//    }
//
//}
