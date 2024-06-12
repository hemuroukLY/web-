<template>
  <div class="wrapper">
      <div style="margin-top: 300px">
          <p style="font-size: 50px;color: palegoldenrod;">欢迎登入学生成绩管理系统</p>
          <router-link to="/login">
              <p style="font-size: 30px; color: #CD7862;">去登录</p>
          </router-link>
          <router-link to="/regist">
              <p style="font-size: 30px; color: #CD7862;">去注册</p>
          </router-link>
      </div>
  </div>
</template>

<script>
  export default {

  }
</script>
<style scoped>
.wrapper{
  
  text-align: center;
  position: fixed;
  left: 0;
  top: 0;
  bottom: 0;
  right: 0;
  background-image: url(../assets/preview.jpg);
  background-size: 100%;
  }

</style>
