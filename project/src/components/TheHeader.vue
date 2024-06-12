<template>
      <div class="row header">
      <div class="col-4 title" >学生成绩管理系统</div>
      <div class="col-4 offset-3 text-right" style="font-weight: 100;color:white">
        {{ getName()}}
        <a @click="deleteLocal" href="" style="margin: 20px;color: #F4B0BB;">退出登入</a>
      </div>
    </div>
</template>

<script>
  import { jwtDecode } from 'jwt-decode';

  export default {
    
      data(){
        return{
          username:""
        }
      },  
      methods:{
        deleteLocal(){
            localStorage.setItem("jwt","");
        },
        getName(){
          return jwtDecode(localStorage.getItem("jwt")).username;
        } 
      },   
    };
</script>
