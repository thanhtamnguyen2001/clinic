import request from '~/utils/httpRequest';
import authHeader from './auth-header';

function getPrescriptionsByCertificateId(cerId) {
    return request.get(`doctors/certificates/${cerId}/prescriptions`, { headers: authHeader() });
}

function getPrescriptionDetailsByPrescriptionId(preId) {
    return request.get(`doctors/prescriptions/${preId}/details`);
}

function getCertificatesByRegisterId(registerId) {
    return request.get(`doctors/registers/${registerId}/certificates`);
}

export default {
    getPrescriptionsByCertificateId,
    getPrescriptionDetailsByPrescriptionId,
    getCertificatesByRegisterId,
};
