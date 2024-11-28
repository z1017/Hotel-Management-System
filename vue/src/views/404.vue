<template>
  <div class="fzf" style="background-color: white; " >
    <div >
      <el-button  round  style="margin-top: 20px; color: #ee7919;font-size: 20px" @click="$router.push('/')">HOME</el-button>
    </div>
  </div>




</template>

<script>
export default {
  name: "404.vue"
}




</script>

<style>
.fzf {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;

  /*min-width: 1000px;*/
  background: url("image_404.png") center; /* 图片路径 */
  background-repeat: no-repeat;
  background-size: 800px 800px;
  background-attachment:fixed;
  /*-webkit-background-size: cover;*/
  /*-o-background-size: cover;*/
}


</style>