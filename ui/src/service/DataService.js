export default function authHeader() {
    let user = JSON.parse(localStorage.getItem('userAuth'));
  
    if (user && user.token) {
      return { Authorization: 'Bearer ' + user.token };
    } else {
      return {};
    }
  }