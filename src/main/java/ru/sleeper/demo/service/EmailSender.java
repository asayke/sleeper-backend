package ru.sleeper.demo.service;

public interface EmailSender {
    void send(String to, String email);
}