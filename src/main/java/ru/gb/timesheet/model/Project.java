package ru.gb.timesheet.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include
    private Long id;
    private String name;

    /*
    @ManyToMany(mappedBy = "projectSet")
    Set<Employee> employeeSet;

    @OneToMany(mappedBy = "project")
    Set<EmployeesProjects> key;*/
}
