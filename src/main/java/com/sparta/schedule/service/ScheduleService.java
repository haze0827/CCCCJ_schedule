package com.sparta.schedule.service;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.entity.User;
import com.sparta.schedule.repository.ScheduleRepository;
import com.sparta.schedule.security.UserDetailsImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto, UserDetailsImpl userDetails) {
        User user = userDetails.getUser();

        Schedule schedule = new Schedule(requestDto, user);

        Schedule saveSchedule = scheduleRepository.save(schedule);

        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        return scheduleResponseDto;
    }

    public List<ScheduleResponseDto> getAllSchedule(){
        return scheduleRepository.findAll().stream().map(ScheduleResponseDto::new).toList();
    }

    public Long updateSchedule(Long id, ScheduleRequestDto requestDto, UserDetailsImpl userDetails) {
        User user = userDetails.getUser();

        Schedule schedule = findSchedule(id);
            if(!schedule.getUser().getId().equals(user.getId())){
                throw  new IllegalArgumentException("작성자만 수정할 수 있습니다.");
            } else {
                schedule.update(id, requestDto);
                return id;
            }
    }

    public Long deleteSchedule(Long id,UserDetailsImpl userDetails){
        User user = userDetails.getUser();

        Schedule schedule = findSchedule(id);
            if(!schedule.getUser().getId().equals(user.getId())){
                throw  new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
            }else {
                scheduleRepository.delete(schedule);
                return id;
            }
    }

    protected Schedule findSchedule(Long id){
        return scheduleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("일정이 존재하지 않습니다."));
    }
}
