package ru.gb.timesheet.controller.dto;

import lombok.Data;

@Data
public class TimesheetPageDto {

    private String id;
    private String minutes;
    private String createdAt;
    private String projectId;
    private String projectName;

}
