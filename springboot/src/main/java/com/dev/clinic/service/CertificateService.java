package com.dev.clinic.service;

import java.util.List;
import com.dev.clinic.model.Certificate;

public interface CertificateService {

    Certificate getCetificateById(long certificateId);

    List<Certificate> getCertificatesByRegisterId(long registerId);

    Certificate createCertificate(long registerId, Certificate certificate);

    Certificate updateCertificate(long certificateId, Certificate certificate);

    Boolean deleteCertifcate(long certificateId);

}
