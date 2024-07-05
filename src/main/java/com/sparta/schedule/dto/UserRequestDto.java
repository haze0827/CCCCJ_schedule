package com.sparta.schedule.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserRequestDto {
    @NotNull
    private String nickname;
    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[0-9]).{4,10}$", message = "알파벳 소문자와 숫자로 최소 4자 이상, 10자 이하로 입력해주세요.")
    private String userName;
    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,15}$", message = "알파벳 대소문자와 숫자로 최소 8자 이상, 15자 이하로 입력해주세요.")
    private String password;

    private String role;
}
