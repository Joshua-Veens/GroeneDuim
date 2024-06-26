export default class LoginService {
    isLoggedIn() {
        return window.localStorage.getItem('token') !== null;
    }

    async login(user, password) {
        const response = await fetch('/api/authentication/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username: user, password: password })
        });

        if (response.ok) {
            const data = await response.json();
            window.localStorage.setItem('token', data.JWT);
            return Promise.resolve();
        } else {
            const errorData = await response.json();
            return Promise.reject(errorData.error || 'Login failed');
        }
    }

    async getUser() {
        const token = window.localStorage.getItem('token');
        if (!token) return Promise.resolve(null);

        const response = await fetch('/api/authentication/validate', {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });

        if (response.ok) {
            return response.json();
        } else {
            this.logout();
            return Promise.resolve(null);
        }
    }

    logout() {
        window.localStorage.removeItem('token');
        return Promise.resolve();
    }
}