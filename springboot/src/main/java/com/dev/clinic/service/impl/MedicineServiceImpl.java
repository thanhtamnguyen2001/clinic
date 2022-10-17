package com.dev.clinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.clinic.exception.NotFoundException;
import com.dev.clinic.model.Medicine;
import com.dev.clinic.repository.MedicineRepository;
import com.dev.clinic.service.MedicineService;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public Medicine getMedicineById(long medicineId) {
        Optional<Medicine> mOptional = this.medicineRepository.findById(medicineId);
        if (mOptional.isPresent()) {
            return mOptional.get();
        }

        throw new NotFoundException("Medicine does not exist!");
    }

    @Override
    public List<Medicine> getMedicines(String name) {
        List<Medicine> medicines = this.medicineRepository.findByNameContaining(name);
        if (medicines.isEmpty()) {
            throw new NotFoundException("Medicine does not exist!");
        }
        return medicines;
    }

}
