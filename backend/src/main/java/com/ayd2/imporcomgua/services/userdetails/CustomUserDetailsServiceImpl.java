package com.ayd2.imporcomgua.services.userdetails;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ayd2.imporcomgua.models.user.Role;
import com.ayd2.imporcomgua.models.user.UserAccount;
import com.ayd2.imporcomgua.repositories.user.UserAccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final UserAccount userAccount = userAccountRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No existe el usuario con el email: " + email));
        return new User(
            userAccount.getEmail(),
            userAccount.getPassword(),
            mapRoleToAuthority(userAccount.getRole())
        );
    }

    private Collection<GrantedAuthority> mapRoleToAuthority(Role role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role.getName()));
    }
    
}
