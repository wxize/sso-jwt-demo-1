<template>
  <div class="login">
    <div class="panel">
      <p class="title">账号密码登录</p>
      <div class="title-bar"></div>
      <div class="title-line"></div>
      <div class="body">
        <div class="input-container">
          <p class="input-title">手机号</p>
          <input
            type="text"
            v-model="phoneNum"
            oninput="value=value.replace(/[^\d]/g,'')"
            maxlength="11"
            @blur="testPhone()"
          />
        </div>
        <div class="input-container">
          <p class="input-title">密码</p>
          <input v-model="password" type="password" @blur="testPass()" />
        </div>
        <div class="status">
          <div class="checkbox">
            <i
              class="iconfont"
              :class="{ iconduoxuan1: iconduoxuan1, iconduoxuan: iconduoxuan }"
              @click="checkboxHandler"
            ></i>
            <span>自动登录</span>
          </div>
          <router-link class="forget" to="/forget">忘记密码</router-link>
        </div>
        <button @click="loginHandler">登 录</button>
        <router-link class="register clearfix" to="/register"
          >注册账户</router-link
        >
      </div>
    </div>
  </div>
</template>

<script>


import { testPhoneNum, testPassWord } from "@/utils/test";
import LoginModel from '@/model/api/Login'
import store from './store'


export default {
  name: "Login",
  data: function () {
    return {
      phoneNum: "",
      password: "",
      iconduoxuan1: true,
      iconduoxuan: false,
    };
  },
  methods: {
    testPhone() {
      let self = this;
      testPhoneNum(self, this.phoneNum);
    },
    testPass() {
      let self = this;
      testPassWord(self, this.password);
    },
    checkboxHandler() {
      this.iconduoxuan1 = !this.iconduoxuan1;
      this.iconduoxuan = !this.iconduoxuan;
    },
    loginHandler() {
      let self = this;
      
      let m = new LoginModel(this.phoneNum, this.password)
      
      if (
        testPhoneNum(self, this.phoneNum) &&
        testPassWord(self, this.password)
      ) {
        this.axios
          .post("/login", m)
          .then((res) => {
            if (res) {
              if (res.token) {
                localStorage.setItem('token', res.token)
                // this.$router.push({ path: "/" });
                // 这里跳转到 来的的地址,
                window.location.replace(store.redirectUrl)
              }
            } else {
              this.$message.error("账号密码错误");
            }
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.login {
  position: fixed;
  width: 100%;
  height: 100%;
  background-color: #fff9f9f9;
  display: flex;
  justify-content: center;
  align-items: center;
  background: url("../../assets/imgs/login_bg.png") no-repeat;
  background-size: 100% auto;
  background-size: cover;
  background-position: center;
  a {
    text-decoration: none;
  }
  .project-name {
    position: fixed;
    left: 135px;
    top: 54px;
    width: 80px;
    height: 22px;
    font-size: 20px;
    font-family: PingFangSC-Semibold, PingFang SC;
    font-weight: 600;
    color: rgba(0, 0, 0, 0.85);
    line-height: 22px;
  }
  .panel {
    text-align: center;
    width: 440px;
    // height: 562px;
    background: linear-gradient(180deg, #fafbff 0%, #ffffff 100%);
    box-shadow: 0px 2px 11px 0px #cdd2e5;
    border-radius: 4px;
    //   添加背景图

    background-size: 100% auto;
    .title {
      // width: 120px;
      // height: 22px;
      font-size: 20px;
      font-family: PingFangSC-Semibold, PingFang SC;
      font-weight: 600;
      color: rgba(0, 0, 0, 0.85);
      line-height: 22px;
      margin-top: 42px;
      margin-bottom: 10px;
    }
    .title-bar {
      width: 64px;
      height: 4px;
      background: #045ffd;
      margin: 0 auto;
    }
    .title-line {
      width: 440px;
      height: 2px;
      background-color: #f1f1f1;
    }
    .body {
      margin: 0 auto;
      width: 357px;
      padding-top: 60px;
      .input-container {
        // width: 357px;
        margin: 0 auto;
        margin-top: 24px;
        text-align: left;
        .input-title {
          //   width: 200px;
          //   height: 22px;
          font-size: 16px;
          font-family: PingFangSC-Medium, PingFang SC;
          font-weight: 500;
          color: rgba(0, 0, 0, 0.85);
          line-height: 32px;
          margin-left: 14px;
        }
        input {
          width: 100%;
          height: 50px;
          background: #ffffff;
          border-radius: 2px;
          border: 1px solid rgba(0, 0, 0, 0.1);
          line-height: 50px;
          padding: 0 16px;
          font-size: 16px;
          font-family: PingFangSC-Medium, PingFang SC;
          font-weight: 500;
          color: rgba(0, 0, 0, 0.85);
          line-height: 22px;
          box-sizing: border-box;
        }
      }
      .status {
        margin-top: 27px;
        display: flex;
        justify-content: space-between;
        align-items: center;
        color: rgba(0, 0, 0, 0.25);
        a {
          color: rgba(0, 0, 0, 0.25);
        }
        .checkbox {
          display: flex;
          align-items: center;
          .checkbox-base {
            background-color: white;
            width: 14px;
            height: 14px;
          }
          .iconfont {
            cursor: pointer;
          }
          .iconduoxuan {
              color: #045ffd;
          }
          span {
            margin: 0 4px;
            width: 70px;
            height: 22px;
            font-size: 16px;
            font-family: PingFangSC-Medium, PingFang SC;
            font-weight: 500;
            color: rgba(0, 0, 0, 0.25);
            line-height: 22px;
          }
        }
        .forget {
          cursor: pointer;
          font-size: 16px;
        }
      }
      button {
        width: 357px;
        height: 50px;
        background: linear-gradient(90deg, #0c8bff 0%, #2e5dfe 100%);
        border-radius: 2px;
        font-size: 20px;
        font-family: PingFangSC-Semibold, PingFang SC;
        font-weight: 600;
        color: #ffffff;
        margin-top: 60px;
        border: 0;
        cursor: pointer;
      }
      .register {
        float: right;
        margin-top: 22px;
        margin-bottom: 47px;
        // width: 64px;
        height: 22px;
        font-size: 16px;
        font-family: PingFangSC-Medium, PingFang SC;
        font-weight: 500;
        color: #2877ff;
        line-height: 22px;
      }
      .clearfix {
        zoom: 1;
      }
      .clearfix::after {
        content: "020";
        display: block;
        height: 0;
        clear: both;
        visibility: hidden;
      }
    }
  }
}
</style>
