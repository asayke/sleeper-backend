package ru.sleeper.demo.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sleeper.demo.model.Sleep;
import ru.sleeper.demo.model.User;
import ru.sleeper.demo.repository.SleepRepository;
import ru.sleeper.demo.repository.UserRepository;

@Service
@Transactional(readOnly = true)
@Slf4j
@AllArgsConstructor
public class SleepService {
    private final SleepRepository sleepRepository;
    private final UserRepository userRepository;

    @Transactional
    public String addSleep(Sleep sleep, String email) {
        User user = userRepository.findByEmail(email).get();

        sleep.setUser(user);

        sleepRepository.save(sleep);

        return "done";
    }

    public Sleep findById(Long id) {
        Sleep sleep = sleepRepository.findById(id).orElse(null);

        if(sleep == null)
            log.warn("IN findById - no sleep found by id: {}", id);

        log.info("IN findById - sleep: {} found by id: {}", sleep, id);
        return sleep;
    }
}