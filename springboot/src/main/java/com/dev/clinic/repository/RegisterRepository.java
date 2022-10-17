package com.dev.clinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.clinic.model.Register;

public interface RegisterRepository extends JpaRepository<Register, Long> {

    List<Register> findByNameOrPhoneContaining(String name, String phone);
}
