package com.dev.clinic.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.dev.clinic.exception.InternalException;
import com.dev.clinic.exception.NotFoundException;
import com.dev.clinic.model.Certificate;
import com.dev.clinic.model.Medicine;
import com.dev.clinic.model.Prescription;
import com.dev.clinic.model.PrescriptionMedicine;
import com.dev.clinic.repository.CertificateRepository;
import com.dev.clinic.repository.PrescriptionMedicineRepository;
import com.dev.clinic.repository.PrescriptionRepository;
import com.dev.clinic.service.CertificateService;
import com.dev.clinic.service.MedicineService;
import com.dev.clinic.service.PrescriptionService;

@Service
public class PresciptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private PrescriptionMedicineRepository prescriptionMedicineRepository;

    @Override
    public Prescription createPrescription(long certificateId, Prescription prescription) {
        Certificate certificate = this.certificateService.getCetificateById(certificateId);
        prescription.setCertificate(certificate);
        prescription.setCreatedDate(new Date());

        Prescription newPrescription = this.prescriptionRepository.save(prescription);

        return newPrescription;
    }

    @Override
    public Prescription getPrescriptionById(long id) {
        Optional<Prescription> pOptional = this.prescriptionRepository.findById(id);
        if (pOptional.isPresent()) {
            Prescription prescription = pOptional.get();
            return prescription;
        }
        throw new NotFoundException("Prescription does not exist");
    }

    @Override
    public Boolean deletePrescription(long id) {
        boolean isExistPre = this.prescriptionRepository.existsById(id);
        if (isExistPre) {
            this.prescriptionRepository.deleteById(id);
            return true;
        }
        throw new NotFoundException("Prescription does not exist!");
    }

    @Override
    public Prescription updatePrescription(long id, Prescription prescription) {
        Optional<Prescription> pOptional = this.prescriptionRepository.findById(id);
        if (pOptional.isPresent()) {
            Prescription existedPre = pOptional.get();
            prescription.setId(existedPre.getId());
            prescription.setCertificate(existedPre.getCertificate());
            // prescription.setMedicines(existedPre.getMedicines());

            Prescription updatPrescription = this.prescriptionRepository.save(prescription);
            return updatPrescription;
        }

        throw new NotFoundException("Prescription does not exist!");
    }

    @Override
    public List<Prescription> getPrescriptionsByCertificateId(long certificateId) {
        boolean isExistCer = this.certificateRepository.existsById(certificateId);
        if (isExistCer) {
            List<Prescription> prescriptions = this.prescriptionRepository.findByCertificateId(certificateId);

            if (!prescriptions.isEmpty()) {
                return prescriptions;
            }
            throw new NotFoundException("Certificate does not have any prescriptions!");
        }
        throw new NotFoundException("Certificate does not exist!");
    }

    @Override
    public Medicine addMedicineToPresciption(long medicineId, long prescriptionId, Integer quantity) {
        if (quantity == null) {
            quantity = 1;
        }

        Prescription prescription = this.getPrescriptionById(prescriptionId);
        Medicine medicine = this.medicineService.getMedicineById(medicineId);
        prescription.addMedicine(medicine, quantity);

        this.prescriptionRepository.save(prescription);

        return medicine;
    }

    @Override
    public Boolean reoveMedicineFromPresciption(long medicineId, long prescriptionId) {
        Prescription prescription = this.getPrescriptionById(prescriptionId);
        PrescriptionMedicine prescriptionMedicine = prescription.getPrescriptionMedicine(medicineId);

        if (prescriptionMedicine != null) {
            try {
                this.prescriptionMedicineRepository.deleteByPMId(prescriptionMedicine.getId());
            } catch (DataAccessException ex) {
                throw new InternalException(ex.getMessage());
            }
            return true;
        }

        throw new NotFoundException("Medicine does not exist in Prescription!");
    }

    @Override
    public List<PrescriptionMedicine> getPrescriptionDetails(long prescriptionId) {
        Prescription prescription = this.getPrescriptionById(prescriptionId);
        List<PrescriptionMedicine> pds = this.prescriptionMedicineRepository.findByPrescription(prescription);

        if (pds.isEmpty()) {
            throw new NotFoundException("Prescription does not any medicin!");
        }

        return pds;
    }

}
