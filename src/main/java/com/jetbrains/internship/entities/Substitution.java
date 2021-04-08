package com.jetbrains.internship.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "substitution")
public class Substitution {
    @Id
    private String id;

    private String variables; //List<String, String>
    private String substitution;

    public Substitution() { }

    public Substitution(String id, String variable, String substitution) {
        this.id = id;
        this.variables = variable;
        this.substitution = substitution;
    }

    @Override
    public String toString() {
        return "substitution {id = " + id + ", variable = " + variables + ", sustitution = " + substitution + "}";
    }
}
