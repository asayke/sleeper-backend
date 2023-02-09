package ru.sleeper.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sleeper.demo.model.Sleep;
import ru.sleeper.demo.repository.SleepRepository;

@Service
@Transactional(readOnly = true)
@Slf4j
public class SleepService {
    private final SleepRepository sleepRepository;

    @Autowired
    public SleepService(SleepRepository sleepRepository) {
        this.sleepRepository = sleepRepository;
    }

    public Sleep findById(Long id) {
        Sleep sleep = sleepRepository.findById(id).orElse(null);

        if(sleep == null)
            log.warn("IN findById - no user found by id: {}", id);

        log.info("IN findById - user: {} found by id: {}", sleep, id);
        return sleep;
    }
}