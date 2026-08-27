package com.User.Service.service;

import com.User.Service.dto.UserRequestDto;
import com.User.Service.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto postUserDetails(UserRequestDto userRequestDto);
    List<UserResponseDto> getAllUserDetails(UserRequestDto userRequestDto);
    UserResponseDto getUserById(UserRequestDto userRequestDto);
    UserResponseDto updateUserByUsername(String userName);
    UserResponseDto deleteUser(String userName);


}
