package ru.sleeper.demo.service;

import com.fasterxml.jackson.annotation.OptBoolean;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sleeper.demo.model.ConfirmationToken;
import ru.sleeper.demo.model.Sleep;
import ru.sleeper.demo.model.User;
import ru.sleeper.demo.repository.SleepRepository;
import ru.sleeper.demo.repository.UserRepository;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final SleepRepository sleepRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ConfirmationTokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User with email %s not found", email)));
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<Sleep> findAllByUser(User user) {
        return sleepRepository.findAllByUser(user);
    }

    public Sleep findLastByUser(User user) {
        Sleep sleep = sleepRepository.findTopByUserOrderByIdDesc(user).get();

        if(sleep == null)
            throw new IllegalStateException("No sleeps found");

        return sleep;
    }

    @Transactional
    public String singUpUser(User user) {

        if(userRepository.existsByEmail(user.getEmail()))
            throw new IllegalStateException("Email already taken");

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);
        userRepository.save(user);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confToken = new ConfirmationToken(token,
                Date.from(Instant.now().plusSeconds(900)), user);

        tokenService.saveConfirmationToken(confToken);

        return token;
    }

    @Transactional
    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }
}