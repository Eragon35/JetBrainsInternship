package com.jetbrains.internship.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.Map;

@Entity
@Data
@Table(name = "substitution")
public class Substitution {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String templateId;
    @ElementCollection
    private Map<String, String> variables;

    public Substitution() { }

    public Substitution(String templateId, Map<String, String> variables) {
        this.templateId = templateId;
        this.variables = variables;
    }

    @Override
    public String toString() {
        return "substitution {id = " + templateId + ", variables = " + variables + "}";
    }
}
