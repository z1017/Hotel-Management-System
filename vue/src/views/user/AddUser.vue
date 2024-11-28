<template>
  <div style="padding: 10px ; width: 80%">
    <h3 style="margin-bottom: 20px">新增会员</h3>
    <el-form :inline="true" :model="form" :rules="rules" ref="ruleForm" label-width="100px">
      <el-form-item label="姓名" prop="name">
        <el-input v-model="form.name" status-icon :rules="rules" ref="ruleForm" placeholder="请输入姓名"></el-input>
      </el-form-item>

      <el-form-item label="年龄" prop="age">
        <el-input v-model.number="form.age" placeholder="请输入年龄"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-radio v-model="form.sex" label="女">女</el-radio>
        <el-radio v-model="form.sex" label="男">男</el-radio>
        <el-radio v-model="form.sex" label="其他">其他</el-radio>

        <!--                <el-input v-model="form.sex" placeholder="请输入性别"></el-input>-->
      </el-form-item>
      <el-form-item label="联系方式" prop="phone">
        <el-input v-model="form.phone" placeholder="请输入联系方式"></el-input>
      </el-form-item>
      <el-form-item label="地址" prop="address">
        <el-input v-model="form.address" placeholder="请输入地址"></el-input>
      </el-form-item>
      <el-form-item label="积分" prop="account">
        <el-input v-model="form.account" placeholder="请输入积分"></el-input>
      </el-form-item>
    </el-form>

    <div style="text-align: center; margin-top: 20px;">
      <el-button type="primary" round style="width: 200px; font-size: 16px" @click="save" size:="medium">提交</el-button>
      <!--      <el-button type="danger" >取消</el-button>-->
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "AddUser",
  data() {
    const checkAge = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('年龄不能为空'));
      }
      if (!Number.isInteger(value)) {
        callback(new Error('请输入数字值'));
        // } else {
        //   if (value < 18) {
        //     callback(new Error('必须年满18岁'));
        //   }
      }
      if (parseInt(value) > 130 || parseInt(value) <= 0) {
        callback(new Error('请输入合理的年龄'));
      } else {
        callback();
      }
    };
    const checkPhone = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('联系方式不能为空'));
      }
      if (!/^[1][3,4,5,6,7,8,9][0-9]{9}$/.test(value)) {
        callback(new Error('请输入合法号码'));
      } else {
        callback();
      }
    };

    const checkAddress = (rule, value, callback) => {
      if (/^[0-9]+$/.test(value)) {
        callback(new Error('请输入合法地址'));
      } else {
        callback();
      }
    };
    return {
      form: {sex: '女'},
      rules: {
        name: [
          {required: true, message: '请输入姓名', trigger: 'blur'},
          // {min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur'}
        ],
        age: [
          {validator: checkAge, required: true, trigger: 'blur'}
        ],
        phone: [
          {validator: checkPhone, required: true, trigger: 'blur'}
        ],

        address: [
          {validator: checkAddress, trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    save() {
      this.$refs['ruleForm'].validate((valid) => {
        if (valid) {
          request.post('/user/save', this.form).then(res => {
            if (res.code === '200') {
              this.$notify.success('新增成功')
              this.$refs['ruleForm'].resetFields()
              this.form = {sex: '女'}
            } else {
              this.$notify.error(res.msg)
            }
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>