package org.huhu.spring.security.demo.service;

import lombok.RequiredArgsConstructor;
import org.huhu.spring.security.demo.entity.spring.CustomizedUserDetails;
import org.huhu.spring.security.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomizedJpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public CustomizedUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                             .map(CustomizedUserDetails::new)
                             .orElseThrow(() -> new UsernameNotFoundException("problem during authentication"));
    }

}
