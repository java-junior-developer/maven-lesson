package ru.itmo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Сообщения для обмена информацией между клиентом и сервером.
 *
 * @author JJD
 * @version 1.0
 *
 * @see java.io.Serializable
 * */
public class Message implements Serializable {
    /**
     * Текст передаваемого сообщения.
     *
     * @see String
     * */
    private final String text;
    /**
     * Дата и время отправки сообщения.
     * Значение необходимо устанавливать непосредственно перед отправкой.
     *
     * @see LocalDateTime
     * @see #setSent(LocalDateTime)
     * */
    private LocalDateTime sent;

    public Message(String text) {
        this.text = text;
    }

    public void setSent(LocalDateTime sent) {
        this.sent = sent;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getSent() {
        return sent;
    }
}