<template>
    <div>
        <div class="container">
            <el-form :inline="true" :model="mass" class="demo-form-inline">
                <el-form-item label="学号">
                    <el-input v-model="mass.id" placeholder="学号" style="width: 100px;"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSubmit()">学号查询</el-button>
                </el-form-item>
                <el-form-item label="姓名">
                    <el-input v-model="mass.name" placeholder="姓名" style="width: 100px;"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSubmit()" >姓名查询</el-button>
                    <el-button type="primary" @click="GetData(1)" style="margin-left: 50px;">显示全部信息</el-button>
                </el-form-item>
                <el-form-item>
                    <!-- <a @click="getAlldata()"><i style="cursor:pointer" class="el-icon-refresh">刷新后导出</i></a> -->
                    <download-excel class="export-excel-wrapper" :data="DetailsForm" :fields="json_fields" :head="ScoreTitle"
                        name="表格.xls" style="display:inline">
                        <el-button type="success" @click="getAlldata()">导出  </el-button>
                    </download-excel>

                </el-form-item>
            </el-form>
                <table id="containerTable">
                    <thead class="tableHeaher">
                    <tr>
                        <th>序号</th>
                        <th>学号</th>
                        <th>姓名</th>
                        <th>java</th>
                        <th>math</th>
                        <th>english</th>
                        <th>sports</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="tdata">
                    <tr class="trHover" v-for="(item,index) in students" :key="index+1">
                        <td class="stuSequence">{{ index+1 }}</td>
                        <td class="stuId">{{item.id}}</td>
                        <td class="stuName">{{item.name}}</td>
                        <td class="stuJava">{{item.java}}</td>
                        <td class="stuMath">{{item.math}}</td>
                        <td class="stuEnglish">{{item.english}}</td>
                        <td class="stuSports">{{item.sports}}</td>
                        <td>
                            <!-- <a class="viewInf " @click="CheckPerson(item.id)">查看</a> -->
                            <el-popover
                            placement="left"
                            width="600"
                            
                            trigger="click">
                            <div id="charts" style="height: 600px; width: 600px;">
                            </div>
                            <a slot="reference" class="updateInf " @click="showChart(index)">查看详细 </a>
                            </el-popover> 
                            <!-- <a class="checkinfo" @click="checkinfo(item.id)">查看详细</a> -->
                                <a class="updateInf " @click="opeScore(item.id,item.name)"> 录入成绩</a>
                        </td>
                    </tr>
                    </tbody>
                    <tfoot>
                    </tfoot>
                </table>
            </div>
            <el-dialog title="新增学生成绩信息" :visible.sync="dialogFormVisible">
                <el-form ref="form" :model="form" label-width="80px">
                <el-form-item label="java:">
                    <el-input v-model="form.java"></el-input>
                </el-form-item>
                <el-form-item label="math:">
                    <el-input v-model="form.math"></el-input>
                </el-form-item>
                <el-form-item label="english:">
                    <el-input v-model="form.english"></el-input>
                </el-form-item>
                <el-form-item label="sports:">
                    <el-input v-model="form.sports"></el-input>
                </el-form-item>

                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="FormSubmit()">确 定</el-button>
                </div>
            </el-dialog>
            
            <div class="footer">
                    <!-- <el-button type="primary" style="width: 93px;
height: 38px;" @click="checkScore()">查找</el-button> -->

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
    </div>
</template>
<script>
import * as echarts from 'echarts';

export default{
    data(){
        return {
            javaTotal:0,
            mathTotal:0,
            englishTotal:0,
            sportsTotal:0,
            eachTotal:0,
            ScoreTitle:"学生成绩表",
            json_fields:{
                "学号":'id',
                "姓名":'name',
                "java":'java',
                "math":'math',
                "english":'english',
                "sports":'sports',
                "total":'total',

            },
            DetailsForm:[
                
            ],
            mass:{
                id:'',
                name:'',
            },
            personId:'',
            personName:'',
            page:{
                pageTotal:10,
                pageNo:1,
                pageSize:10,
                pageNumber:5
            },
            
            dialogFormVisible:false,
            dialogVisible: false,
            form:{
                java:'',
                math:'',
                english:'',
                sports:''
            },
            students:[
                
            ],

        }

    },
    methods:{
        showChart(index){
            console.log(index);
            var myChart = echarts.init(document.getElementById('charts'));
// 绘制图表
                myChart.setOption({
                title: {
                    text: `${this.students[index].name}的成绩分布图`
                },
                tooltip: {},
                xAxis: {
                    data: ['Java', 'Math', 'English', 'Sports']
                },
                yAxis: {},
                series: [
                    {
                    name: '成绩',
                    type: 'bar',
                    data: [this.students[index].java,this.students[index].math,this.students[index].english,this.students[index].sports]
                    }
                ]
                });
        },
        onSubmit() {
            if(this.mass.id==='' ){
                if(this.mass.name===''){
                    alert("您还没有输入查找信息！")
                }
                else{//按名字查找
                    this.$axios({
                    url: `http://localhost:8080/searchGradeByName/${this.mass.name}`,
                    method: "get",
                    headers: {
                            'Content-Type': 'application/json',
                            'token':localStorage.getItem("jwt")
                    }
                }).then((res) => {
                    console.log(res.data)
                        this.students=[];
                        if(res.data){
                        this.students=res.data;}
                        console.log(res);
                })
                .catch(error=>
                            {console.log(error);
                            this.$router.push('/login')
                            alert("请重新登录");
                        });
                }
            }
            else{
                if(this.mass.name==='') {//按id查找
                    this.$axios({
                    url: `http://localhost:8080/searchGradeById/${this.mass.id}`,
                    method: "get",
                    headers: {
                            'Content-Type': 'application/json',
                            'token':localStorage.getItem("jwt")
                    }
                    }).then((res) => {         
                        console.log(res.data)
                        this.students=[];
                        if(res.data){
                        this.students[0]=res.data;}
                        console.log(res);
                        
                    })
                    .catch((err) => {
                        console.log(err);
                    });
                }
            }
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
        GetData(i){
                this.page.pageNo=i||this.page.pageNo;
                // this.$axios.get(`http://localhost:8080/searchGradeAll/${this.page.pageNo}`)
                this.$axios({
                    url:`http://localhost:8080/searchGradeAll/${this.page.pageNo}`,
                    method:"get",
                    headers:{
                        'token':localStorage.getItem("jwt")
                    }
                })   ///////position
            .then(res=>{
                this.students=res.data.list
                this.page.pageTotal=res.data.total
                this.page.pageNumber=res.data.pages 
            })
            .catch(error=>
                            {console.log(error);
                            this.$router.push('/login')
                            alert("请重新登录");
                        });
            },
        handleClose(){
            alert("close")
        },
        checkinfo(id){
            console.log(id)
        },
        opeScore(id,name){
                this.personId=id;
                this.personName=name;
                this.dialogFormVisible = true;
                // this.subScore(id);
            },
        FormSubmit(){//添加成绩
            this.dialogFormVisible = false;
            
            var Student = {
                    "id":this.personId,
                    "name":this.personName,
                    "java": this.form.java?this.form.java:-1,
                    "math": this.form.math?this.form.math:-1,
                    "english": this.form.english?this.form.english:-1,
                    "sports": this.form.sports?this.form.sports:-1,
                }
                if(Student.java===-1&&Student.math===-1&&Student.english===-1&&Student.sports===-1) alert("请输入一科或者多科成绩");
                else{
                this.$axios({
                url: "http://localhost:8080/updateGrade",
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
                }

        },
        getAlldata(){
            this.$axios({
                url:("http://localhost:8080/getExcelGrade"),
                method:"post",
                headers:{
                    'token':localStorage.getItem("jwt")
                }
            })
            .then(res=>{
                this.DetailsForm=res.data;
                this.javaTotal=0
                this.mathTotal=0
                this.englishTotal=0
                this.sportsTotal=0
                this.eachTotal=0
                for(var i=0;i<res.data.length;i++){
                    this.javaTotal += this.DetailsForm[i].java;
                    this.mathTotal+=this.DetailsForm[i].math;
                    this.englishTotal+=this.DetailsForm[i].english;
                    this.sportsTotal+=this.DetailsForm[i].sports;
                    this.eachTotal+=this.DetailsForm[i].total;
                }
               
                    this.DetailsForm.push({id:'',name:'平均值',
                    java:this.javaTotal/this.DetailsForm.length,
                    math:this.mathTotal/this.DetailsForm.length,
                    english:this.englishTotal/this.DetailsForm.length,
                    sports:this.sportsTotal/this.DetailsForm.length,
                    total:this.eachTotal/this.DetailsForm.length})
               
              
            })
            .catch(error=>
                            {console.log(error);
                            this.$router.push('/login')
                            alert("请重新登录")
              });
        },
    },

     mounted(){
        this.GetData(1)
        
    }
    
    
}
</script>

<style>

    .footer{
        width: 1200px;
        height: 111px;
        margin: 0 auto;
        text-align: center;
    }
    .active{
        background-color: pink;
    }
    .viewInf ,.updateInf{
        color: #409EFF;
        cursor: pointer;
    }
    .updateInf{
        margin-left: 9px;
    }
    .center{
        text-align: center;
    }
    .container{
        margin: 0 auto;
        font-size: 12px;
        text-align: center;
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