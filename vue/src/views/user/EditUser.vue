<template>
  <div style="padding: 10px ; width: 80%">
    <h3 style="margin-bottom: 20px">编辑用户</h3>
    <el-form :inline="true" :model="form" label-width="100px">
      <el-form-item label="卡号">
        <el-input v-model="form.username" disabled></el-input>
      </el-form-item>
      <el-form-item label="姓名">
        <el-input v-model="form.name" placeholder="请输入姓名"></el-input>
      </el-form-item>

      <el-form-item label="年龄">
        <el-input v-model="form.age" placeholder="请输入年龄"></el-input>
      </el-form-item>
      <el-form-item label="性别">
<!--        <el-input v-model="form.sex" placeholder="请输入性别"></el-input>-->
        <el-radio v-model="form.sex" label="女">女</el-radio>
        <el-radio v-model="form.sex" label="男">男</el-radio>
        <el-radio v-model="form.sex" label="其他">其他</el-radio>

        <!--        <template>-->
        <!--          <el-select v-model="form.sex" clearable  placeholder="请选择">-->
        <!--            <el-option-->
        <!--                v-for="item in options"-->
        <!--                :key="item.value"-->
        <!--                :label="item.label"-->
        <!--                :value="item.value">-->
        <!--            </el-option>-->
        <!--          </el-select>-->
        <!--        </template>-->


      </el-form-item>
      <el-form-item label="联系方式">
        <el-input v-model="form.phone" placeholder="请输入联系方式"></el-input>
      </el-form-item>
      <el-form-item label="地址">
        <el-input v-model="form.address" placeholder="请输入地址"></el-input>
      </el-form-item>
    </el-form>

    <div style="text-align: center; margin-top: 20px;">
      <!--      <el-button type="primary" round>主要按钮</el-button>-->
      <el-button type="primary" round style="width: 200px; font-size: 16px" @click="save" size:="medium">提交</el-button>
      <!--      <el-button type="danger" >取消</el-button>-->
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "EditUser",
  data() {
    return {
      form: {},
      // options: [{
      //   value: '选项1',
      //   label: '女'
      // }, {
      //   value: '选项2',
      //   label: '男'
      // }, {
      //   value: '选项3',
      //   label: '其他'
      // }],
      // value: ''
    }
  },
  created() {
    const id = this.$route.query.id
    request.get("/user/" + id).then(res => {
      this.form = res.data

    })
  },
  methods: {
    save() {
      request.put('/user/update', this.form).then(res => {
        if (res.code === '200') {
          this.$notify.success('更新成功')
          this.$router.push("/userList")
        } else {
          this.$notify.error(res.msg)
        }
      })
    }
  }
}


</script>

<style scoped>

</style>