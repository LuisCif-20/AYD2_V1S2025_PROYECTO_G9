package com.ayd2.imporcomgua.controllers.user;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayd2.imporcomgua.dto.user.NewUserAccountRequestDTO;
import com.ayd2.imporcomgua.dto.user.UpdateUserAccountRequestDTO;
import com.ayd2.imporcomgua.dto.user.UserAccountResponseDTO;
import com.ayd2.imporcomgua.exceptions.DuplicatedEntityException;
import com.ayd2.imporcomgua.exceptions.InvalidActionException;
import com.ayd2.imporcomgua.exceptions.NotFoundException;
import com.ayd2.imporcomgua.services.user.UserAccountService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1.0/user-accounts")
@RequiredArgsConstructor
// @PreAuthorize("hasAuthority('GERENTE_GENERAL')")
public class UserAccountController {

    private final UserAccountService userAccountService;

    @GetMapping
    public ResponseEntity<List<UserAccountResponseDTO>> getAllUsers() {
        final List<UserAccountResponseDTO> users = userAccountService.getAllUserAccounts();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/me")
    public ResponseEntity<UserAccountResponseDTO> getUserInfo() throws NotFoundException {
        final UserAccountResponseDTO user = userAccountService.getUserInfo();
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping
    public ResponseEntity<UserAccountResponseDTO> createUser(
            @RequestBody @Valid NewUserAccountRequestDTO newUserRequestDTO)
            throws NotFoundException, DuplicatedEntityException {
        final UserAccountResponseDTO user = userAccountService.createUserAccount(newUserRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserAccountResponseDTO> updateUser(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateUserAccountRequestDTO updateUserRequestDTO)
            throws NotFoundException, DuplicatedEntityException {
        final UserAccountResponseDTO user = userAccountService.updateUserAccount(id, updateUserRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) throws NotFoundException, InvalidActionException {
        userAccountService.deleteUserAccount(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
}
