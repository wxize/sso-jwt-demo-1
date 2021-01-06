<template>
  <div class="head_wrapper">
    <router-link class="head_left" to="/">
      <slot name="head">蜂鸟系统</slot>
    </router-link>
    <div class="head_right">
      <div class="help_word" @click="handleHelp">
        <i class="iconfont iconbangzhuwendang"></i> 帮助文档
      </div>
      <div class="current_user" @click="handleUser">
        <img class="avatar_img" :src="avatar" alt="" />
        <span class="user_name">{{ nickname }}</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Header",
  computed: {
    nickname() {
      return this.$store.state.user.nickname
    },
    avatar() {
        return localStorage.getItem("avatar");
    }
  },
  methods: {
    handleHelp() {
      window.open("https://synjone.github.io/docs/", "_blank");
    },
    handleUser() {
      this.$router.push({ path: "/userhome" });
    },
  },
};
</script>

<style lang="scss" scoped>
.head_wrapper {
  width: 100%;
  display: flex;
  height: 50px;
  line-height: 50px;
  justify-content: space-between;
  border-bottom: 1px solid #efefef;
  .head_left {
    width: 160px;
    text-align: center;
    font-size: 18px;
    font-weight: 600;
    cursor: pointer;
  }
  .head_right {
    width: 300px;
    display: flex;
    justify-content: space-around;
    .help_word {
      cursor: pointer;
    }
    .current_user {
      cursor: pointer;
    }
    .avatar_img {
      width: 30px;
      height: 30px;
      display: inline-block;
      border-radius: 50%;
      margin-top: 10px;
      margin-right: 4px;
    }
  }
}
</style>
