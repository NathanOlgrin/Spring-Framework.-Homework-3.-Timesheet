package ru.gb.timesheet.service.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.timesheet.controller.dto.TimesheetPageDto;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.service.ProjectService;
import ru.gb.timesheet.service.TimesheetService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimesheetPageService {

    private final TimesheetService timesheetService;
    private final ProjectService projectService;
    public Optional<TimesheetPageDto> findById(long id){
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
        timesheetPageDto.setProjectId(String.valueOf(project.getId()));

        return timesheetPageDto;
    }

}
