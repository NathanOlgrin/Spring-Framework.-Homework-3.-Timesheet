package ru.gb.timesheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.ProjectRepository;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class TimesheetApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TimesheetApplication.class, args);
		ProjectRepository projectRepository = context.getBean(ProjectRepository.class);
		TimesheetRepository timesheetRepository = context.getBean(TimesheetRepository.class);

		LocalDate createdAt = LocalDate.now();

		for(int i = 1; i<=5; i++){
			Project project = new Project();
			project.setId((long) i);
			project.setName(UUID.randomUUID().toString());
			projectRepository.create(project);
		}

		for(int i = 1;i<=10;i++){
			createdAt = createdAt.plusDays(1);
			System.out.println(createdAt);

			Timesheet timesheet = new Timesheet();
			timesheet.setId((long) i);
			timesheet.setCreatedAt(createdAt);
			timesheet.setMinutes(ThreadLocalRandom.current().nextInt(100, 1000));
			timesheet.setProjectId(ThreadLocalRandom.current().nextLong(1,6));
			timesheetRepository.create(timesheet);
		}
	}
}