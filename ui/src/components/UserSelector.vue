<template>
  <div class="userSelector">
    <input v-model="toUserName" type="text" :placeholder="userSelectorText" @change="toUserNameChanged" />
  </div>
</template>

<script>
import axios from "axios";
import authHeader from "../service/DataService";

export default {
  name: "UserSelector",
  data() {
    return {
      toUserName: "",
      userSelectorText: "S kým budeme žvanit"
    };
  },
  methods: {
    toUserNameChanged(value) {
      if (value) {
        axios
          .get("/api/user/" + value.target.value, {
            headers: authHeader(),
          })
          .then((res) => {
            if (res.status === 200) {
              this.$emit("changed", res.data.username);
            }
          }).catch((error) => {
            if(error.response.status === 404) {
              this.toUserName = "";
              this.userSelectorText = "Nikdo takový neexistuje ! Zkus znovu."
            }
          });
      }
    },
  },
};
</script>

<style scoped>
.userSelector input{
    margin-bottom: 20px;
    width: 100%;
  }
  ::-webkit-input-placeholder {
   text-align: center;
}
</style>
