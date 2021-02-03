<template>
  <div class="userSelector">
    <input
      v-model="toUserName"
      type="text"
      :placeholder="userSelectorText"
      @change="toUserNameChanged"
    />
    <div class="userList">
      <h3>Seznam Žvanilů</h3>
      <a href="" v-for="user in userList" :key="user.username" v-on:click.prevent="toUserNamePicked(user.username)" >{{ user.username }}</a>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import authHeader from "../service/DataService";
import URLS from "../constants/urls";

export default {
  name: "UserSelector",
  data() {
    return {
      toUserName: "",
      userSelectorText: "S kým budeme žvanit",
      userList: [],
    };
  },
  methods: {
    toUserNameChanged(value) {
      if (value) {
        axios
          .get(URLS.API_USER + "/" + value.target.value, {
            headers: authHeader(),
          })
          .then((res) => {
            if (res.status === 200) {
              this.$emit("changed", res.data.username);
            }
          })
          .catch((error) => {
            if (error.response.status === 404) {
              this.toUserName = "";
              this.userSelectorText = "Nikdo takový neexistuje ! Zkus znovu.";
            }
          });
      }
    },
    loadUserList() {
      axios
        .get(URLS.API_USER_ALL, {
          headers: authHeader(),
        })
        .then((res) => {
          if (res.status === 200) {
            this.userList = res.data;
          }
        });
    },
    toUserNamePicked(toUsername) {
      if (toUsername) {
        this.$emit("changed", toUsername);
      }
    },
  },
  mounted() {
    this.loadUserList();
  },
};
</script>

<style scoped>
.userSelector input {
  margin-bottom: 20px;
  width: 100%;
}
.userList a {
  display: block;
}
::-webkit-input-placeholder {
  text-align: center;
}
</style>
