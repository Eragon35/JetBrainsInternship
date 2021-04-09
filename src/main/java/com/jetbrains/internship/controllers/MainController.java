package com.jetbrains.internship.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jetbrains.internship.entities.Substitution;
import com.jetbrains.internship.entities.Template;
import com.jetbrains.internship.repositories.SubstitutionRepository;
import com.jetbrains.internship.repositories.TemplateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@Slf4j
public class MainController {
    private final SubstitutionRepository substitutionRepository;
    private final TemplateRepository templateRepository;

    public MainController(SubstitutionRepository substitutionRepository, TemplateRepository templateRepository) {
        this.substitutionRepository = substitutionRepository;
        this.templateRepository = templateRepository;
    }

    @PostMapping("/addTemplate")
    public void add(@RequestBody Template request) {
        templateRepository.save(request);
        log.info("Add new {}", request);
    }


    @PostMapping("/substitute")
    public void change(@RequestBody String request) {
        JsonObject jsonObject = new JsonParser().parse(request).getAsJsonObject();
        String templateId = jsonObject.get("templateId").toString();
        String variablesString = jsonObject.get("variables")
                .toString()
                .replace("[","")
                .replace("]","");
        jsonObject = new JsonParser().parse(variablesString).getAsJsonObject();
        Map variables = new Gson().fromJson(jsonObject, Map.class);
        Substitution substitution = new Substitution(templateId, variables);
        log.info("new {} arrived", substitution);
        substitutionRepository.save(substitution);
    }

}
