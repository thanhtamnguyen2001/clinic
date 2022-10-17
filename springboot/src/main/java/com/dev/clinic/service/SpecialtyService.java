package com.dev.clinic.service;

import java.util.List;

import com.dev.clinic.model.Specialty;

public interface SpecialtyService {
    List<Specialty> getSpecialties(String name);
}
