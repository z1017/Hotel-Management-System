<template>
  <div>
    <!--  搜索表单  -->
    <div style="margin: 10px 0 20px 5px">
      <el-input style="width: 200px; margin-right: 5px " placeholder="请输入客房名称" v-model="params.name"></el-input>
      <el-input style="width: 170px; margin-right: 5px" placeholder="请输入房间号" v-model="params.hotelNo"></el-input>
      <el-input style="width: 170px" placeholder="请输入用户名称" v-model="params.userName"></el-input>

      <el-button style="margin-left: 5px" type="primary" @click="load"><i class="el-icon-search"
                                                                          style="margin-right: 4px"></i>搜索
      </el-button>
      <el-button style="margin-left: 5px" type="warning" @click="reset"><i class="el-icon-refresh"
                                                                           style="margin-right: 4px"></i>重置
      </el-button>
    </div>

    <el-table :data="tableData" stripe row-key="id" default-expand-all>
      <el-table-column prop="id" label="编号" width="80%"></el-table-column>
      <el-table-column prop="hotelName" label="客房名称"></el-table-column>
      <el-table-column prop="hotelNo" label="房间号"></el-table-column>
      <el-table-column prop="userNo" label="会员码"></el-table-column>
      <el-table-column prop="userName" label="用户名称"></el-table-column>
      <el-table-column prop="userPhone" width="120" label="用户联系方式"></el-table-column>
      <el-table-column prop="score" label="所用积分"></el-table-column>
      <el-table-column prop="status" label="订房状态"></el-table-column>
      <el-table-column prop="createtime" width="100" label="订房时间"></el-table-column>
      <el-table-column prop="days" label="预定天数"></el-table-column>
      <el-table-column prop="returnDate"  label="退房日期"></el-table-column>
      <el-table-column prop="realDate" width="120" label="实际归还日期"></el-table-column>

      <el-table-column label="操作" >
        <template v-slot="scope">
          <el-popconfirm style="margin-left: 5px" title="您确定删除这行数据吗？"
                         @confirm="del(scope.row.id)"
          >
            <el-button type="danger" slot="reference" :loading="false">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <!--  分页  -->
    <div style="margin-top: 20px;">
      <el-pagination
          background
          :current-page="params.pageNum"
          :page-size="params.pageSize"
          layout="prev, pager, next"
          @current-change="handleCurrentChange"
          :total="total">
      </el-pagination>
    </div>

  </div>
</template>
<script>
import request from "@/utils/request";
import Cookies from 'js-cookie'

export default {
  name: 'ReturList',
  data() {
    return {
      admin: Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {},
      tableData: [],
      total: 0,
      params: {
        pageNum: 1,
        pageSize: 10,
        name: '',
        hotelNo: '',
      },
    }
  },

  created() {
    this.load()
  },
  methods: {
    load() {
      request.get('/reserve/pageRetur', {
        params: this.params
      }).then(res => {
        if (res.code === '200') {
          this.tableData = res.data.list
          this.total = res.data.total
        }
      })
    },
    reset() {
      this.params = {
        pageNum: 1,
        pageSize: 10,
        hotelName: '',
        hotelNo: '',
        userName: '',
      }
      this.load()
    },
    handleCurrentChange(pageNum) {
      // 点击分页按钮 触发分页
      this.params.pageNum = pageNum;
      this.load()
    },

    del(id) {
      request.delete("/reserve/deleteRetur/" + id).then(res => {
        if (res.code === '200') {
          this.$notify.success('删除成功')
          this.load()
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
    returnHotels(row) {
    }
  }
}
</script>

<style scoped>

</style>