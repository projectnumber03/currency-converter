package ru.shilov.cc.currencyconverter.service;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.shilov.cc.currencyconverter.entity.User;
import ru.shilov.cc.currencyconverter.repo.UserRepo;

import java.util.List;

@Service
@AllArgsConstructor
public final class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    public void save(final User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    @Bean
    public void initAdmin(){
        if(findAll().size() == 0){
            User user = new User();
            user.setActive(true);
            user.setLogin("admin");
            user.setPassword("123");
            save(user);
        }
    }

    public List<User> findAll(){
        return userRepo.findAll();
    }

    public User findUserByLogin(final String login) {
        return userRepo.findUserByLogin(login);
    }

    @Override
    public UserDetails loadUserByUsername(final String s) throws UsernameNotFoundException {
        return findUserByLogin(s);
    }

}
