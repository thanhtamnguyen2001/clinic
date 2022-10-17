import request from '~/utils/httpRequest';

function register(username, email, password, phone) {
    return request.post('auth/signin', { username, email, password, phone });
}

function login(username, password) {
    return request.post('auth/signin', { username, password }).then((res) => {
        if (res.data.accessToken) {
            localStorage.setItem('user', JSON.stringify(res.data));
        }

        return res.data;
    });
}

function logout() {
    localStorage.removeItem('user');
}

export default { register, login, logout };
