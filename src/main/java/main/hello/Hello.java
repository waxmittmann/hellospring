package main.hello;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hello_messages")
public class Hello {
    @Id
    @GeneratedValue
    @Column(name = "id")
    long id;

    @Column(name = "message")
    String message;

    @Column(name = "sender")
    String sender;

    public Hello() {
    }

    public Hello(String msg) {
        this.message = msg;
    }

    public Hello(String msg, String sender) {
        this(msg);
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
