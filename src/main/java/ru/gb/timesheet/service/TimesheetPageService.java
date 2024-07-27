package ru.gb.timesheet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.timesheet.controller.TimesheetPageDto;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimesheetPageService {

    private final TimesheetService timesheetService;
    private final ProjectService projectService;
    public Optional<TimesheetPageDto> findbyId(long id){
        return timesheetService.getById(id).map(this::convert);
    }

    public List<TimesheetPageDto> findAll(){
        return timesheetService.getAll().stream().map(this::convert).toList();
    }

    private TimesheetPageDto convert(Timesheet timesheet){
        Project project = projectService.getById(timesheet.getProjectId()).get();

        TimesheetPageDto timesheetPageDto = new TimesheetPageDto();
        timesheetPageDto.setId(String.valueOf(timesheet.getId()));
        timesheetPageDto.setMinutes(String.valueOf(timesheet.getMinutes()));
        timesheetPageDto.setCreatedAt(timesheet.getCreatedAt().toString());
        timesheetPageDto.setProjectName(project.getName());

        return timesheetPageDto;
    }

}
