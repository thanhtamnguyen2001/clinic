import request from '~/utils/httpRequest';
import authHeader from './auth-header';

function getListRegisters(phone, name) {
    return request.get('registers', {
        params: {
            phone,
            name,
        },
    });
}

function getRegisterById(id) {
    return request.get(`registers/${id}`);
}

function createRegister(data) {
    return request.post('registers', data, { headers: authHeader() });
}

function verifiedRegister(id) {
    return request.post(`registers/${id}/verified`);
}

function deleteRegister(id) {
    return request.delete(`registers/${id}`).then((res) => res.data);
}

function updateRegister(id, name, phone, healthIssues, examinationTime) {
    return request.put(`registers/${id}`, { name, phone, healthIssues, examinationTime });
}

function getRegistersByCurrentUser() {
    return request.get('users/registers', { headers: authHeader() });
}

export default {
    getListRegisters,
    getRegisterById,
    createRegister,
    verifiedRegister,
    deleteRegister,
    updateRegister,
    getRegistersByCurrentUser,
};
