<template>
  <div style="padding: 10px ; width: 80%">
    <h3 style="margin-bottom: 20px">新增菜品</h3>
    <el-form :inline="true" :rules="rules" ref="ruleForm" :model="form" label-width="100px">
      <el-form-item label="名称" prop="name">
        <el-input v-model="form.name" placeholder="请输入名称"></el-input>
      </el-form-item>
      <el-form-item label="描述" prop="description">
        <el-input style="width: 190px" type="textarea" v-model="form.description" placeholder="请输入描述"></el-input>
      </el-form-item>
      <el-form-item label="发布日期" prop="eatDate">
        <el-date-picker
            style="width: 190px"
            v-model="form.eatDate"
            type="date"
            placeholder="请选择发布日期">
        </el-date-picker>
      </el-form-item>

      <el-form-item label="负责人" prop="head">
        <el-input v-model="form.head" placeholder="请输入负责人"></el-input>
      </el-form-item>


      <el-form-item label="分类" prop="eatCategory">
        <el-cascader
            style="width: 190px"
            :props="{ value: 'name', label: 'name'}"
            v-model="form.eatCategories"
            :options="eatCategories">
        </el-cascader>
      </el-form-item>

      <el-form-item label="餐品编号" prop="eatNo">
        <el-input v-model="form.eatNo" placeholder="请输入餐品编号"></el-input>
      </el-form-item>

      <el-form-item label="积分" prop="score">
        <el-input-number v-model="form.score" :min="10" :max="3000000" label="所需积分"></el-input-number>
      </el-form-item>

      <el-form-item label="数量" prop="nums">
        <el-input v-model="form.nums" placeholder="请输入数量"></el-input>
      </el-form-item>

<br/>
            <el-form-item label="封面" prop="cover">
              <el-upload
                  class="avatar-uploader"
                  :action="'http://localhost:9090/api/hotel/file/upload?token=' + this.admin.token"
                  :show-file-list="false"
                  :on-success="handleCoverSuccess">
                <img v-if="form.cover" :src="form.cover" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
            </el-form-item>
    </el-form>

    <div style="text-align: center; margin-top: 20px;">
      <el-button type="primary" round style="width: 200px; font-size: 16px" @click="save" size:="medium">提交</el-button>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
import Cookies from "js-cookie";

export default {
  name: 'AddEat',
  data() {
    const checkNums = (rule, value, callback) => {
      value = parseInt(value)
      if (value < 0 || value >= 1000) {
        callback(new Error('请输入大于等于0小于1000的整数'));
      }
      callback()
    };

    return {
      admin: Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {},
      form: {score: 10, cover: ''},
      eatCategories: [],
      rules: {
        name: [
          {required: true, message: '请输入分类名称', trigger: 'blur'}
        ],
        eatNo: [
          {required: true, message: '请输入菜品编号', trigger: 'blur'}
        ],
        score: [
          {validator: checkNums, trigger: 'blur'}
        ],
        nums: [
          {required: true, message: '请输入菜品数量', trigger: 'blur'},
          {validator: checkNums, trigger: 'blur'}
        ],
      }
    }
  },
  created() {
    request.get('/eatCategory/tree').then(res => {
      this.eatCategories = res.data
    })
  },
  methods: {
    handleCoverSuccess(res) {
      if (res.code === '200') {
        this.form.cover = res.data
      }
    },
    save() {
      this.$refs['ruleForm'].validate((valid) => {
        if (valid) {
          request.post('/eat/save', this.form).then(res => {
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

<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}

.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
