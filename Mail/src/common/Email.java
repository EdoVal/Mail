/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Email implements Serializable {

    private int id;
    private String sender;
    private String receiver;
    private String subject;
    private String body;
    LocalDateTime date;

    public Email(int id, String sender, String receiver, String subject, String body) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.body = body;
        this.date = LocalDateTime.now();
    }

    /*costruisce Email da metodo toString*/
    public Email(String s) {
        String[] split = s.split("#");
        this.id = Integer.parseInt(split[0]);
        this.sender = split[1];
        this.receiver = split[2];
        this.subject = split[3];
        this.body = split[4];
        this.date = LocalDateTime.parse(split[5]);
    }

    public int getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return Integer.toString(id) + "#"
                + sender + "#"
                + receiver + "#"
                + subject + "#"
                + body + "#"
                + date.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Email))
            return false;
        Email tmp = (Email) obj;
        return this.getBody().equals(tmp.getBody());
    }

}
