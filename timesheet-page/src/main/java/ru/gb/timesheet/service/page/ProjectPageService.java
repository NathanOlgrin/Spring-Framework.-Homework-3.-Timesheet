//package ru.gb.timesheet.service.page;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import ru.gb.timesheet.controller.dto.ProjectPageDto;
//import ru.gb.timesheet.model.Project;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class ProjectPageService {
//
//    private final ProjectService projectService;
//    public Optional<ProjectPageDto> findById(long id){
//        return projectService.getById(id).map(this::convert);
//    }
//
//    public List<ProjectPageDto> findAll(){
//        return projectService.getAll().stream().map(this::convert).toList();
//    }
//
//    private ProjectPageDto convert(Project project){
//        ProjectPageDto projectPageDto = new ProjectPageDto();
//
//        projectPageDto.setId(String.valueOf(project.getId()));
//        projectPageDto.setName(String.valueOf(project.getName()));
//
//        return projectPageDto;
//    }
//
//}
