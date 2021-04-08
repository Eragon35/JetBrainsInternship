package com.jetbrains.internship.repositories;

import com.jetbrains.internship.entities.Template;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateRepository extends JpaRepository<Template, Long> {
}
