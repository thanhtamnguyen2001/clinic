package com.dev.clinic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.clinic.model.Specialty;
import com.dev.clinic.service.SpecialtyService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CommonController {

    @Autowired
    private SpecialtyService specialtyService;
    
    @GetMapping("/specialties")
    public ResponseEntity<List<Specialty>> getSpecialties(@RequestParam(required = false, defaultValue = "") String name) {
        List<Specialty> specialties = this.specialtyService.getSpecialties(name);

        return ResponseEntity.ok(specialties);
    }
}
