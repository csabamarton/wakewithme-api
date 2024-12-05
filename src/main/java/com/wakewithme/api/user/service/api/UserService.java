package com.wakewithme.api.user.service.api;

import com.wakewithme.api.user.web.request.UserDto;
import com.wakewithme.api.user.web.request.UserUpdateRequest;
import com.wakewithme.api.user.web.response.UserResponse;

import java.util.UUID;

public interface UserService {
    UserResponse registerUser(UserDto userDto);

    UserResponse getUserById(UUID id);

    UserResponse updateUser(UUID id, UserUpdateRequest userDto);

    void deleteUser(UUID id);

    UserResponse getUserByEmail(String email);
}
