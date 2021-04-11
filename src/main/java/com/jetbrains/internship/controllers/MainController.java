package com.jetbrains.internship.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jetbrains.internship.entities.Message;
import com.jetbrains.internship.entities.Substitution;
import com.jetbrains.internship.entities.Template;
import com.jetbrains.internship.repositories.SubstitutionRepository;
import com.jetbrains.internship.repositories.TemplateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
    public ResponseEntity<String> add(@RequestBody Template request) {
        try {
            templateRepository.save(request);
            log.info("Add new {}", request);
            return new ResponseEntity<>("Template was saved successfully!", HttpStatus.OK);
        } catch (Exception e) {
            log.error("Unexpected Error {}", e.getMessage());
            return new ResponseEntity<>("Unexpected Error: "+ e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/substitute")
    public ResponseEntity<String> change(@RequestBody String request) {
        JsonObject jsonObject = new JsonParser().parse(request).getAsJsonObject();
        String templateId = jsonObject.get("templateId").toString();
        String variablesString = jsonObject.get("variables")
                .toString()
                .replace("[","")
                .replace("]","");
        jsonObject = new JsonParser().parse(variablesString).getAsJsonObject();
        Map variables = new Gson().fromJson(jsonObject, Map.class);
        Substitution substitution = new Substitution(templateId, variables);
        if (jsonObject.has("schedule")){ // make schedule sending
            substitutionRepository.save(substitution);
            log.info("new {} arrived", substitution);
            return new ResponseEntity<>("Substitution was saved successfully and will be sending by schedule", HttpStatus.OK);
        }
        else {
            log.info("new {} arrived", substitution);
            Template template = templateRepository.findByTemplateId(substitution.getTemplateId());
            System.out.println(template);
            Message message = new Message(template, substitution);
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Gson gson = new Gson();
            for (String url : template.getRecipients()){
                HttpEntity<String> response = new HttpEntity<String>(gson.toJson(message), headers);
                restTemplate.postForEntity(url, message, Message.class);
            }
            return new ResponseEntity<>("Message was sent successfully", HttpStatus.OK);
        }
    }

}
