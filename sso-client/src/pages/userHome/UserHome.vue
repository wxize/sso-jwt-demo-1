<template>
  <div>
    <Header></Header>
    <div class="info_wrapper" shadow="always">
      <div class="avatar_wrapper">
        <el-upload
          class="avatar-uploader"
          action="https://jsonplaceholder.typicode.com/posts/"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
        >
          <img v-if="imageUrl" :src="imageUrl" class="avatar" />
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </div>
      <user-info :user="{ user }"></user-info>
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import { BASE_URL } from "@/config";
import UserInfo from "./UserInfo";
export default {
  name: "UserName",
  components: {
    Header,
    UserInfo,
  },
  data() {
    return {
      imageUrl: localStorage.getItem("avatar"),
      uploadUrl: BASE_URL + "/avatar",
      user: {
        nickname: "...",
        phone: "...",
      },
    };
  },
  mounted() {
    this.getUserInfo();
  },
  methods: {
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
      localStorage.setItem("avatar", this.imageUrl);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
    async getUserInfo() {
      this.user = await this.axios.get("/baseinfo");
    },
  },
};
</script>

<style lang="scss" scoped>
.info_wrapper {
  width: 600px;
  height: 300px;
  box-shadow: 0 0 10px #efefef;
  position: absolute;
  top: 50%;
  left: 50%;
  margin-top: -200px;
  margin-left: -300px;
}
.avatar_wrapper {
  width: 120px;
  height: 120px;
  background: #efefef;
  border: 1px solid #efefef;
  border-radius: 50%;
  position: absolute;
  left: 240px;
  top: -60px;
  overflow: hidden;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 120px;
  height: 120px;
  line-height: 120px;
  text-align: center;
}
.avatar {
  width: 120px;
  height: 120px;
  display: block;
}
</style>