const { default: request } = require('~/utils/httpRequest');

function getSpecialties() {
    return request.get('specialties');
}

export default { getSpecialties };
