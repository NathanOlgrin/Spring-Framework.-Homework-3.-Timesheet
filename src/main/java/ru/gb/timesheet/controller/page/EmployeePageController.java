package ru.gb.timesheet.controller.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.timesheet.controller.dto.EmployeePageDto;
import ru.gb.timesheet.controller.dto.ProjectPageDto;
import ru.gb.timesheet.service.page.EmployeePageService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/home/employees")
@RequiredArgsConstructor
public class EmployeePageController {

    private final EmployeePageService employeePageService;

    @GetMapping
    public String getAllEmployees(Model model){
        List<EmployeePageDto> employees = employeePageService.findAll();
        model.addAttribute("employees", employees);
        return "employees-page.html";
    }

    // GET /home/projects/{id}
    @GetMapping("{id}")
    public String getEmployeePage(@PathVariable Long id, Model model){
        Optional<EmployeePageDto> employeesOpt = employeePageService.findById(id);
        if(employeesOpt.isEmpty()){
            return "not-found.html";
        }
        model.addAttribute("employee", employeesOpt.get());
        return "employee-page.html";
    }
}
