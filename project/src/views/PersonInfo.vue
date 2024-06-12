<template>
    <div style="position: absolute;">
        <el-form style="position: relative;left: 300px;"  status-icon label-width="100px">
            <el-form-item label="账号名:" prop="pass">
                {{ user.username }}
            </el-form-item>
            <el-form-item label="密码:" prop="checkPass">
                {{ user.password }}
            </el-form-item>
            <el-form-item label="修改密码" prop="checkPass">
                <el-input type="text" v-model="newPassword" autocomplete="off"></el-input>
            </el-form-item>

            <el-form-item style="position: absolute;">
                <el-button style="position: relative;left: 20px;" type="primary" @click="submitForm()">提交</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
// import axios from 'axios';
import { jwtDecode } from 'jwt-decode';
export default{
    data(){
        return{
            user:{},
            newPassword:'',
            username:''
        }
    },
    methods:{
        submitForm(){
            var User=
            {"username":jwtDecode(localStorage.getItem("jwt")).username ,
             "password":this.newPassword ,}
                this.$axios({
                url: "http://localhost:8080/updatePassword",
                method: "post",
                data:User,
                headers: {
                        'Content-Type': 'application/json',
                        'token':localStorage.getItem("jwt")
                    }
                })

                .then((res) => {
                    if(res){
                        alert("修改成功")
                        this.GetData();
                    }else {
                        alert("修改失败");
                    }
                    console.log(res.data);
                    
                })
                .catch((err) => {
                    this.$router.push({path:'/login'});
                    console.log(err);
                });
        },
        GetData(){
            var User=
            {"username":jwtDecode(localStorage.getItem("jwt")).username ,
             "password":"" ,}
            this.$axios({
                url:"http://localhost:8080/searchTeacher",
                method:"post",
                data:User,
                headers:{
                    'Content-Type': 'application/json',
                     'token':localStorage.getItem("jwt")
                    }
            })
            .then(res=>{
                this.user=res.data;
                })
            .catch(error=>
            {console.log(error);
             this.$router.push('/login')
             alert("请重新登录");
            }  );
            }
            
    },
    mounted(){
        this.GetData()
    }
    

}
</script>

<style scoped>
.el-form-item .el-input{
    width: 200px;
}
</style>