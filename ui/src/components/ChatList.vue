<template>
  <div class="chat-history">
    <ul class="m-b-0">
      <li
        v-for="(message, index) in chatList"
        :key="`message-${index}`"
        class="clearfix"
      >
        <div v-if="message.author">
          <div class="message-data text-right">
            <!--<span class="message-data-time">10:10 AM, Today</span>-->
            <img src="@/assets/avatar.png" alt="avatar" />
          </div>
          <div class="message my-message float-right">
            {{ message.message }}
          </div>
        </div>
        <div v-else>
          <div class="message-data text-left">
            <!--<span class="message-data-time">10:10 AM, Today</span>-->
            <img src="@/assets/avatar.png" alt="avatar" />
          </div>
          <div class="message other-message float-left">
            {{ message.message }}
          </div>
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
    };
  },
  props: {
    chatList: Array,
    toUserName: String,
  },
  methods: {},
};
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
