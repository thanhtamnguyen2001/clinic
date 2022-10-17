package com.dev.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.clinic.model.Unit;

public interface UnitRepository extends JpaRepository<Unit, Integer> {
    
}
