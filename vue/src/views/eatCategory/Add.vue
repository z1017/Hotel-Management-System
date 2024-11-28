<template>
  <div style="padding: 10px ; width: 80%">
    <h3 style="margin-bottom: 20px">新增餐饮种类</h3>
    <el-form :inline="true" :rules="rules" ref="ruleForm" :model="form" label-width="100px">
      <el-form-item label="名称" prop="name">
        <el-input v-model="form.name" placeholder="请输入名称"></el-input>
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input v-model="form.remark" placeholder="请输入备注"></el-input>
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
  name: 'AddEatCategory',
  data() {
    return {
      form: {},
      rules: {
        name: [
          { required: true, message: '请输入分类名称', trigger: 'blur'}
        ],
      }
    }
  },
  methods: {
    save() {
      this.$refs['ruleForm'].validate((valid) => {
        if (valid) {
          request.post('/eatCategory/save', this.form).then(res => {
            if (res.code === '200') {
              this.$notify.success('新增成功')
              this.$refs['ruleForm'].resetFields()
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

