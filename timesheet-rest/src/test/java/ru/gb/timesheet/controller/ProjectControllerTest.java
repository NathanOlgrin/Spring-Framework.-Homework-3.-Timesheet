package ru.gb.timesheet.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestClient;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.repository.ProjectRepository;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RestClientTest
class ProjectControllerTest {

    @Autowired
    ProjectRepository projectRepository;
    @LocalServerPort
    private int port;
    @Test
    void getById() {
        //given
        Project expected = new Project();
        expected.setName("111");
        expected = projectRepository.save(expected);

        // save (project)
        // GET /projects/1L => 200 OK

        RestClient restClient = RestClient.create("http://localhost:" + port);
        ResponseEntity<Project> actual = restClient.get()
                .uri("/projects/" + expected.getId())
                .retrieve()
                .toEntity(Project.class);
        //assert 200 OK
        assertEquals(HttpStatus.OK,actual.getStatusCode());

        Project responceBody = actual.getBody();
        assertNotNull(responceBody);
        assertEquals(expected.getId(), responceBody.getId());
        assertEquals(expected.getName(), responceBody.getName());

    }
}