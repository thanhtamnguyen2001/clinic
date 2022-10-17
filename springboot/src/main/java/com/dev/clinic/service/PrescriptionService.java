package com.dev.clinic.service;

import java.util.List;

import com.dev.clinic.model.Medicine;
import com.dev.clinic.model.Prescription;
import com.dev.clinic.model.PrescriptionMedicine;

public interface PrescriptionService {

    Prescription createPrescription(long certificateId, Prescription prescription);

    Prescription getPrescriptionById(long id);

    Boolean deletePrescription(long id);

    Prescription updatePrescription(long id, Prescription prescription);

    List<Prescription> getPrescriptionsByCertificateId(long certificateId);

    Medicine addMedicineToPresciption(long medicineId, long prescriptionId, Integer quantity);

    Boolean reoveMedicineFromPresciption(long medicineId, long prescriptionId);

    List<PrescriptionMedicine> getPrescriptionDetails(long prescriptionId);

}
