package ru.gb.timesheet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.timesheet.service.ProjectPageService;
import ru.gb.timesheet.service.TimesheetPageService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/home/projects")
@RequiredArgsConstructor
public class ProjectPageController {

    private final ProjectPageService service;

    @GetMapping
    public String getAllProjects(Model model){
        List<ProjectPageDto> projects = service.findAll();
        model.addAttribute("projects", projects);
        return "projects-page.html";
    }

    // GET /home/projects/{id}
    @GetMapping("{id}")
    public String getProjectPage(@PathVariable Long id, Model model){
        Optional<ProjectPageDto> projectsOpt = service.findById(id);
        if(projectsOpt.isEmpty()){
            return "not-found.html";
        }
        model.addAttribute("project", projectsOpt.get());
        return "project-page.html";
    }

}
