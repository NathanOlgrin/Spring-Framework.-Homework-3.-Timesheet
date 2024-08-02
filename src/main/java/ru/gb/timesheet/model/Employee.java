package ru.gb.timesheet.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include
    private Long id;
    private String firstName;
    private String lastName;
    /*
    @ManyToMany
            @JoinTable(
                    name = "employee_projects",
                    joinColumns = @JoinColumn(name = "employee_id"),
                    inverseJoinColumns = @JoinColumn(name = "project_id")
            )
    Set<Project> projectSet;

    @OneToMany(mappedBy = "employee")
    Set<EmployeesProjects> key;*/
}
