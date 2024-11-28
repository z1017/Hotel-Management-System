<template>
  <div style="width: 80%">
    <div style="margin-bottom: 30px">编辑客房</div>
    <el-form :inline="true" :rules="rules" ref="ruleForm" :model="form" label-width="100px">
      <el-form-item label="房间号" prop="hotelNo">
        <el-select v-model="form.hotelNo" clearable filterable placeholder="请选择" @change="selhotel">
          <el-option
              v-for="item in hotels"
              :key="item.id"
              :label="item.hotelNo"
              :value="item.hotelNo">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="房间名称" prop="hotelName">
        <el-input v-model="form.hotelName" disabled></el-input>
      </el-form-item>
      <el-form-item label="所需积分" prop="score">
        <el-input v-model="form.score" disabled></el-input>
      </el-form-item>
      <el-form-item label="房间数量" prop="nums">
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
  name: 'EditHotel',
  data() {
    return {
      form: {},
      hotels: [],
      users: [],
      rules: {
        hotelNo: [
          {required: true, message: '请输入房间号', trigger: 'blur'}
        ],
        userNo: [
          {required: true, message: '请输入会员码', trigger: 'blur'}
        ]
      }
    }
  },

  created() {
    request.get('/hotel/list').then(res => {
      this.hotels = res.data
    })

    request.get('/user/list').then(res => {
      this.users = res.data.filter(v => v.status)
    })

    const id = this.$route.query.id
    request.get("/reserve/" + id).then(res => {
      this.form = res.data
    })
  },
  methods: {
    save() {
      request.put('/reserve/update', this.form).then(res => {
        if (res.code === '200') {
          this.$notify.success('更新成功')
          this.$router.push("/hotelList")
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
    selHotel() {
      const hotel = this.hotels.find(v => v.hotelNo === this.form.hotelNo)
      request.get('/hotel/' + hotel.id).then(res => {
        this.form.hotelName = res.data.name
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

