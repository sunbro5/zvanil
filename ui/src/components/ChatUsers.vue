<template>
  <div id="plist" class="people-list">
    <!--<div class="input-group">
      <div class="input-group-prepend">
        <span class="input-group-text"><i class="fa fa-search"></i></span>
      </div>
      <input
        v-model="toUserName"
        type="text"
        :placeholder="userSelectorText"
        @change="toUserNameChanged"
        class="form-control"
      />
    </div>-->
    <ul class="list-unstyled chat-list mt-2 mb-0">
      <li
        href=""
        v-for="user in userList"
        :key="user.username"
        v-on:click.prevent="toUserNamePicked(user.username)"
        class="clearfix"
      >
        <img src="@/assets/avatar.png" alt="avatar" />
        <div class="about">
          <div class="name">{{ user.username }}</div>
          <div class="status"><i class="fa fa-circle online"></i> - chat</div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from "axios";
import authHeader from "../service/DataService";
import URLS from "../constants/urls";
import AuthService from "../service/AuthService";

export default {
  name: "ChatUsers",
  data() {
    return {
      toUserName: "",
      userSelectorText: "Jméno žvanila",
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
              this.userSelectorText = "Žvanil nenalezen.";
            }
            if (res.status === 401) {
              this.$router.push({ name: "Main" });
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
        })
        .catch((error) => {
          if (error.response.status === 401) {
            this.$router.push({ name: "Main" });
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
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
