package com.jetbrains.internship.entities;


import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "template")
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String templateId;
    private String template;
    @ElementCollection
    private List<String> recipients;

    public Template() {}

    public Template(String templateId, String template, List<String> recipients) {
        this.templateId = templateId;
        this.template = template;
        this.recipients = recipients;
    }

    @Override
    public String toString() {
        return "template{id = " + templateId + ", template = " + template + ", recipients = " + recipients + "}";
    }
}
