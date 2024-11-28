<template>
  <div>
    <!--  头部区域  -->
    <div style="height: 60px; line-height: 60px; background-color: white; margin-bottom: 2px; display: flex">
      <div style="width: 400px">
        <img src="@/assets/logo.png" alt="" style="width: 40px; position: relative; top: 10px; left: 20px">
        <span style="margin-left: 25px; font-size: 24px">酒店餐饮管理系统</span>
      </div>

<!--      模式调整-->

<!--      <input type="checkbox" id="switch" @click="Mode"/>-->
<!--      <label for="switch" >模式切换</label>-->

      <el-button type="warning" plain id="switch" @click="Mode" style="margin-top: 20px" mini>切换模式</el-button>



      <div style="flex: 1; text-align: right;padding-right: 20px ">
        <el-dropdown size="medium" style="cursor: pointer">
          <i class="el-icon-s-custom" style="color: #f6d03e"></i>
          <span class="el-dropdown-link">

            {{ admin.username }}<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown" style="margin-top: -5px">
            <el-dropdown-item>
              <div style="width: 50px; text-align: center;" @click="logout">退出</div>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>

    </div>

    <!--  侧边栏和主体  -->
    <div style="display: flex; ">
      <!--   侧边栏导航   -->
      <!--   min-height:100vh; 占满全屏   -->
      <!--      :default-openeds="['/']"  默认打开的二级菜单  -->
<!--/*      <div style="width: 200px;background-color: lightslategray; min-height:calc(100vh - 62px); overflow: hidden; margin-right: 2px; background-color: #334672">*/-->
        <div style="width: 200px;  min-height: calc(100vh - 62px); overflow: hidden; margin-right: 2px; background-color: white">

        <el-menu :default-active="$route.path" router class="el-menu-demo" style="margin-bottom: 10px">
<!--                 background-color="#334672"-->
<!--                 text-color="#fff"-->

          <el-menu-item index="/">
            <i class="icon_home"></i>
            <span>首页</span>
          </el-menu-item>
          <el-submenu index="user">

            <template slot="title">
              <i class="el-icon-bank-card" style="color: cornflowerblue"></i>
              <span>会员管理</span>
            </template>
            <el-menu-item index="/userList">会员列表</el-menu-item>
            <el-menu-item index="/addUser">会员添加</el-menu-item>
          </el-submenu>
          <el-submenu index="admin">
            <template slot="title">
              <i class="el-icon-user" style="color: cornflowerblue"></i>
              <span>管理员管理</span>
            </template>
            <el-menu-item index="/adminList">管理员列表</el-menu-item>
            <el-menu-item index="/addAdmin">管理员添加</el-menu-item>
          </el-submenu>

          <el-submenu index="category">
            <template slot="title">
              <i class="el-icon-guide" style="color: cornflowerblue"></i>
              <span>客房分类</span>
            </template>
            <el-menu-item index="/categoryList">分类列表</el-menu-item>
            <el-menu-item index="/addCategory">分类添加</el-menu-item>
          </el-submenu>

          <el-submenu index="hotel">
            <template slot="title">

              <i class="el-icon-date" style="color: cornflowerblue"></i>
<!--              <i class="icon_hotel_manage"></i>-->
              <span>客房管理</span>
            </template>
            <el-menu-item index="/hotelList">客房列表</el-menu-item>
            <el-menu-item index="/addHotel">客房添加</el-menu-item>
          </el-submenu>

          <el-submenu index="reserve">
            <template slot="title">
              <i class="el-icon-key" style="color: cornflowerblue"></i>
              <span>订房管理</span>
            </template>
            <el-menu-item index="/reserveList">订房列表</el-menu-item>
            <el-menu-item index="/addReserve">订房添加</el-menu-item>
          </el-submenu>

          <el-submenu index="retur">
            <template slot="title">
              <i class="el-icon-set-up" style="color: cornflowerblue"></i>
              <span>退房管理</span>
            </template>
            <el-menu-item index="/returList">退房列表</el-menu-item>
          </el-submenu>



          <el-submenu index="eatCategory">
            <template slot="title">
              <i class="el-icon-guide" style="color: cornflowerblue"></i>
              <span>餐饮分类</span>
            </template>
            <el-menu-item index="/eatCategoryList">分类列表</el-menu-item>
            <el-menu-item index="/addEatCategory">分类添加</el-menu-item>
          </el-submenu>

          <el-submenu index="eat">
            <template slot="title">
              <i class="el-icon-dish" style="color: cornflowerblue"></i>
              <span>餐饮管理</span>
            </template>
            <el-menu-item index="/eatList">餐品列表</el-menu-item>
            <el-menu-item index="/addEat">餐品添加</el-menu-item>
          </el-submenu>
          <el-submenu index="eatReserve">
            <template slot="title">
              <i class="el-icon-fork-spoon" style="color: cornflowerblue"></i>
              <span>餐饮预定</span>
            </template>
            <el-menu-item index="/eatReserveList">预定列表</el-menu-item>
            <el-menu-item index="/addEatReserve">预定添加</el-menu-item>
<!--            <el-menu-item index="/eatList">预定列表</el-menu-item>-->
          </el-submenu>
        </el-menu>
      </div>

      <!-- 主体数据 -->
      <!--      width:0; 可以限制容器的宽度，不被子元素无限制撑开-->
      <div style="flex: 1; width:0; background-color: white">
        <router-view/>
      </div>
    </div>

  </div>
</template>

<script>
import Cookies from 'js-cookie'

export default {
  name: "Layout.vue",
  data() {
    return {
      admin: Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {},

    }
  },
  methods: {
    logout() {
      this.$router.push('/login')
      // 清除浏览器用户数据
      Cookies.remove('admin')
      this.$router.push('/login')
    },

    // 在data里面定义mode为false
    /*
      * 模式切换
      * */
    // fnChangeMode(){
    //   this.mode = !this.mode;
    //   if (this.mode == true) {
    //     window.document.documentElement.setAttribute('data-theme', 'dark')
    //   } else {
    //     window.document.documentElement.setAttribute('data-theme', 'light')
    //   }
    // },


    Mode(){
      let toggle = document.querySelector("#switch");
      toggle.addEventListener("click",()=> {
        document.documentElement.classList.toggle("dark-mode");
      })
    }
  }
}
</script>

<style >

/*样式反转*/
.dark-mode{
  filter:invert(1) hue-rotate(180deg);
}

/* 首页 */
.icon_home {
  background: url("icons/home_icon.png") center no-repeat;
  background-size: 25px;
}

.icon_home:before {
  content: "home";
  font-size: 8px;
  visibility: hidden;
}

/* 会员 */
.el-icon_vip {
  background: url("ico_vip.png") center no-repeat;
  background-size: 20px;
}

.el-icon_vip:before {
  /* content中是中文的Unicode编码 */
  content: "\8d3a"; /* before 属性中的 content 文本是用来占位的,必须有 */
  font-size: 8px; /* 图标大小 */
  visibility: hidden; /*隐藏文字 */
}

/* 管理员 */
.ico_managePeople {
  background: url("icons/ico_managePeople.png") center no-repeat;
  background-size: 23px;
}

.ico_managePeople:before {
  content: "room";
  font-size: 6px;
  visibility: hidden;
}


/* */
.el-icon_hotel_room {
  background: url("icons/ico_hotel.png") center no-repeat;
  background-size: 23px;
}

.el-icon_hotel_room:before {
  content: "room";
  font-size: 6px;
  visibility: hidden;
}


/* */
.icon_hotel_manage {
  background: url("icons/ico_hotel_manage.png") center no-repeat;
  background-size: 23px;
}

.icon_hotel_manage:before {
  content: "home";
  font-size: 8px;
  visibility: hidden;
}

</style>