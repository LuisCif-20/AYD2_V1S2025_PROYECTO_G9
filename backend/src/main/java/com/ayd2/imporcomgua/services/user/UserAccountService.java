package com.ayd2.imporcomgua.services.user;

import java.util.List;
import java.util.UUID;

import com.ayd2.imporcomgua.dto.user.NewUserAccountRequestDTO;
import com.ayd2.imporcomgua.dto.user.UpdateUserAccountRequestDTO;
import com.ayd2.imporcomgua.dto.user.UserAccountResponseDTO;
import com.ayd2.imporcomgua.exceptions.DuplicatedEntityException;
import com.ayd2.imporcomgua.exceptions.NotFoundException;

public interface UserAccountService {
    
    List<UserAccountResponseDTO> getAllUserAccounts();
    UserAccountResponseDTO createUserAccount(NewUserAccountRequestDTO newUserAccountRequestDTO)
            throws NotFoundException, DuplicatedEntityException;
    UserAccountResponseDTO updateUserAccount(UUID id, UpdateUserAccountRequestDTO updateUserAccountRequestDTO)
            throws NotFoundException, DuplicatedEntityException;
    void deleteUserAccount(UUID id) throws NotFoundException;

}
