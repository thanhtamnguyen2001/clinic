package com.dev.clinic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.clinic.exception.NotFoundException;
import com.dev.clinic.model.Specialty;
import com.dev.clinic.repository.SpecialtyRepository;
import com.dev.clinic.service.SpecialtyService;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Override
    public List<Specialty> getSpecialties(String name) {
        List<Specialty> specialties = this.specialtyRepository.findByNameContaining(name);
        if (specialties.isEmpty()) {
            throw new NotFoundException("Does not any specialty!");
        }
        return specialties;
    }
    
}
