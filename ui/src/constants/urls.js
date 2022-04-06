const PROD_URL = ""
const LOCAL_URL = "http://localhost:8081"
const URL = PROD_URL
const API_USER = URL + "/api/user/"
const API_CHAT = URL + "/api/chat"
const API_REGISTRATION = URL + "/api/registration"
const API_AUTH = URL + "/api/auth"
const API_USER_ALL = URL + "/api/user/all"
const API_WS = URL + "/ws"
const API_CHAT_KEY = URL + "/api/chat/key"

export default {
  URL : URL,
  API_USER : API_USER,
  API_CHAT : API_CHAT,
  API_CHAT_KEY : API_CHAT_KEY,
  API_REGISTRATION : API_REGISTRATION,
  API_AUTH : API_AUTH,
  API_USER_ALL : API_USER_ALL,
  API_WS : API_WS
}