<template>
  <div class="chat">
    <h2 v-if="toUserName != null">Žvanění s {{ toUserName }}</h2>
    <h2 v-else>Žvanění</h2>
    <UserSelector v-if="toUserName == null" @changed="toUserNameFilled" />
    <div class="chatlist" v-if="chatList && chatList.length">
      <div v-for="(message, index) in chatList" :key="`message-${index}`">
        <span v-if="message.userName == toUserName" class="right"
          >{{ message.userName }}: {{ message.message }}</span
        >
        <span v-else class="left"
          >{{ message.userName }} : {{ message.message }}</span
        >
      </div>
    </div>
    <div v-on:submit.prevent="sendMessage" v-if="toUserName != null">
      <form v-on:keyup.enter="sendMessage">
        <textarea v-model="newMessage" type="text" placeholder="" />
        <button>Odeslat žvanění</button>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import UserSelector from "./UserSelector";
import authHeader from "../service/DataService";
import URLS from "../constants/urls";

export default {
  name: "Chat",
  components: { UserSelector },
  data() {
    return {
      toUserName: null,
      chatList: [],
      newMessage: "",
    };
  },
  methods: {
    toUserNameFilled(value) {
      this.toUserName = value;
      console.log(value);
      this.loadChatList();
      this.setPeriodicLoad();
    },
    setPeriodicLoad() {
      this.interval = setInterval(() => this.loadChatList(), 1000);
    },
    loadChatList() {
      axios
        .get(URLS.API_CHAT + "?withUsername=" + this.toUserName, {
          headers: authHeader(),
        })
        .then((res) => {
          if (res.status === 200) {
            this.chatList = res.data;
          }
        })
        .catch((error) => {
          if (error.response.status === 401) {
            this.$router.push({ name: "Main" });
          }
        });
    },
    sendMessage() {
      if (this.toUserName && this.newMessage) {
        const postData = {
          toUserName: this.toUserName,
          message: this.newMessage,
        };
        axios
          .post(URLS.API_CHAT, postData, {
            headers: authHeader(),
          })
          .then((res) => {
            if (res.status === 200) {
              console.log("sended");
              this.newMessage = "";
              loadChatList();
            }
          })
          .catch((error) => {
            if (error.response.status === 401) {
              this.$router.push({ name: "Main" });
            }
          });
      }
    },
  },
};
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.chat {
  width: 50%;
  margin: auto;
  text-align: center;
}
.chat form {
  padding: 20px 20px;
}
.chat textarea {
  margin-bottom: 20px;
  width: 100%;
}
::-webkit-input-placeholder {
  text-align: center;
}
.chatlist {
  margin: 20px;
}
.chatlist span {
  display: block;
  height: 30px;
  width: 80%;
  vertical-align: middle;
  line-height: 30px;
}
.chatlist .left {
  float: left;
  margin: 2px 10px;
  background-color: lightsteelblue;
}
.chatlist .right {
  float: right;
  margin: 2px 10px;
  background-color: whitesmoke;
}
</style>
