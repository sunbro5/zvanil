<template>
  <div class="registration">
    <h2>Registrace</h2>
    <form v-on:submit.prevent="submitRegistraion">
      <div>
        <input
          type="text"
          id="username"
          placeholder="Jméno žvanila"
          v-model="form.username"
        />
      </div>
      <div>
        <input
          type="text"
          id="password"
          placeholder="Heslo"
          v-model="form.password"
        />
      </div>
      <button>Potvrď žvanila</button>
    </form>
    <router-link :to="{ name: 'Main' }">Zpět na přihlášení.</router-link>
  </div>
</template>

<script>
import axios from "axios";
import URLS from "../constants/urls";

export default {
  name: "Registration",
  data() {
    return {
      form: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    submitRegistraion() {
      axios
        .post(URLS.API_REGISTRATION, this.form)
        .then((res) => {
          if (res.status === 200) {
            this.$router.push({ name: "Main" });
          }
        })
        .catch((error) => {
          if (error.response.status === 401) {
            this.$router.push({ name: "Main" });
          }
        });
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.registration {
  width: 50%;
  margin: auto;
  text-align: center;
}
.registration form {
  padding: 20px 20px;
}
.registration input {
  margin-bottom: 20px;
  width: 100%;
  max-width: 200px;
}
::-webkit-input-placeholder {
  text-align: center;
}
</style>
