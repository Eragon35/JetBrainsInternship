package com.jetbrains.internship.entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "template")
public class Template {
    @Id
    private String id;

    private String message;
    private String recipients; // array

    public Template() {}

    public Template(String id, String message, String recipients) {
        this.id = id;
        this.message = message;
        this.recipients = recipients;
    }

    @Override
    public String toString() {
        return "template{id = " + id + ", message = " + message + ", recipients = " + recipients + "}";
    }
}
