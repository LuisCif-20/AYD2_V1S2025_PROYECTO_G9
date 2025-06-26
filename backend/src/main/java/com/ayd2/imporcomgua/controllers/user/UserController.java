package com.ayd2.imporcomgua.controllers.user;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayd2.imporcomgua.dto.user.NewUserRequestDTO;
import com.ayd2.imporcomgua.dto.user.UpdateUserRequestDTO;
import com.ayd2.imporcomgua.dto.user.UserResponseDTO;
import com.ayd2.imporcomgua.exceptions.NotFoundException;
import com.ayd2.imporcomgua.services.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1.0/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        final List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(
            @RequestBody @Valid NewUserRequestDTO newUserRequestDTO) throws NotFoundException {
        final UserResponseDTO user = userService.createUser(newUserRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateUserRequestDTO updateUserRequestDTO) throws NotFoundException {
        final UserResponseDTO user = userService.updateUser(id, updateUserRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) throws NotFoundException {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
}
