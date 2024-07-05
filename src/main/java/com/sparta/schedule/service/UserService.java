package com.sparta.schedule.service;

import com.sparta.schedule.dto.UserRequestDto;
import com.sparta.schedule.entity.User;
import com.sparta.schedule.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signup(UserRequestDto userRequestDto) {
        String nickname = userRequestDto.getNickname();
        String userName = userRequestDto.getUserName();
        String password = userRequestDto.getPassword();
        String role = userRequestDto.getRole();

        Optional<User> checkUserName = userRepository.findByUserName(userName);
        if (checkUserName.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
        User user = new User(nickname, userName, password, role);
        userRepository.save(user);
    }
}
