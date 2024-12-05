package com.wakewithme.api.user.web;

import com.wakewithme.api.user.security.UserOrAdmin;
import com.wakewithme.api.user.service.api.UserService;
import com.wakewithme.api.user.web.documentation.UserApiDocumentation;
import com.wakewithme.api.user.web.request.UserUpdateRequest;
import com.wakewithme.api.user.web.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    @UserApiDocumentation.GetUserById
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID id) {
        UserResponse userResponse = userService.getUserById(id);
        return ResponseEntity.ok(userResponse);
    }

    @PutMapping("/{id}")
    @UserApiDocumentation.UpdateUser
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID id, @RequestBody UserUpdateRequest userUpdateRequest) {
        UserResponse updatedUser = userService.updateUser(id, userUpdateRequest);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    @UserApiDocumentation.DeleteUser
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
