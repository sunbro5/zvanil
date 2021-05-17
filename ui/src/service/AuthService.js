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
          localStorage.setItem('userAuth', JSON.stringify(response.data));
          localStorage.setItem('username', response.data.username);
        }
        return response.data;
      });
  }

  logout() {
    localStorage.removeItem('userAuth');
    localStorage.removeItem('username');
  }

  userName(){
    return localStorage.getItem('username');
  }
}

export default new AuthService();