package com.ayd2.imporcomgua.services.user;

import java.util.List;
import java.util.UUID;

import com.ayd2.imporcomgua.dto.user.NewUserRequestDTO;
import com.ayd2.imporcomgua.dto.user.UpdateUserRequestDTO;
import com.ayd2.imporcomgua.dto.user.UserResponseDTO;
import com.ayd2.imporcomgua.exceptions.NotFoundException;

public interface UserService {
    
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO createUser(NewUserRequestDTO newUserRequestDTO) throws NotFoundException;
    UserResponseDTO updateUser(UUID id, UpdateUserRequestDTO updateUserRequestDTO) throws NotFoundException;
    void deleteUser(UUID id) throws NotFoundException;

}
