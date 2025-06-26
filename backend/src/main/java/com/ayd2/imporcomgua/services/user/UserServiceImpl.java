package com.ayd2.imporcomgua.services.user;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayd2.imporcomgua.dto.user.NewUserRequestDTO;
import com.ayd2.imporcomgua.dto.user.UpdateUserRequestDTO;
import com.ayd2.imporcomgua.dto.user.UserResponseDTO;
import com.ayd2.imporcomgua.exceptions.NotFoundException;
import com.ayd2.imporcomgua.mappers.user.UserMapper;
import com.ayd2.imporcomgua.models.user.Role;
import com.ayd2.imporcomgua.models.user.User;
import com.ayd2.imporcomgua.repositories.user.RoleRepository;
import com.ayd2.imporcomgua.repositories.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponseDTO> getAllUsers() {
        final List<UserResponseDTO> users = userRepository.findAll()
                .stream()
                .map(userMapper::toUserResponseDTO)
                .toList();
        return users;
    }

    @Override
    public UserResponseDTO createUser(NewUserRequestDTO newUserRequestDTO) throws NotFoundException {
        final UUID roleId = newUserRequestDTO.roleId();
        final Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new NotFoundException("No existe el rol con id: " + roleId));
        final User user = userMapper.toUser(newUserRequestDTO);
        user.setRole(role);
        final String password = newUserRequestDTO.password();
        user.setPassword(passwordEncoder.encode(password));
        final User newUser = userRepository.save(user);
        return userMapper.toUserResponseDTO(newUser);
    }

    @Override
    public UserResponseDTO updateUser(UUID id, UpdateUserRequestDTO updateUserRequestDTO) throws NotFoundException {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No existe el usuario con id: " + id));
        userMapper.UpdateUserFromDTO(updateUserRequestDTO, user);
        final UUID roleId = updateUserRequestDTO.roleId();
        if (roleId != null) {
            final Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new NotFoundException("No existe el rol con id: " + roleId));
            user.setRole(role);
        }
        final String password = updateUserRequestDTO.password();
        if (password != null) {
            user.setPassword(passwordEncoder.encode(password));
        }
        final User updatedUser = userRepository.save(user);
        return userMapper.toUserResponseDTO(updatedUser);
    }

    @Override
    public void deleteUser(UUID id) throws NotFoundException {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No existe el usuario con id: " + id));
        user.setIsActive(false);
        userRepository.save(user);
    }
    
}
