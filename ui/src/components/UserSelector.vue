<template>
  <div class="userSelector">
    <div>
      <input
        v-model="toUserName"
        type="text"
        :placeholder="userSelectorText"
        @change="toUserNameChanged"
      />
    </div>
    <div class="userList">
      <h3>Seznam Žvanilů</h3>
      <a
        href=""
        v-for="user in userList"
        :key="user.username"
        v-on:click.prevent="toUserNamePicked(user.username)"
        >{{ user.username }}</a
      >
    </div>
  </div>
</template>

<script>
import axios from "axios";
import authHeader from "../service/DataService";
import URLS from "../constants/urls";
import AuthService from "../service/AuthService";

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
            if (res.status === 401) {
              this.$router.push({ name: "Main" });
            }
          })
          .catch((error) => {
            if (error.response.status === 404) {
              this.toUserName = "";
              this.userSelectorText = "Žvanil nenalezen.";
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
            this.userList = this.filterLoggedUsername(res.data);
          }
        });
    },
    toUserNamePicked(toUsername) {
      if (toUsername) {
        this.$emit("changed", toUsername);
      }
    },
    filterLoggedUsername(loadedUserList) {
      var userName = AuthService.userName();
      if (!userName) {
        return loadedUserList;
      }
      return loadedUserList.filter((user) => user.username !== userName);
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
  max-width: 200px;
}
.userList a {
  display: block;
}
::-webkit-input-placeholder {
  text-align: center;
}
</style>
