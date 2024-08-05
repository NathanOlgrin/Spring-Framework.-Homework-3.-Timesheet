package ru.gb.timesheet.controller.page;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.timesheet.controller.dto.TimesheetPageDto;
import ru.gb.timesheet.service.page.TimesheetPageService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/home/timesheets")
@Tag(name = "Timesheets", description = "API Для работы с таймшитами")
@RequiredArgsConstructor
public class TimesheetPageController {

    private final TimesheetPageService service;

    @Operation(summary = "Get All Timesheet", description = "Получить все таймшиты в отдельной html-странице")
    @GetMapping
    public String getAllTimeshees(Model model){
        List<TimesheetPageDto> timesheets = service.findAll();
        model.addAttribute("timesheets", timesheets);
        return "timesheets-page.html";
    }

    // GET /home/timesheets/{id}
    @Operation(summary = "Get Timesheet", description = "Получить таймшит по его идентификатору в отдельной html-странице")
    @GetMapping("{id}")
    public String getTimesheetPage(@PathVariable @Parameter(description = "Идентификатор таймшита") Long id, Model model){
        Optional<TimesheetPageDto> timesheetOpt = service.findById(id);
        if(timesheetOpt.isEmpty()){
            return "not-found.html";
        }
        model.addAttribute("timesheet", timesheetOpt.get());
        return "timesheet-page.html";
    }

}
