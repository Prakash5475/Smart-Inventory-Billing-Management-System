const API_BASE = 'http://localhost:8080/api';
function getHeaders() {
    const token = localStorage.getItem('token');
    return { 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + token };
}
async function apiCall(endpoint, method = 'GET', body = null) {
    const options = { method: method, headers: getHeaders() };
    if (body) options.body = JSON.stringify(body);
    const response = await fetch(`${API_BASE}${endpoint}`, options);
    if (response.status === 401 || response.status === 403) { logout(); throw new Error('Unauthorized'); }
    if (!response.ok) throw new Error('Network error');
    return response.json();
}
async function login(username, password) {
    const response = await fetch(`${API_BASE}/auth/login`, {
        method: 'POST', headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
    });
    if (!response.ok) throw new Error('Login failed');
    return response.json();
}