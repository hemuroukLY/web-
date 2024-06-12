<template>
    <div class="wrapperBox" id="stu">
            <div class="container">
                <table id="containerTable">
                    <thead class="tableHeaher">
                    <tr>
                        <th><input type="checkbox" class="checkAll" @click="checkAll()"></th>
                        <th>序号</th>
                        <th>学号</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>生辰</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="tdata">
                    <tr class="trHover" v-for="(item,index) in students" :key="index+1" value-format="yyyy-MM-dd">
                        <td><input type="checkbox" class="checkone" :value="item.id"></td>
                        <td class="stuSequence">{{ index+1 }}</td>
                        <td class="stuId">{{item.id}}</td>
                        <td class="stuName">{{item.name}}</td>
                        <td class="stuSex">{{item.gender}}</td>
                        <td class="stuYear">{{item.birthday }}</td>
                        <td>
                            <!-- <a class="viewInf " @click="CheckPerson(item.id)">查看</a> -->
                            <a class="updateInf " @click="UpdatePerson(item.id)">修改</a>
                        </td>
                    </tr>
                    </tbody>
                    <tfoot>
                    </tfoot>
                </table>
            </div>
            <div class="footer">
                    <el-button type="primary" style="width: 93px;
height: 38px;" @click="dialogFormVisible = true">新增</el-button>
                    <el-button type="primary" style="width: 93px;
height: 38px;" @click="DeletePerson()">删除</el-button>

            <div class="rq">
                <ul class="" style="text-align: center;">
                    <li @click="prePage()">
                        <a>
                            <span>&laquo;</span>
                        </a>
                    </li>
                    <li :class="page.pageNo==index?'active':''" v-for="index in page.pageNumber" :key="index" @click="curPage(index)">
                        <a>{{ index }}</a>
                    </li>
                    <li @click="nextPage()">
                        <a>
                            <span>&raquo;</span>
                        </a>
                    </li>
                </ul>
            </div>
            </div>




            <!-- {id:'1000',name:'王',sex:1,birthday:'2001-01-01'}, -->
            <el-dialog title="新增学生信息" :visible.sync="dialogFormVisible">
                <el-form ref="form" :model="form" label-width="80px">
                
                <el-form-item label="学生姓名">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="学生性别">
                    <el-select v-model="form.gender" placeholder="请选择性别">
                    <el-option label="男" value="男"></el-option>
                    <el-option label="女" value="女"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="出生日期">
                    <el-col :span="11">
                    <el-date-picker type="date" placeholder="选择日期" v-model="form.birthday"  value-format="yyyy-MM-dd" style="width: 100%;"></el-date-picker>
                    </el-col>
                    <el-col class="line" :span="2">-</el-col>
                </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="FormSubmit()">确 定</el-button>
                </div>
            </el-dialog>

            <el-dialog title="修改学生信息" :visible.sync="UpdataKey">
                <el-form ref="UpdataForm" :model="UpdataForm" label-width="80px">
                <el-form-item label="修改姓名">
                    <el-input v-model="UpdataForm.name"></el-input>
                </el-form-item>
                <el-form-item label="学生性别">
                    <el-select v-model="UpdataForm.gender" placeholder="请选择性别">
                    <el-option label="男" value="男"></el-option>
                    <el-option label="女" value="女"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="出生日期">
                    <el-col :span="11">
                    <el-date-picker type="date" placeholder="选择日期" value-format="yyyy-MM-dd" v-model="UpdataForm.birthday" style="width: 100%;"></el-date-picker>
                    </el-col>
                    <el-col class="line" :span="2">-</el-col>
                </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="SendUpdataInfo()">确认修改</el-button>
                </div>
            </el-dialog>        
        </div>
        
</template>
<script>
// import axios from 'axios';

import moment from 'moment';


export default{


    data(){
        return{
            students:[ ],
            dialogFormVisible: false,
            UpdataKey:false,
            page:{
                pageTotal:"",
                pageNo:"",
                pageSize:"",
                pageNumber:""
            },
            form: {
                id: "",
                name: "",
                gender: "",
                birthday: ""
            },
            UpdataForm:{
                id: "",
                name: "",
                gender: "",
                birthday: ""
            },
            formLabelWidth: '120px',
        }
        },
    methods:{
            checkAll(){
                const qx=document.querySelector(".checkAll")
                const dx=document.querySelectorAll(".checkone");
                for(let i=0;i<dx.length;i++){
                    dx[i].checked=qx.checked;
                }
            },
            FormSubmit(){
                this.dialogFormVisible = false;
                var Student = {
  
                    "name": this.form.name,
                    "gender": this.form.gender,
                    "birthday": this.form.birthday
                }
                
                this.$axios({
                url: "http://localhost:8080/addStudent",
                method: "post",
                data:Student,

                headers: {
                        'Content-Type': 'application/json',
                        'token':localStorage.getItem("jwt")
                    }
                })

                .then((res) => {
                    this.GetData(this.page.pageNo)
                    console.log(res);
                    
                })
                .catch(error=>
                            {console.log(error);
                            this.$router.push('/login')
                            alert("请重新登录");
                        });
            },
            // CheckPerson(id){
            // }
            UpdatePerson(id){
                this.UpdataKey=true;
                // this.UpdataForm=this.student.filter(item=>item.id===id);
                let i;
                for(i=0;;i++){
                    if(this.students[i].id===id){
                        this.UpdataForm.id = this.students[i].id;
                        this.UpdataForm.name= this.students[i].name;
                        this.UpdataForm.gender = this.students[i].gender;
                        this.UpdataForm.birthday = this.students[i].birthday;
                        break;
                    }
                }
            },
            SendUpdataInfo(){
                this.UpdataKey = false;
                // console.log(this.UpdataForm.gender);
                var Student = {
                    "id": this.UpdataForm.id,
                    "name": this.UpdataForm.name,
                    "gender": this.UpdataForm.gender,
                    "birthday": this.UpdataForm.birthday
                }
                //提交给数据库信息
                // alert(this.form.birthday)
                this.$axios({
                url: "http://localhost:8080/updateStudent",
                method: "post",
                data:Student,
                headers: {
                        'Content-Type':'application/json',
                        'token':localStorage.getItem("jwt")
                    }
                })
                .then((res) => {
                    this.GetData(this.page.pageNo)
                    console.log(res);
                })
                .catch(error=>
                            {console.log(error);
                            this.$router.push('/login')
                            alert("请重新登录");
                        });
            },
            DeletePerson(){
                const dx=document.querySelectorAll(".checkone");
                for(let i=0;i<dx.length;i++){
                    if(dx[i].checked===true){
                       
                         this.$axios({
                        url: "http://localhost:8080/deleteStudent/"+dx[i].value,
                        method: "get",
                        data:dx[i].value,
                        headers: {
                                'Content-Type': 'application/json',
                                'token':localStorage.getItem("jwt")
                            }
                        })
                        .then((res) => {
                            console.log(res);
                            this.GetData(this.page.pageNo);
                            document.querySelector(".checkAll").checked=false;
                            this.checkAll()
                        })
                        .catch(error=>
                            {console.log(error);
                            this.$router.push('/login')
                            alert("请重新登录");
                        });
                    }
                }
            },
            GetData(i){

                this.page.pageNo=i||this.page.pageNo;
                // this.$axios.get(`http://localhost:8080/searchStudent/${this.page.pageNo}`)
                this.$axios({
                    url:`http://localhost:8080/searchStudent/${this.page.pageNo}`,
                    method:"get",
                    headers:{
                        'token':localStorage.getItem("jwt")
                    }
                })
                
            .then(res=>{
                
                for(var i=0;i<res.data.list.length;i++){
                    this.students[i]=res.data.list[i]
                let time = moment(res.data.list[i].birthday).format("YYYY-MM-DD")
                this.students[i].birthday=time
                }
                this.students=res.data.list

                this.page.pageTotal=res.data.total
                this.page.pageNumber=res.data.pages
                
                
            })
            .catch(error=>
            {console.log(error);
             this.$router.push('/login')
             alert("请重新登录");
            }   
            )
            },
            prePage(){
                if(this.page.pageNo>1)  this.GetData(--this.page.pageNo)
            },
            nextPage(){
                if(this.page.pageNo<this.page.pageTotal/10)  this.GetData(++this.page.pageNo)
            },
            curPage(index){
                this.GetData(index);
            },
            
        
        },
       
        mounted(){
         
         this.GetData(1)
        }
    }
</script>
<style scoped>
.action{
    background-color: #409EFF;
}
.wrapperBox{
margin: 0 auto;
}
.footer{
width: 1200px;
height: 111px;
margin: 0 auto;
text-align: center;
}

.headerTitle{
width: 1200px;
height: 56px;
}
.container{
margin: 0 auto;
font-size: 12px;
text-align: center;
}
.viewInf ,.updateInf{
color: #409EFF;
cursor: pointer;
}
.updateInf{
margin-left: 9px;
}
.container table{
border-collapse: collapse;
width: 100%;
}
.container td{
height: 40px;
}
.tableHeaher{
height: 55px;
background-color: #dadee1;
}
tr.trHover:hover{
background-color: rgb(168, 162, 163);
}
td{
height: 40px;
}


.rq{
    width: 100%;
    height: 50px;
    /* background-color: aquamarine; */
    text-align: center;
    padding-top: 20px;
}
.rq ul li,.rq ul li  {
    display: inline-block;
    border-radius: 20%;
    line-height: 25px;
    height: 25px;
    padding: 0px 10px;
    margin: 0px 2px;
    border: 1px solid #916565;
    font-size: 14px;
    color: rgb(0, 0, 0);
    text-decoration: none;
}
.rq span{
    
    color: rgb(1, 0, 0);
}
</style>
