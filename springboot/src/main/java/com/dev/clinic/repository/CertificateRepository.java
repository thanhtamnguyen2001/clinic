package com.dev.clinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.clinic.model.Certificate;
import com.dev.clinic.model.Register;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    
    List<Certificate> findByRegisterId(long registerId);

    List<Certificate> findByRegister(Register register);
}
