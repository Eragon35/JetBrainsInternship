package com.jetbrains.internship.repositories;

import com.jetbrains.internship.entities.Substitution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubstitutionRepository extends JpaRepository<Substitution, Long> {
}
