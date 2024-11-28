<template>
  <div style="height: 100vh; overflow: hidden; position: relative">
    <el-card class="cover" v-if="loginAdmin.id">
      <slide-verify :l="42"
                    :r="10"
                    :w="310"
                    :h="155"
                    :accuracy="5"
                    slider-text="向右滑动"
                    @success="onSuccess"
                    @fail="onFail"
                    @refresh="onRefresh"
      ></slide-verify>
    </el-card>


    <div style="height: 100vh; overflow: hidden; position: relative" class="mag">

      <div class="loginPart">
        <div style="margin: 20px; text-align: center; font-size: 30px; font-weight: bold; color: white">立即登录</div>
        <el-form :model="admin" :rules="rules" ref="loginForm">
          <el-form-item prop="username" class="inputElement">
            <!--          prefix-icon="el-icon-user"-->
            <el-input placeholder="请输入账号" size="medium" v-model="admin.username"></el-input>
          </el-form-item>

          <el-form-item prop="password" class="inputElement" style="margin-top: 40px;">
            <!--            prefix-icon="el-icon-lock"-->
            <el-input placeholder="请输入密码" show-password size="medium" v-model="admin.password"></el-input>
          </el-form-item>

          <el-form-item style="margin-top: 50px">

            <el-button style="width: 100%" size="medium" type="primary" @click="login">登录</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>

import Cookies from 'js-cookie'
import request from "@/utils/request";

export default {
  name: 'LOGIN',
  data() {
    return {
      admin: {},
      loginAdmin: {},
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 3, max: 10, message: '长度在3-10个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 4, max: 10, message: '长度在4-10个字符', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    login() {
      this.$refs['loginForm'].validate((valid) => {
        if (valid) {
          request.post('/admin/login', this.admin).then(res => {
            if (res.code === '200') {
              this.loginAdmin = res.data  // 滑块组件就出现了
            } else {
              this.$notify.error(res.msg)
            }
          })
        }
      })
    },
    onSuccess() { // 滑块验证通过之后触发的
      Cookies.set('admin', JSON.stringify(this.loginAdmin))
      this.$notify.success("登录成功")
      this.$router.push('/')
    },
    onFail() {
      console.log('onFail')
    },
    onRefresh() {
      console.log('refresh')
    }
  }
}
</script>

<style>
.cover {
  width: fit-content;
  background-color: white;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 1000;
}
</style>