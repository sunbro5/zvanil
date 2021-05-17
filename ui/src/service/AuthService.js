import axios from 'axios';
import URLS from '../constants/urls'

class AuthService {
  login(user) {
    return axios
      .post(URLS.API_AUTH, {
        username: user.username,
        password: user.password
      })
      .then(response => {
        if (response.data.token) {
          localStorage.setItem('user', JSON.stringify(response.data));
        }
        return response.data;
      });
  }

  logout() {
    localStorage.removeItem('user');
  }
}

export default new AuthService();