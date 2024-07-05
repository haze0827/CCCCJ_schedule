package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CommentRequestDto {

    @NotNull
    private String comment;
    @NotNull
    private Schedule schedule;
}
