package com.dev.clinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.clinic.model.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    
    List<Prescription> findByCertificateId(long certificateId);
} 
