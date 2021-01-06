<template>
  <div class="user_info_wrapper">
    <div class="info_item">
      <label>姓名</label>
      <span>{{ userFoo.user.nickname }} </span>
      <i class="iconfont iconxiugai" @click="handleName"></i>
    </div>
    <div class="info_item">
      <label>手机号</label>
      <span>{{ userFoo.user.phone }} </span>
      <i class="iconfont iconxiugai" @click="handlePhone"></i>
    </div>
    <div class="info_item">
      <label>密码</label>
      <span>******** </span>
      <i class="iconfont iconxiugai" @click="handlePassWord"></i>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserInfo",
  props: {
    user: {
      required: false,
      type: Object,
    },
  },
  computed: {
    userFoo: function () {
      return this.user;
    },
  },
  methods: {
    handleName: function () {
      this.$prompt("请输入姓名", "修改姓名", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
      })
        .then(({ value }) => {
          let newName = value;
          this.axios.post("/user/rename", {
            newName,
          });
          this.$message({
            type: "success",
            message: "您的姓名是: " + value,
          });
          this.userFoo.user.nickname = value;
          this.$store.commit('changeName', value)
          localStorage.setItem('nickName', value);
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "取消输入",
          });
        });
    },
    handlePhone: function() {
        console.log(123)
    },
    handlePassWord: function() {
        console.log(456)
    }
  },
};
</script>

<style scoped lang="scss">
.user_info_wrapper {
  width: 400px;
  margin: 100px auto 0;
  padding-top: 10px;
  .info_item {
    height: 50px;
    line-height: 50px;
    border-bottom: 1px solid #efefef;
    overflow: hidden;
    label {
      display: block;
      width: 100px;
      float: left;
    }
    .iconfont {
      font-size: 16px;
      float: right;
      cursor: pointer;
    }
  }
}
</style>
