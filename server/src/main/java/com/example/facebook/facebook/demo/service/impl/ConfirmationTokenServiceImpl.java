package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.model.ConfirmationToken;
import com.example.facebook.facebook.demo.repository.ConfirmationTokenRepository;
import com.example.facebook.facebook.demo.service.ConfirmationTokenService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;
    Logger logger = LoggerFactory.getLogger(ConfirmationTokenServiceImpl.class);
    @Override
    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    @Override
    public Optional<ConfirmationToken> getToken(String token) {
        logger.info("Getting token for confirmation: {}", token);
        return confirmationTokenRepository.findByToken(token);

    }

    @Override
    public void setConfirmedAt(String token) {
        ConfirmationToken t = confirmationTokenRepository.findByToken(token).get();
        t.setConfirmedAt(LocalDateTime.now());
        confirmationTokenRepository.save(t);
    }
}
