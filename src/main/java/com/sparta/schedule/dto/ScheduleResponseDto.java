package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.entity.User;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String content;
    private String userName;

    public ScheduleResponseDto(Schedule schedule){
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.userName = schedule.getUser().getUserName();
    }

    public ScheduleResponseDto(Long id, String title, String content, String userName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userName = userName;
    }
}

