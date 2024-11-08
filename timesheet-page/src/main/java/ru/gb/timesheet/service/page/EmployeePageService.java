//package ru.gb.timesheet.service.page;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import ru.gb.timesheet.controller.dto.EmployeePageDto;
//import ru.gb.timesheet.model.Employee;
//import ru.gb.timesheet.model.Project;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class EmployeePageService {
//
//    private final EmployeeService employeeService;
//
//    public Optional<EmployeePageDto> findById(long id){
//        return employeeService.getById(id).map(this::convert);
//    }
//
//    public List<EmployeePageDto> findAll(){
//        return employeeService.getAll().stream().map(this::convert).toList();
//    }
//
//    private EmployeePageDto convert(Employee employee){
//        EmployeePageDto employeePageDto = new EmployeePageDto();
//
//        employeePageDto.setId(String.valueOf(employee.getId()));
//        employeePageDto.setFirstName(String.valueOf(employee.getFirstName()));
//        employeePageDto.setLastName(String.valueOf(employee.getLastName()));
//
//        return employeePageDto;
//    }
//}
