package ru.sstu.sharing.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sstu.sharing.dao.UserRepository;
import ru.sstu.sharing.domain.UserDetailsImpl;
import ru.sstu.sharing.domain.entities.User;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
       Optional<User> optionalUser = this.userRepository.findByLogin(s);
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Login: " + s + " not found"));
        return optionalUser.map(UserDetailsImpl::new).get();
    }
}
