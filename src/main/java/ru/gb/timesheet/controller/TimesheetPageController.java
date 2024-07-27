package ru.gb.timesheet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.timesheet.service.TimesheetPageService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/home/timesheets")
@RequiredArgsConstructor
public class TimesheetPageController {

    private final TimesheetPageService service;

    @GetMapping
    public String getAllTimeshees(Model model){
        List<TimesheetPageDto> timesheets = service.findAll();
        model.addAttribute("timesheets", timesheets);
        return "timesheets-page.html";
    }

    // GET /home/timesheets/{id}
    @GetMapping("{id}")
    public String getTimesheetPage(@PathVariable Long id, Model model){
        Optional<TimesheetPageDto> timesheetOpt = service.findbyId(id);
        if(timesheetOpt.isEmpty()){
            return "not-found.html";
        }
        model.addAttribute("timesheet", timesheetOpt.get());
        return "timesheet-page.html";
    }

}
