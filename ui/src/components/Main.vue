<template>
  <div class="main">
    <h2>Prihlášení</h2>
    <form v-on:submit.prevent="login">
      <input
        type="text"
        id="username"
        placeholder="Jmeno"
        v-model="form.username"
      />
      <input
        type="text"
        id="password"
        placeholder="Heslo"
        v-model="form.password"
      />
      <button>Prihlaš se</button>
    </form>
    <router-link :to="{ name: 'Registration'}">Registruj se!</router-link>
  </div>
</template>

<script>
import axios from "axios";

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
    login() {
      axios
        .post("http://localhost:8080/login_process", this.form)
        .then((res) => {
          console.log("prihlasen");
          if (res.status == 200) {
          }
        });
    },
    redirectToRegistration() {

    }
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1,
h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
