<template>
  <div class="registration">
    <h2>Nějaká registrace</h2>
    <form v-on:submit.prevent="submitRegistraion">
      <input
        type="text"
        id="username"
        placeholder="Jak na tebe hulákaj"
        v-model="form.username"
      />
      <input
        type="text"
        id="password"
        placeholder="Doplň Heslo nebo 123"
        v-model="form.password"
      />
      <button>Upiš se!</button>
    </form>
    <router-link :to="{ name: 'Main' }">Ale tady jsem už byl.</router-link>
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
        .post('/api/registration', this.form)
        .then((res) => {
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
  .registration{
    width: 50%;
    margin: auto;
    text-align: center;
  }
  .registration form{
    padding: 20px 20px;
  }
  .registration input{
    margin-bottom: 20px;
    width: 100%;
  }
  ::-webkit-input-placeholder {
   text-align: center;
}
</style>
