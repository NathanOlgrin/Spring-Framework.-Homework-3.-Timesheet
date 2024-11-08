package ru.gb.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.timesheet.model.EmployeesProjects;

@Repository
public interface EmployeeProjectRepository extends JpaRepository<EmployeesProjects, Long> {
}
