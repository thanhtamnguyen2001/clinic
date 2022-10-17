package com.dev.clinic.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/patient")
    @PreAuthorize("hasRole('ROLE_PATIENT') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE') or hasRole('ROLE_ADMIN')")
    public String patientAccess() {
        return "Patient Content.";
    }

    @GetMapping("/doctor")
    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    public String doctorAccess() {
        return "Doctor Board.";
    }

    @GetMapping("/nurse")
    @PreAuthorize("hasRole('ROLE_NURSE')")
    public String nurseAccess() {
        return "Nurse Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}