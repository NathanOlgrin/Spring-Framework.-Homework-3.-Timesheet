package ru.gb.timesheet.service.page;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import ru.gb.timesheet.client.ProjectResponse;
import ru.gb.timesheet.client.TimesheetResponse;
import ru.gb.timesheet.controller.dto.TimesheetPageDto;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class TimesheetPageService {


    private final DiscoveryClient discoveryClient;
    public TimesheetPageService(DiscoveryClient discoveryClient){
        this.discoveryClient = discoveryClient;
    }

    private RestClient restClient(){
        List<ServiceInstance> instances = discoveryClient.getInstances("TIMESHEET-REST");
        int instancesCount = instances.size();
        int instanceIndex = ThreadLocalRandom.current().nextInt(0, instancesCount);
        ServiceInstance serviceInstance = instances.get(instanceIndex);

        String uri = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort();
        return RestClient.create(uri);
    }
    public Optional<TimesheetPageDto> findById(long id){

        try {
            TimesheetResponse timesheetResponse = restClient().get().uri("/timesheets/" + id).retrieve().body(TimesheetResponse.class);

            TimesheetPageDto timesheetPageDto = new TimesheetPageDto();

            timesheetPageDto.setId(String.valueOf(timesheetResponse.getId()));
            timesheetPageDto.setMinutes(String.valueOf(timesheetResponse.getMinutes()));
            timesheetPageDto.setProjectId(String.valueOf(timesheetResponse.getProjectId()));
            timesheetPageDto.setCreatedAt(String.valueOf(timesheetResponse.getCreatedAt().format(DateTimeFormatter.ISO_DATE)));

            ProjectResponse projectResponse = restClient().get().uri("/projects/" + timesheetResponse.getProjectId()).retrieve().body(ProjectResponse.class);

            timesheetPageDto.setProjectName(projectResponse.getName());
            return Optional.of(timesheetPageDto);
        } catch (HttpClientErrorException.NotFound e){
            return Optional.empty();
        }
    }

    public List<TimesheetPageDto> findAll(){
        //HTTP GET /timesheets

        List<TimesheetResponse> timesheets = null;
        int attempts = 5;
        while(attempts -- > 0){
            try {
                timesheets = restClient().get()
                        .uri("/timesheets")
                        .retrieve()
                        .body(new ParameterizedTypeReference<List<TimesheetResponse>>() {});
                break;
            } catch (HttpClientErrorException e){
                // ignore
            }
        }

        if(timesheets == null){
            throw new RuntimeException("oops");
        }

        List<TimesheetPageDto> result = new ArrayList<>();
        for (TimesheetResponse timesheet : timesheets) {
            TimesheetPageDto timesheetPageDto = new TimesheetPageDto();
            timesheetPageDto.setId(String.valueOf(timesheet.getId()));
            timesheetPageDto.setProjectId(String.valueOf(timesheet.getProjectId()));
            timesheetPageDto.setMinutes(String.valueOf(timesheet.getMinutes()));
            timesheetPageDto.setCreatedAt(String.valueOf(timesheet.getCreatedAt().format(DateTimeFormatter.ISO_DATE)));

            ProjectResponse projectResponse = restClient().get().uri("/projects/" + timesheet.getProjectId()).retrieve().body(ProjectResponse.class);

            timesheetPageDto.setProjectName(projectResponse.getName());

            result.add(timesheetPageDto);
        }

        return result;
    }

}
