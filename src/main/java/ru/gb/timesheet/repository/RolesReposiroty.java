package ru.gb.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.timesheet.model.Roles;

@Repository
public interface RolesReposiroty extends JpaRepository<Roles, Long> {

}
