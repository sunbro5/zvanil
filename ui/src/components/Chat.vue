<template>
  <div class="container">
    <div class="row clearfix">
      <div class="col-lg-12">
        <div class="card chat-app">
          <ChatUsers @changed="toUserNameFilled" />
          <div class="chat">
            <div class="chat-header clearfix">
              <div class="row">
                <div class="col-lg-6">
                  <a
                    href="javascript:void(0);"
                    data-toggle="modal"
                    data-target="#view_info"
                  >
                    <img src="@/assets/avatar.png" alt="avatar" />
                  </a>
                  <div v-if="toUserName != null" class="chat-about">
                    <h6 class="m-b-0">{{ toUserName }}</h6>
                    <small style="display: none">Last seen: 2 hours ago</small>
                  </div>
                </div>
              </div>
            </div>
            <ChatList :chatList="chatList" :toUsername="toUsername" />
            <div class="chat-message clearfix">
              <div class="input-group mb-0">
                <div class="input-group-prepend">
                  <span v-on:click="sendMessage" class="input-group-text"
                    ><i class="fa fa-send"></i
                  ></span>
                </div>
                <input
                  v-on:keyup.enter="sendMessage"
                  v-model="newMessage"
                  type="text"
                  class="form-control"
                  placeholder="Enter text here..."
                />
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import ChatUsers from "./ChatUsers";
import ChatList from "./ChatList";
import authHeader from "../service/DataService";
import URLS from "../constants/urls";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

export default {
  name: "Chat",
  components: { ChatUsers, ChatList },
  data() {
    return {
      toUserName: null,
      chatKey: null,
      chatList: [],
      newMessage: "",
    };
  },
  methods: {
    toUserNameFilled(value) {
      this.disconnectWS();
      this.toUserName = value;
      this.loadChatList();
      this.loadChatKey();
      this.connectWS();
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
    loadChatKey() {
      axios
        .get(URLS.API_CHAT_KEY + "?withUsername=" + this.toUserName, {
          headers: authHeader(),
        })
        .then((res) => {
          if (res.status === 200) {
            this.chatKey = res.data;
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
            }
          })
          .catch((error) => {
            if (error.response.status === 401) {
              this.$router.push({ name: "Main" });
            }
          });
      }
    },

    connectWS() {
      this.socket = new SockJS(URLS.API_WS);
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect(
        {},
        (frame) => {
          this.connected = true;
          console.log(frame);
          this.stompClient.subscribe("/topic/chat/" + this.chatKey, (msg) => {
            this.onMessage(msg);
          });
        },
        (error) => {
          console.log(error);
          this.connected = false;
        }
      );
    },

    disconnectWS(){
      if (this.stompClient) {
        this.stompClient.disconnect();
      }
      this.connected = false;
    },

    onMessage(msg) {
      var messageId = JSON.parse(msg.body).messageId;
      this.loadNewMessage(messageId);
    },

    loadNewMessage(messageId){
      axios
        .get(URLS.API_CHAT + "/" + messageId, {
          headers: authHeader(),
        })
        .then((res) => {
          if (res.status === 200) {
            this.chatList.push(res.data);
          }
        })
        .catch((error) => {
          if (error.response.status === 401) {
            this.$router.push({ name: "Main" });
          }
        });
    }
  }
};
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
