package com.sparta.schedule.dto;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class ScheduleRequestDto {
    private String title;
    private String content;
}
