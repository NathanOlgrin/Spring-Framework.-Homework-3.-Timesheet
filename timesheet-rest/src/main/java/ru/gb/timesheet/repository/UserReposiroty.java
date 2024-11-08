package ru.gb.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.timesheet.model.User;

import java.util.Optional;

@Repository
public interface UserReposiroty extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String username);
}
