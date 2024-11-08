package ru.gb.timesheet.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "employee_project")
public class EmployeesProjects {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;


    @JoinColumn(name = "employee_id")
    Long employeeId;

    @JoinColumn(name = "project_id")
    Long projectId;
}
