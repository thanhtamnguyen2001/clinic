import authHeader from './auth-header';

const { default: request } = require('~/utils/httpRequest');

function getCertificatesByRegisterId(registerId) {
    return request.get(`doctors/registers/${registerId}/certificates`);
}

function deleteCertificate(certificateId) {
    return request.delete(`doctors/certificates/${certificateId}`);
}

function updateCertificate(cerId, cerData) {
    return request.put(`doctors/certificates/${cerId}`, cerData, { headers: authHeader() });
}

function createCertificate(registerId, cerData) {
    return request.post(`doctors/registers/${registerId}/certificates`, cerData, { headers: authHeader() });
}

function getCertificateById(cerId) {
    return request.get(`doctors/certificates/${cerId}`, { headers: authHeader() });
}

function createPrescription(cerId) {
    return request.post(`doctors/certificates/${cerId}/prescriptions`, { headers: authHeader() });
}

function updatePrescription(preId) {
    return request.put(`doctors/prescriptions/${preId}`);
}

function getPrescriptionsByCertificateId(cerId) {
    return request.get(`doctors/certificates/${cerId}/prescriptions`, { headers: authHeader() });
}

function deletePrescription(preId) {
    return request.delete(`doctors/prescriptions/${preId}`);
}

function addMedicineToPresciption(preId, medicinId) {
    return request.post(`doctors/prescriptions/${preId}/medicines/${medicinId}`);
}

function getMedicines() {
    return request.get('doctors/medicines', { headers: authHeader() });
}

function getPrescriptionDetailsByPrescriptionId(preId) {
    return request.get(`doctors/prescriptions/${preId}/details`);
}

function removeMedicineFromPresciption(preId, medicineId) {
    return request.delete(`doctors/prescriptions/${preId}/medicines/${medicineId}`);
}

export default {
    getCertificatesByRegisterId,
    deleteCertificate,
    updateCertificate,
    createCertificate,
    getCertificateById,
    createPrescription,
    getPrescriptionsByCertificateId,
    deletePrescription,
    updatePrescription,
    addMedicineToPresciption,
    getMedicines,
    getPrescriptionDetailsByPrescriptionId,
    removeMedicineFromPresciption,
};
