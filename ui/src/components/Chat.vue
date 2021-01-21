/* eslint-disable */
<template>
  <div class="registration">
    <h2>Kecálek</h2>
    <UserSelector v-if="toUserName == null" @changed="toUserNameFilled"/>
  </div>
</template>

<script>
import axios from "axios";
import UserSelector from "./UserSelector";

export default {
  name: "Chat",
  components: {UserSelector},
  data() {
    return {
      toUserName: null
    };
  },
  methods: {
    submitRegistraion() {
      axios
        .post("http://localhost:8080/api/registration", this.form)
        .then((res) => {
          console.log("registered");
          if (res.status == 200) {
            this.$router.push({ name: 'Main' })
          }
        });
    },
    toUserNameFilled(value){
      this.toUserName = value;
      console.log(value);
    }
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
