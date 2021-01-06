<template>
  <div class="login">
    <div class="panel">
      <p class="title">注册</p>
      <div class="title-bar"></div>
      <div class="title-line"></div>

      <div class="body">
        <div class="input-container">
          <input
            type="text"
            placeholder="11位手机号"
            v-model="phoneNum"
            oninput="value=value.replace(/[^\d]/g,'')"
            maxlength="11"
          />
        </div>
        <div class="input-container code">
          <input
            type="text"
            oninput="value=value.replace(/[^\d]/g,'')"
            placeholder="输入验证码"
            v-model="phoneCode"
            @blur="testPhoneCode()"
          />
          <button @click="getPhoneCode">{{ codeStatus.text }}</button>
        </div>
        <div class="input-container">
          <input
            v-model="password"
            type="password"
            placeholder="6-16位密码，区分大小写"
            @blur="testPass()"
          />
        </div>
        <div class="input-container">
          <input
            v-model="repassword"
            type="password"
            placeholder="确认密码"
            @blur="testRePassWord()"
          />
        </div>
        <button class="ctrl-button" @click="registerHandler">注 册</button>
        <router-link class="register clearfix" to="/login"
          >使用已有帐户登录</router-link
        >
      </div>
    </div>
  </div>
</template>

<script>
import Countdown from "countdown-lft";
let countdown = new Countdown();

import { PHONE_CODE_TIME } from "@/config";
import { testPhoneNum, testPassWord } from "@/utils/test";

export default {
  name: "Register",
  data() {
    return {
      phoneNum: "",
      phoneCode: "",
      password: "",
      repassword: "",
      // 验证码状态
      codeStatus: {
        TIME_DOWN: PHONE_CODE_TIME,
        effective: true,
        text1: "获取验证码",
        text2: "重新获取",
        text: "获取验证码",
      },
    };
  },
  methods: {
    testPhoneCode() {
      if (this.phoneCode == "") {
        this.$message({
          message: "验证码不能为空",
          type: "warning",
        });
        return false;
      }
      return true;
    },
    testPass() {
       let self = this;
      testPassWord(self, this.password)
    },
    testRePassWord() {
      if (this.repassword == "") {
        this.$message({
          message: "密码不能为空",
          type: "warning",
        });
        return false;
      } else if (this.repassword != this.password) {
        this.$message({
          message: "两条密码不一致",
          type: "warning",
        });
        return false;
      }
      return true;
    },
    checkboxHandler(e) {
      let bool = !e.target.selected;
      e.target.selected = bool;
      let str = bool ? "√" : " ";
      e.target.innerText = str;
    },
    registerHandler() {
      let self = this;
      if (
        testPhoneNum(self, this.phoneNum) &&
        this.testPhoneCode() &&
        testPassWord(self, this.password) &&
        this.testRePassWord()
      ) {
        const param = {
          phone: this.phoneNum,
          code: this.phoneCode,
          pwd: this.password,
        };
        this.axios
          .post("/register", param)
          .then((res) => {
            if (res.data.error === 0) {
              this.$router.push({ path: "/login" });
            }
          })
          .catch((err) => console.log(err));
      }
    },

    getPhoneCode(e) {
      let self = this;
      let c = this.codeStatus;

      if (testPhoneNum(self, this.phoneNum)) {
        if (!c.effective) {
          return;
        }
        c.effective = false;
        console.log(e);

        c.text = c.text2 + `(${c.TIME_DOWN}s)`;

        countdown.start({
          totalTime: c.TIME_DOWN * 1000,
          interval: 1000,
          onTime: (val) => {
            c.text = c.text2 + `(${val.second}s)`;
          },
          onComplete: (duration) => {
            console.log(duration);
            c.text = c.text1;
            c.effective = true;
          },
        });
        this.axios
          .get("/phone/code", {
            params: {
              phone: Number(this.phoneNum),
            },
          })
          .then((res) => {
            console.log(res);
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
  //   添加背景图
  background: url(../../assets/imgs/login_bg.png) no-repeat;
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
    background: url(../../assets/imgs/login_panel_bg.png) no-repeat;
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
        // ... 应该作用于 input 上，， 尴尬
        input::-webkit-input-placeholder {
          /* WebKit browsers 适配谷歌 */
          color: rgba(0, 0, 0, 0.25);
        }
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
      .code {
        display: flex;
        flex-direction: row;

        input {
          // box-sizing: border-box;
          flex: 1;
        }
        button {
          width: 80px;
          font-size: 16px;
          font-family: PingFangSC-Medium, PingFang SC;
          font-weight: 500;
          color: rgba(0, 0, 0, 0.85);
          line-height: 22px;
          width: 126px;
          height: 50px;
          background: #ffffff;
          border-radius: 2px;
          border: 1px solid rgba(0, 0, 0, 0.1);
          outline: none;
          cursor: pointer;
          margin-left: 10px;
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
          .mycheckbox {
            display: inline-block;
            // position: absolute;
            width: 14px;
            height: 14px;
            background: #ffffff;
            border-radius: 2px;
            border: 1px solid rgba(0, 0, 0, 0.1);
            font-size: 12px;
            cursor: pointer;
            // visibility: visible;
            // pointer-events: none;
          }
          //   input[type="checkbox"]::after {
          //     content: " ";
          //     display: inline-block;
          //     position: absolute;
          //     width: 14px;
          //     height: 14px;
          //     background: #ffffff;
          //     border-radius: 2px;
          //     border: 1px solid rgba(0, 0, 0, 0.1);
          //     visibility: visible;
          //     pointer-events: none;
          //   }
          //   .mycheckbox[selected] {
          //     content: "\2713";
          //     // display: inline-block;
          //     // position: absolute;
          //     // width: 14px;
          //     // height: 14px;
          //     // background: #ffffff;
          //     // border-radius: 2px;
          //     // border: 1px solid rgba(0, 0, 0, 0.1);
          //     // visibility: visible;
          //     // pointer-events: none;
          //   }
          span {
            margin: 0 13px;
            width: 64px;
            height: 22px;
            font-size: 16px;
            font-family: PingFangSC-Medium, PingFang SC;
            font-weight: 500;
            color: rgba(0, 0, 0, 0.25);
            line-height: 22px;
          }
        }
      }
      .ctrl-button {
        width: 357px;
        height: 50px;
        background: linear-gradient(90deg, #0c8bff 0%, #2e5dfe 100%);
        border-radius: 2px;
        font-size: 20px;
        font-family: PingFangSC-Semibold, PingFang SC;
        font-weight: 600;
        color: #ffffff;
        margin-top: 60px;
        border: none;
        outline: none;
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
