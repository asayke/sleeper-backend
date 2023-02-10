package ru.sleeper.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sleeper.demo.model.ConfirmationToken;
import ru.sleeper.demo.repository.ConfirmationTokenRepository;

import java.sql.Date;
import java.time.Instant;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository tokenRepository;

    @Transactional
    public void saveConfirmationToken(ConfirmationToken token) {
        tokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Transactional
    public int setConfirmedAt(String token) {
        return tokenRepository.updateConfirmedAt(token, Date.from(Instant.now()));
    }
}