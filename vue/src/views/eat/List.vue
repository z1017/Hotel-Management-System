<template>
  <div>
    <!--  搜索表单  -->
    <div style="margin: 10px 0 20px 5px">
      <el-input style="width: 200px; margin-right: 5px " placeholder="请输入菜品名称" v-model="params.name"></el-input>
      <el-input style="width: 170px" placeholder="请输入菜品编号" v-model="params.eatNo"></el-input>
      <el-button style="margin-left: 5px" type="primary" @click="load"><i class="el-icon-search"
                                                                          style="margin-right: 4px"></i>搜索
      </el-button>
      <el-button style="margin-left: 5px" type="warning" @click="reset"><i class="el-icon-refresh"
                                                                           style="margin-right: 4px"></i>重置
      </el-button>
    </div>
    <el-table :data="tableData" stripe row-key="id" default-expand-all>
      <el-table-column prop="id" label="编号" width="80%"></el-table-column>
      <el-table-column prop="name" label="餐品名称"></el-table-column>
      <el-table-column prop="description" width="100" label="描述"></el-table-column>
      <el-table-column prop="eatDate" label="发布日期"></el-table-column>
      <el-table-column prop="head" label="负责人"></el-table-column>
      <el-table-column prop="eatCategory" width="100" label="种类"></el-table-column>
      <el-table-column prop="eatNo" label="餐品编号"></el-table-column>
      <el-table-column prop="score" label="订餐积分"></el-table-column>
      <el-table-column prop="nums" label="数量"></el-table-column>

      <el-table-column prop="cover" label="封面">
        <template v-slot="scope">
          <el-image :src="scope.row.cover" :preview-src-list="[scope.row.cover]"></el-image>
        </template>

      </el-table-column>

      <el-table-column prop="createtime" label="创建时间"></el-table-column>
      <el-table-column prop="updatetime" label="更新时间"></el-table-column>
      <el-table-column label="操作" width="150">
        <template v-slot="scope">

          <!--          编辑-->
          <el-button type="primary" icon="el-icon-edit"
                     @click="$router.push('/editEat?id=' + scope.row.id)"></el-button>
          <el-popconfirm style="margin-left: 5px" title="您确定删除这行数据吗？"
                         @confirm="del(scope.row.id)"
          >
            <!--            删除-->
            <el-button type="danger" slot="reference" :loading="false" class="el-icon-delete"></el-button>
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
  name: 'EatList',
  data() {
    return {
      //admin:缓存里的数据
      admin: Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {},
      tableData: [],
      total: 0,
      params: {
        pageNum: 1,
        pageSize: 10,
        name: '',
        eatNo: '',
      },
    }
  },

  created() {
    this.load()
  },
  methods: {
    load() {
      request.get('/eat/page', {
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
        name: '',
        eatNo: '',
      }
      this.load()
    },
    handleCurrentChange(pageNum) {
      // 点击分页按钮 触发分页
      this.params.pageNum = pageNum;
      this.load()
    },

    del(id) {
      request.delete("/eat/delete/" + id).then(res => {

        if (res.code === '200') {
          this.$notify.success('删除成功')
          this.load()
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
  }
}
</script>

<style scoped>

</style>