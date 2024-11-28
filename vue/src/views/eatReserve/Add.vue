<template>
  <div style="padding: 10px ; width: 80%">
    <h3 style="margin-bottom: 20px">新增订餐记录</h3>
    <el-form :inline="true" :rules="rules" ref="ruleForm" :model="form" label-width="100px">
      <el-form-item label="餐饮编号" prop="eatNo">
        <el-select v-model="form.eatNo" clearable filterable placeholder="请选择" @change="selEat">
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

      <el-form-item label="预定的数量" prop="days">
        <el-input-number v-model="form.days" :min="1" :max="30" label="预定的数量"></el-input-number>
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
  name: 'addEat',
  data() {
    return {
      form: {days:1},
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
  },

  methods: {
    save() {
      this.$refs['ruleForm'].validate((valid) => {
        if (valid) {
          request.post('/eatReserve/save', this.form).then(res => {
            if (res.code === '200') {
              this.$notify.success('新增成功')
              this.$refs['ruleForm'].resetFields()
            } else {
              this.$notify.error(res.msg)
            }
          })
        }
      })
    },
    selEat() {
      const eat = this.eats.find(v => v.eatNo === this.form.eatNo)
      request.get('/eat/' + eat.id).then(res => {
        // 强制设置对象属性
        // 1. 对象 2. 对象的属性名称 3. 对象属性值
        this.$set(this.form, 'eatName', res.data.name)
        this.form.score = res.data.score
        this.form.nums = res.data.nums
      })
    },
    selUser() {
      const user = this.users.find(v => v.username === this.form.userNo)
      request.get('/user/' + user.id).then(res => {
        this.$set(this.form, 'userName', res.data.name)
        this.form.userPhone = res.data.phone
        this.form.account = res.data.account
      })
    }
  }
}

</script>

