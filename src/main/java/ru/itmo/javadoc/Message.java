package ru.itmo.javadoc;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Сообщения для обмена информацией между клиентом и сервером.
 *
 * @author JJD
 * @version 1.0
 *
 * @see java.io.Serializable
 * @see String
 * @see LocalDateTime
 * @see IllegalArgumentException
 * */
public class Message implements Serializable {

    /**
     * Текст передаваемого сообщения.
     * */
    private final String text;

    /**
     * Дата и время отправки сообщения.
     * Значение необходимо устанавливать непосредственно перед отправкой.
     *
     * @see #setSent(LocalDateTime)
     * */
    private LocalDateTime sent;

    /**
     * Инициализация экземпляра Message с заданным текстом.
     *
     * @param text неизменяемый текст сообщения, не менее одного символа.
     * */
    public Message(String text) {
        if (text == null || text.strip().length() < 1)
            throw new IllegalArgumentException("text должен состоять хотя бы из одного символа");
        this.text = text;
    }

    /**
     * Устанавливает дату и время отправки сообщения.
     * Может быть вызван перед сериализацией сообщения.
     *
     * @param sent дата и время, не null
     * */
    public void setSent(LocalDateTime sent) {
        Objects.requireNonNull(sent);
        this.sent = sent;
    }

    /**
     * Может быть использован после получения сообщения.
     *
     * @return текст сообщения.
     * */
    public String getText() {
        return text;
    }


    /**
     * Может быть использован после получения сообщения.
     *
     * @return время отправки сообщения.
     * */
    public LocalDateTime getSent() {
        return sent;
    }
}