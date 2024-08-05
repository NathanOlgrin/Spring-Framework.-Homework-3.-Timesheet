package ru.gb.timesheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.gb.timesheet.model.Employee;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.EmployeeRepository;
import ru.gb.timesheet.repository.ProjectRepository;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.time.LocalDate;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class TimesheetApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TimesheetApplication.class, args);
		ProjectRepository projectRepository = context.getBean(ProjectRepository.class);
		TimesheetRepository timesheetRepository = context.getBean(TimesheetRepository.class);
		EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);
		//EmployeeProjectRepository employeeProjectRepository = context.getBean(EmployeeProjectRepository.class);

		LocalDate createdAt = LocalDate.now();

		//Long key = 1L;

		for(int i = 1; i<=5; i++){
			Project project = new Project();
			project.setName(UUID.randomUUID().toString());


			Employee employee = new Employee();

			Random random = new Random();
			String firstName = random.ints(97, 123)
					.limit(10)
					.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
					.toString();
			String lastName = random.ints(97, 123)
					.limit(10)
					.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
					.toString();
			employee.setFirstName(firstName);
			employee.setLastName(lastName);
			/*
			if(random.nextBoolean()){
				EmployeesProjects employeesProjects = new EmployeesProjects();
				employeesProjects.setEmployee(employee);
				employeesProjects.setProject(project);
				employeesProjects.setKey(key);
				key++;

				employeeProjectRepository.save(employeesProjects);
			}

			employee.setKey((Set<EmployeesProjects>) employeeProjectRepository);
			project.setKey((Set<EmployeesProjects>) employeeProjectRepository);
			*/
			projectRepository.save(project);
			employeeRepository.save(employee);
		}


		for(int i = 1;i<=10;i++){
			createdAt = createdAt.plusDays(1);

			Timesheet timesheet = new Timesheet();
//			timesheet.setId((long) i);
			timesheet.setCreatedAt(createdAt);
			timesheet.setMinutes(ThreadLocalRandom.current().nextInt(100, 1000));
			timesheet.setProjectId(ThreadLocalRandom.current().nextLong(1,6));
			timesheet.setEmployeeId(ThreadLocalRandom.current().nextLong(1,6));
			timesheetRepository.save(timesheet);
		}
	}
}