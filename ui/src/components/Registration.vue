/* eslint-disable */
<template>
  <div class="registration">
    <h2>Registrace</h2>
    <form v-on:submit.prevent="submitRegistraion">
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
      <button>Registruj</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Registration",
  data() {
    return {
      form: {
        username: '',
        password: ''
      }
    }
  },
  methods: {
    submitRegistraion() {
      axios
        .post('http://localhost:8080/api/registration', this.form)
        .then((res) => {
          console.log('registered')
          if (res.status === 200) {
            this.$router.push({ name: 'Main' })
          }
        })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
