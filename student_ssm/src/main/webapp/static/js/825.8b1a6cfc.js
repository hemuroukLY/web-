"use strict";(self["webpackChunkproject"]=self["webpackChunkproject"]||[]).push([[825],{825:function(r,o,t){t.r(o),t.d(o,{default:function(){return u}});var e=function(){var r=this,o=r._self._c;return o("div",{staticClass:"wrapper"},[o("div",{staticClass:"regist-box"},[o("h3",{staticClass:"title"},[r._v("注册界面")]),o("div",[r._v(r._s(r.info))]),o("el-form",{ref:"ruleForm",staticClass:"demo-ruleForm",attrs:{model:r.loginForm,"status-icon":"",rules:r.rules,"label-width":"80px"}},[o("el-form-item",{attrs:{label:"账号",prop:"username"}},[o("el-input",{attrs:{type:"text",autocomplete:"off"},model:{value:r.loginForm.username,callback:function(o){r.$set(r.loginForm,"username",o)},expression:"loginForm.username"}})],1),o("el-form-item",{attrs:{label:"密码",prop:"password"}},[o("el-input",{attrs:{type:"password",autocomplete:"off"},model:{value:r.loginForm.password,callback:function(o){r.$set(r.loginForm,"password",o)},expression:"loginForm.password"}})],1),o("el-form-item",{attrs:{label:"确认密码",prop:"fpassword"}},[o("el-input",{attrs:{type:"password",autocomplete:"off"},model:{value:r.loginForm.fpassword,callback:function(o){r.$set(r.loginForm,"fpassword",o)},expression:"loginForm.fpassword"}})],1),o("div",{staticStyle:{"padding-left":"83px"}},[o("el-form-item",[o("el-button",{attrs:{type:"primary"},on:{click:function(o){return r.submitForm("ruleForm")}}},[r._v("注册")]),o("el-button",{on:{click:function(o){return r.resetForm("ruleForm")}}},[r._v("重置")]),o("el-button",{on:{click:function(o){return r.gotoLogin()}}},[r._v("去登录")])],1)],1)],1)],1)])},s=[],a=(t(4114),{data(){var r=(r,o,t)=>{""===o?t(new Error("请输入账号")):t()},o=(r,o,t)=>{""===o?t(new Error("请输入密码")):t()};return{info:"",loginForm:{username:"",password:"",fpassword:""},rules:{username:[{validator:r,trigger:"blur"}],password:[{validator:o,trigger:"blur"}]}}},methods:{gotoLogin(){this.$router.push("/login")},submitForm(r){this.loginForm.fpassword!==this.loginForm.password?alert("密码不一致"):this.$refs[r].validate((r=>{if(!r)return!1;var o={username:this.loginForm.username,password:this.loginForm.password};this.$axios({url:"http://localhost:8080/register",method:"post",data:o,headers:{"Content-Type":"application/json"}}).then((r=>{1==r.data?(alert("注册成功"),this.loginForm={}):0==r.data?alert("注册失败"):2==r.data&&alert("用户名重复"),console.log(r.data)})).catch((r=>{console.log(r)}))}))},resetForm(r){this.$refs[r].resetFields()}}}),l=a,i=t(1656),n=(0,i.A)(l,e,s,!1,null,"5af9dbdf",null),u=n.exports}}]);
//# sourceMappingURL=825.8b1a6cfc.js.map