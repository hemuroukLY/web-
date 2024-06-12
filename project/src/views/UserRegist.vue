<template>
    <div class="wrapper">
        <div class="regist-box">

        <h3 class="title">注册界面</h3>
        <div>{{ info }}</div>
        <el-form
            :model="loginForm"
            status-icon
            :rules="rules"
            ref="ruleForm"
            label-width="80px"
            class="demo-ruleForm"
        >
            <el-form-item label="账号" prop="username">
            <el-input
                type="text"
                v-model="loginForm.username"
                autocomplete="off"
            ></el-input>
            </el-form-item>

            <el-form-item label="密码" prop="password">
            <el-input
                type="password"
                v-model="loginForm.password"
                autocomplete="off"
            ></el-input>
            </el-form-item>

            <el-form-item label="确认密码" prop="fpassword">
            <el-input
                type="password"
                v-model="loginForm.fpassword"
                autocomplete="off"
            ></el-input>
            </el-form-item>

            <div style="padding-left: 83px;">
                <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')"
                    >注册</el-button
                >
                <el-button @click="resetForm('ruleForm')">重置</el-button>
                <el-button @click="gotoLogin()">去登录</el-button>
                </el-form-item>
            </div>
        </el-form>
        </div>
    </div>
</template>
<script>

// import {mapMutations} from 'vuex'
export default {
        data() {
        var validateUser = (rule, value, callback) => {
            if (value === '') {
            callback(new Error('请输入账号'));
            } else {
            callback();
            }
        };
        var validatePass = (rule, value, callback) => {
            if (value === '') {
            callback(new Error('请输入密码'));
            } else {
            callback();
            }
        };
        return {
            info:'',
            loginForm: {
            username: '',
            password: '',
            fpassword: '',
            },
            rules: {
            username: [
                { validator: validateUser, trigger: 'blur' }
            ],
            password: [
                { validator: validatePass, trigger: 'blur' }
            ],
            }
        };
        },
        methods: {
            gotoLogin(){
                this.$router.push('/login');
            },
            submitForm(formName) {
            if(this.loginForm.fpassword!==this.loginForm.password){
                alert("密码不一致");
            }
            else{
                this.$refs[formName].validate((valid) => {
            if (valid) {
                var User = {
                    "username": this.loginForm.username,
                    "password": this.loginForm.password,

                }
                
                this.$axios({
                url: "http://localhost:8080/register",
                method: "post",
                data:User,
                headers: {
                        'Content-Type': 'application/json'
                    }
                })

                .then((res) => {
                    if(res.data==1){
                        alert("注册成功");
                        this.loginForm={};
                    
                    }else if(res.data==0){
                        alert("注册失败");
                    }else if(res.data==2){
                        alert("用户名重复")
                    }
                    console.log(res.data);
                })
                .catch((err) => {
        
                    console.log(err);
                });
            } else {
                return false;
            }
            });
            }
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        }
        }
    }
</script>
<style scoped>
.wrapper{
    position: fixed;
    left: 0;
    top: 0;
    bottom: 0;
    right: 0;
    background-image: url(../assets/preview.jpg);
    background-size: 100%;
    }
    .regist-box {
    width: 600px;
    height: 400px;
    padding: 20px;
    margin: 0 auto;
    margin-top: 180px;
    border-radius: 10px;
    border: 1px solid #eee;
    background: #fff;
    position: relative;
    }
    .demo-ruleForm{
    margin-top: 60px;
    }
    .title{
    margin-bottom: 30px;
    text-align: center;
    color: #666;
}

</style>
