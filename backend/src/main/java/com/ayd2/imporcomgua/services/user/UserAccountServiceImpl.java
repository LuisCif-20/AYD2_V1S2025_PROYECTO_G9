package com.ayd2.imporcomgua.services.user;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayd2.imporcomgua.dto.user.NewUserAccountRequestDTO;
import com.ayd2.imporcomgua.dto.user.UpdateUserAccountRequestDTO;
import com.ayd2.imporcomgua.dto.user.UserAccountResponseDTO;
import com.ayd2.imporcomgua.exceptions.DuplicatedEntityException;
import com.ayd2.imporcomgua.exceptions.NotFoundException;
import com.ayd2.imporcomgua.mappers.user.UserAccountMapper;
import com.ayd2.imporcomgua.models.user.Role;
import com.ayd2.imporcomgua.models.user.UserAccount;
import com.ayd2.imporcomgua.repositories.user.RoleRepository;
import com.ayd2.imporcomgua.repositories.user.UserAccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UserAccountServiceImpl implements UserAccountService {

    private final PasswordEncoder passwordEncoder;
    private final UserAccountRepository userAccountRepository;
    private final RoleRepository roleRepository;
    private final UserAccountMapper userAccountMapper;

    @Override
    public UserAccountResponseDTO getUserInfo() throws NotFoundException {
        final User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        final String email = user.getUsername();
        final UserAccount userAccount = userAccountRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("No existe el usuario con email: " + email));
        return userAccountMapper.toUserAccountResponseDTO(userAccount);
    }

    @Override
    public List<UserAccountResponseDTO> getAllUserAccounts() {
        final List<UserAccountResponseDTO> userAccounts = userAccountRepository.findAll()
                .stream()
                .map(userAccountMapper::toUserAccountResponseDTO)
                .toList();
        return userAccounts;
    }

    @Override
    public UserAccountResponseDTO createUserAccount(NewUserAccountRequestDTO newUserRequestDTO)
            throws NotFoundException, DuplicatedEntityException {
        final UUID roleId = newUserRequestDTO.roleId();
        final Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new NotFoundException("No existe el rol con id: " + roleId));
        final String email = newUserRequestDTO.email();
        if (userAccountRepository.existsByEmail(email)) {
            throw new DuplicatedEntityException("Ya existe una usuario con el email: " + email);
        }
        final UserAccount userAccount = userAccountMapper.toUserAccount(newUserRequestDTO);
        userAccount.setRole(role);
        final String password = newUserRequestDTO.password();
        userAccount.setPassword(passwordEncoder.encode(password));
        final UserAccount newUserAccount = userAccountRepository.save(userAccount);
        return userAccountMapper.toUserAccountResponseDTO(newUserAccount);
    }

    @Override
    public UserAccountResponseDTO updateUserAccount(UUID id, UpdateUserAccountRequestDTO updateUserRequestDTO)
            throws NotFoundException, DuplicatedEntityException {
        final UserAccount userAccount = userAccountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No existe el usuario con id: " + id));
        userAccountMapper.UpdateUserAccountFromDTO(updateUserRequestDTO, userAccount);
        final String email = updateUserRequestDTO.email();
        if (email != null && userAccountRepository.existsByEmailAndIdNot(email, id)) {
            throw new DuplicatedEntityException("Ya existe una usuario con el email: " + email);
        }
        final UUID roleId = updateUserRequestDTO.roleId();
        if (roleId != null) {
            final Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new NotFoundException("No existe el rol con id: " + roleId));
            userAccount.setRole(role);
        }
        final String password = updateUserRequestDTO.password();
        if (password != null) {
            userAccount.setPassword(passwordEncoder.encode(password));
        }
        final UserAccount updatedUserAccount = userAccountRepository.save(userAccount);
        return userAccountMapper.toUserAccountResponseDTO(updatedUserAccount);
    }

    @Override
    public void deleteUserAccount(UUID id) throws NotFoundException {
        final UserAccount userAccount = userAccountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No existe el usuario con id: " + id));
        userAccount.setIsActive(false);
        userAccountRepository.save(userAccount);
    }
    
}
