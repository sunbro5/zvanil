<template>
  <div class="main">
    <h2>Přihlášení do žvanila.</h2>
    <form v-on:submit.prevent="login">
      <div>
        <input
          type="text"
          id="username"
          placeholder="Jméno"
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
      <button>Tak hurá do kecání</button>
    </form>
    <router-link :to="{ name: 'Registration' }"
      >Když nevíš klikni sem</router-link
    >
  </div>
</template>

<script>
import axios from "axios";
import AuthService from "../service/AuthService";

export default {
  name: "Main",
  data() {
    return {
      clientLogged: false,
      form: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    isUserLogged() {
      const userName = AuthService.userName();
      if (userName) {
        this.clientLogged = true;
        return;
      }
      //this.$router.push({ name: "Chat" });
    },
    login(event) {
      AuthService.login(this.form).then((data) => {
        if (data.username) {
          this.$router.push({ name: "Chat" });
        }
      });
    },
  },
  created() {
    this.isUserLogged();
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.main {
  width: 50%;
  margin: auto;
  text-align: center;
}
.main form {
  padding: 20px 20px;
}
.main input {
  margin-bottom: 20px;
  width: 100%;
  max-width: 200px;
}
::-webkit-input-placeholder {
  text-align: center;
}
</style>
