package com.jetbrains.internship.entities;

import java.util.Map;

public class Message {
    String message;
    public Message(Template template, Substitution substitution) throws IllegalArgumentException {
        if (!template.getTemplateId().equals(substitution.getTemplateId())) throw new IllegalArgumentException("Different ids");
        else {
            String temporaryMessage = template.getTemplate();
            for (Map.Entry<String, String> entry : substitution.getVariables().entrySet()) {
                if (temporaryMessage.contains("$" + entry.getKey() + "$")) {
                    temporaryMessage = temporaryMessage.replace("$" + entry.getKey() + "$", entry.getValue());
                } else throw new IllegalArgumentException("No such substitution in template");
            }
            message = temporaryMessage;
        }
    }

}
