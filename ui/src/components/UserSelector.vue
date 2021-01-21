/* eslint-disable */
<template>
  <div class="userSelector">
    <input
      type="text"
      placeholder="Komu psát"
      @change="toUserNameChanged"
    />
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "UserSelector",
  data(){
    return {

    }
  },
  methods: {
    toUserNameChanged(value){
      if(value){
        axios.get("http://localhost:8080/api/user/" + value.target.value).then(res => {
          if(res.status === 200){
            this.$emit('changed', res.data.username)
          }
        })
      }
    }
  }
}
</script>

<style scoped>

</style>
