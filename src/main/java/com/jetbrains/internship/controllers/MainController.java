package com.jetbrains.internship.controllers;

import com.jetbrains.internship.entities.Substitution;
import com.jetbrains.internship.entities.Template;
import com.jetbrains.internship.repositories.SubstitutionRepository;
import com.jetbrains.internship.repositories.TemplateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


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
    public void change(@RequestBody Substitution request) {
        substitutionRepository.save(request);
        log.info("new {} arrived", request);
    }

}
