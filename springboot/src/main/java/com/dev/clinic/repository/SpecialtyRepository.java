package com.dev.clinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.clinic.model.Specialty;

public interface SpecialtyRepository extends JpaRepository<Specialty, Integer> {
    List<Specialty> findByNameContaining(String name);
}
