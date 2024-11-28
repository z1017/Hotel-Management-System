<template>
  <div style="width: 80%">
    <div style="margin-bottom: 30px"></div>
    <el-form :inline="true" :rules="rules" ref="ruleForm" :model="form" label-width="100px">
      <el-form-item label="餐品编号" prop="eatNo">
        <el-select v-model="form.eatNo" clearable filterable placeholder="请选择" @change="seleat">
          <el-option
              v-for="item in eats"
              :key="item.id"
              :label="item.eatNo"
              :value="item.eatNo">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="菜品名称" prop="eatName">
        <el-input v-model="form.eatName" disabled></el-input>
      </el-form-item>
      <el-form-item label="所需积分" prop="score">
        <el-input v-model="form.score" disabled></el-input>
      </el-form-item>
      <el-form-item label="菜品数量" prop="nums">
        <el-input v-model="form.nums" disabled></el-input>
      </el-form-item>

      <br/>

      <el-form-item label="会员码" prop="userNo">
        <el-select v-model="form.userNo" filterable placeholder="请选择" @change="selUser">
          <el-option
              v-for="item in users"
              :key="item.id"
              :label="item.username"
              :value="item.username">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="用户名称" prop="userName">
        <el-input disabled v-model="form.userName"></el-input>
      </el-form-item>
      <el-form-item label="用户联系方式" prop="userPhone">
        <el-input disabled v-model="form.userPhone"></el-input>
      </el-form-item>
      <el-form-item label="用户账户积分" prop="account">
        <el-input disabled v-model="form.account"></el-input>
      </el-form-item>
      
    </el-form>

    <div style="text-align: center; margin-top: 20px;">
      <el-button type="primary" round style="width: 200px; font-size: 16px" @click="save" size:="medium">提交</el-button>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: 'EditEat',
  data() {
    return {
      form: {},
      eats: [],
      users: [],
      rules: {
        eatNo: [
          {required: true, message: '请输入菜品编号', trigger: 'blur'}
        ],
        userNo: [
          {required: true, message: '请输入会员码', trigger: 'blur'}
        ]
      }
    }
  },

  created() {
    request.get('/eat/list').then(res => {
      this.eats = res.data
    })

    request.get('/user/list').then(res => {
      this.users = res.data.filter(v => v.status)
    })

    const id = this.$route.query.id
    request.get("/eatReserve/" + id).then(res => {
      this.form = res.data
    })
  },
  methods: {
    save() {
      request.put('/eatReserve/update', this.form).then(res => {
        if (res.code === '200') {
          this.$notify.success('更新成功')
          this.$router.push("/eatList")
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
    selHotel() {
      const eat = this.eats.find(v => v.eatNo === this.form.eatNo)
      request.get('/eat/' + eat.id).then(res => {
        this.form.eatName = res.data.name
        this.form.score = res.data.score
        this.form.nums = res.data.nums
      })
    },
    selUser() {
      const user = this.users.find(v => v.username === this.form.userNo)
      request.get('/user/' + user.id).then(res => {
        this.form.userName = res.data.name
        this.form.userPhone = res.data.phone
        this.form.account = res.data.account
      })
    }

  }
}
</script>

