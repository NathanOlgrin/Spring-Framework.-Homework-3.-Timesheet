package ru.gb.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.timesheet.model.Timesheet;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {

    // select * from timesheet where project_id = $1
    List<Timesheet> findByProjectId(Long projectId);

    //select * from timesheet where project_id = $1 or minutes = $2
    List<Timesheet> findByProjectIdOrMinutes(Long projectId, Integer minutes);

    //select * from timesheet where created_at > $1
    List<Timesheet> findByCreatedAtGreaterThan(LocalDate createdAt);

    //select * from timesheet where employee_id = $1
    List<Timesheet> findByEmployeeId(Long employeeId);

}
