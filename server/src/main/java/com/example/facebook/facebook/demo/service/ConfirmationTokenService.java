package com.example.facebook.facebook.demo.service;

import com.example.facebook.facebook.demo.model.ConfirmationToken;

import java.util.Optional;

public interface ConfirmationTokenService {

    public void saveConfirmationToken(ConfirmationToken token);
    public Optional<ConfirmationToken> getToken(String token);
    public void setConfirmedAt(String token);

}
