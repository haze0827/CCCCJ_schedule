package com.sparta.schedule.controller;


import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.security.UserDetailsImpl;
import com.sparta.schedule.service.ScheduleService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }


    //생성
    @PostMapping
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl authentication){
        return scheduleService.createSchedule(requestDto, authentication);
    }


    //전체조회
    @GetMapping
    public List<ScheduleResponseDto> getAllSchedule(){
        return scheduleService.getAllSchedule();
    }


    //수정
    @PutMapping("/{id}")
    public Long updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl authentication) {
        return scheduleService.updateSchedule(id, requestDto, authentication);
    }


    //삭제
    @DeleteMapping("/{id}")
    public Long deleteSchedule(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl authentication){
        return scheduleService.deleteSchedule(id, authentication);
    }
}
