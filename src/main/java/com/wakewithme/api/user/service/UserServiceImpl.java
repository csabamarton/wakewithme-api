package com.wakewithme.api.user.service;

import com.wakewithme.api.user.entity.User;
import com.wakewithme.api.user.exception.UserNotFoundException;
import com.wakewithme.api.user.mapper.UserMapper;
import com.wakewithme.api.user.repository.UserRepository;
import com.wakewithme.api.user.service.api.UserService;
import com.wakewithme.api.user.web.request.UserDto;
import com.wakewithme.api.user.web.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse registerUser(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = userMapper.toEntity(userDto);
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    @Override
    public UserResponse getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse updateUser(UUID id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        userMapper.updateEntity(user, userDto);
        User updatedUser = userRepository.save(user);
        return userMapper.toResponse(updatedUser);
    }

    @Override
    public void deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return userMapper.toResponse(user);
    }
}
