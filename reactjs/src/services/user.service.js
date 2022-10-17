import request from '~/utils/httpRequest';
import authHeader from './auth-header';

function getPublicContent() {
    return request.get('test/all');
}

function getPatientBoard() {
    return request.get('test/patient', { headers: authHeader() });
}

function getDoctorBoard() {
    return request.get('test/doctor', { headers: authHeader() });
}

function getNurseBoard() {
    return request.get('test/nurse', { headers: authHeader() });
}

function getAdminBoard() {
    return request.get('test/admin', { headers: authHeader() });
}

export default {
    getPublicContent,
    getPatientBoard,
    getDoctorBoard,
    getNurseBoard,
    getAdminBoard,
};
