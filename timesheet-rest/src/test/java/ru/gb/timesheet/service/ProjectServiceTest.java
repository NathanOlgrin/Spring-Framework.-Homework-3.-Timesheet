package ru.gb.timesheet.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.repository.ProjectRepository;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static reactor.core.publisher.Mono.when;

@ActiveProfiles("test")
@SpringBootTest
class ProjectServiceTest {


    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ProjectService projectService;
    @Test
    void getById_Empty() {
        //given
        assertFalse(projectRepository.existsById(2L));

        //then
        assertTrue(projectService.getById(2L).isEmpty());
    }

    @Test
    void getById_Present() {
        //given
        Project project = new Project();
        project.setName("111");
        project = projectRepository.save(project);

        //when
        Optional<Project> actual = projectService.getById(project.getId());

        //then
        assertTrue(actual.isPresent());
        assertEquals(actual.get().getId(), project.getId());
        assertEquals(actual.get().getName(), project.getName());
    }

}