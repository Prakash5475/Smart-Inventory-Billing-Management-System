package com.inventory.service;
import com.inventory.model.User; import com.inventory.repository.UserRepository; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.security.core.authority.SimpleGrantedAuthority; import org.springframework.security.core.userdetails.*; import org.springframework.stereotype.*;
import java.util.Collections;
@Service public class UserService implements UserDetailsService {
    @Autowired private UserRepository repo;
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = repo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(u.getRole().getName())));
    }
}