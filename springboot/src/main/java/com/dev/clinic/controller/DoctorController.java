package com.dev.clinic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.clinic.model.Certificate;
import com.dev.clinic.model.Medicine;
import com.dev.clinic.model.Prescription;
import com.dev.clinic.model.PrescriptionMedicine;
import com.dev.clinic.service.CertificateService;
import com.dev.clinic.service.MedicineService;
import com.dev.clinic.service.PrescriptionService;

@CrossOrigin
@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private MedicineService medicineService;

    // #region certificate
    @GetMapping("/certificates/{id}")
    public ResponseEntity<Certificate> getCertificateById(@PathVariable long id) {
        Certificate certificate = this.certificateService.getCetificateById(id);
        return ResponseEntity.ok(certificate);
    }

    @GetMapping("/registers/{registerId}/certificates")
    public ResponseEntity<List<Certificate>> getCertificatesByRegisterId(@PathVariable long registerId) {
        List<Certificate> certificates = this.certificateService.getCertificatesByRegisterId(registerId);

        return ResponseEntity.ok(certificates);
    }

    @PostMapping("/registers/{registerId}/certificates")
    public ResponseEntity<Certificate> createCertificate(@PathVariable long registerId,
            @RequestBody Certificate certificate) {
        Certificate newCertificate = this.certificateService.createCertificate(registerId, certificate);

        return ResponseEntity.status(HttpStatus.CREATED).body(newCertificate);
    }

    @PutMapping("certificates/{certificateId}")
    public ResponseEntity<Certificate> updateCertificate(@PathVariable long certificateId,
            @RequestBody Certificate certificate) {
        Certificate updatedCertificate = this.certificateService.updateCertificate(certificateId, certificate);

        return ResponseEntity.ok(updatedCertificate);
    }

    @DeleteMapping("certificates/{certificateId}")
    public ResponseEntity<Boolean> deleteCertificate(@PathVariable long certificateId) {
        boolean result = this.certificateService.deleteCertifcate(certificateId);
        return ResponseEntity.ok(result);
    }
    // #endregion

    // #region precription
    @PostMapping("certificates/{certificateId}/prescriptions")
    public ResponseEntity<Prescription> createPrescription(@PathVariable long certificateId,
            Prescription prescription) {
        Prescription newPrescription = this.prescriptionService.createPrescription(certificateId, prescription);

        return ResponseEntity.status(HttpStatus.CREATED).body(newPrescription);
    }

    @GetMapping("prescriptions/{id}")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable long id) {
        Prescription prescription = this.prescriptionService.getPrescriptionById(id);
        return ResponseEntity.ok(prescription);
    }

    @GetMapping("certificates/{certificateId}/prescriptions")
    public ResponseEntity<List<Prescription>> getPrescriptionsByCertificateId(@PathVariable long certificateId) {
        List<Prescription> prescriptions = this.prescriptionService.getPrescriptionsByCertificateId(certificateId);

        return ResponseEntity.ok(prescriptions);
    }

    @PutMapping("prescriptions/{id}")
    public ResponseEntity<Prescription> updatePrescription(@PathVariable long id,
            @RequestBody Prescription prescription) {
        Prescription updaPrescription = this.prescriptionService.updatePrescription(id, prescription);

        return ResponseEntity.ok(updaPrescription);
    }

    @DeleteMapping("prescriptions/{id}")
    public ResponseEntity<Boolean> deletePrescription(@PathVariable long id) {
        boolean result = this.prescriptionService.deletePrescription(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping("prescriptions/{prescriptionId}/medicines/{medicineId}")
    public ResponseEntity<Medicine> addMedicineToPresciption(@PathVariable long prescriptionId,
            @PathVariable long medicineId, @RequestParam(required = false) Integer quantity) {

        Medicine medicine = this.prescriptionService.addMedicineToPresciption(medicineId, prescriptionId, quantity);
        return ResponseEntity.ok(medicine);

    }

    @DeleteMapping("prescriptions/{prescriptionId}/medicines/{medicineId}")
    public ResponseEntity<Boolean> removeMedicineFromPresciption(@PathVariable long prescriptionId,
            @PathVariable long medicineId) {
        boolean result = this.prescriptionService.reoveMedicineFromPresciption(medicineId, prescriptionId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("prescriptions/{prescriptionId}/details") 
    public ResponseEntity<?> getPrescriptionDetailsByPrescriptionId(@PathVariable long prescriptionId) {
        List<PrescriptionMedicine> pds = this.prescriptionService.getPrescriptionDetails(prescriptionId);

        return ResponseEntity.ok(pds);
    }

    @GetMapping("medicines") 
    public ResponseEntity<List<Medicine>> getMedicines(@RequestParam(required = false, defaultValue = "") String name) {
        List<Medicine> medicines = this.medicineService.getMedicines(name);
        return ResponseEntity.ok(medicines);
    }

    // #endregion
}
