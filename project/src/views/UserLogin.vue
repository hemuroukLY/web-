<template>
    <div class="wrapper" >
        <div class="login-box" >
        <h3 class="title">登录界面</h3>
        <div>{{ info }}</div>
        <el-form
            :model="loginForm"
            status-icon
            :rules="rules"
            ref="ruleForm"
            label-width="60px"
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
            <div style="padding-left: 83px;">
                <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')"
                    >登入</el-button
                >
                <el-button @click="resetForm('ruleForm')">重置</el-button>
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
  //      ...mapMutations('loginModule',['setUser']),
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
            if (valid) {
               
                var User = {
                    "username": this.loginForm.username,
                    "password": this.loginForm.password,
                }
                this.$axios({
                url: "http://localhost:8080/login",
                method: "post",
                data:User,
                headers: {
                        'Content-Type': 'application/json'
                    }
                })

                .then((res) => {
                    if(res.data=="登录失败"){
                        alert("登录失败");
                    }else{
                    localStorage.setItem("jwt",res.data);
                    this.$router.push('/themain');
                    }
                    
                    
                })
                .catch((err) => {
                    this.$router.push({path:'/login'});
                    console.log(err);
                });
            } else {
                return false;
            }
            });
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
    .login-box {
    width: 480px;
    height: 312px;
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
