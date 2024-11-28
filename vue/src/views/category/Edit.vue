<template>
  <div style="padding: 10px ;width: 80%">
    <h3 style="margin-bottom: 20px">编辑二级客房</h3>
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
  name: 'EditCategory',
  data() {
    return {
      form: {},

      rules: {
        name: [
          { required: true, message: '请输入分类名称', trigger: 'blur'},
        ],

      }

    }
  },
  created() {
    const id = this.$route.query.id
    request.get("/category/" + id).then(res => {
      this.form = res.data
    })
  },
  methods: {
    save() {
      request.put('/category/update', this.form).then(res => {
        if (res.code === '200') {
          this.$notify.success('更新成功')
          this.$router.push("/categoryList")
        } else {
          this.$notify.error(res.msg)
        }
      })
    }
  }
}

</script>

