<template>
    <div>
        <div class="container">
            <el-form :inline="true" :model="mass" class="demo-form-inline"> 
                <el-form-item label="按">
                    <el-select v-model="formInline" placeholder="Search">
                    <el-option label="math" value="math"></el-option>
                    <el-option label="java" value="java"></el-option>
                    <el-option label="english" value="english"></el-option>
                    <el-option label="sports" value="sports"></el-option>
                    <el-option label="total" value="total"></el-option>
                    </el-select>
                </el-form-item>
                <el-button  @click="search()" type="success" style="margin-right: 20px;">排序</el-button>
                <el-form-item>
                    <download-excel class="export-excel-wrapper" :data="DetailsForm" :fields="json_fields" :head="ScoreTitle"
                        name="表格.xls" style="display:inline">
                        <el-button type="success" @click="getAlldata()">导出 </el-button>
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
                        <th>total</th>
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
                        <td class="stuTotal">{{item.total}}</td>
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
                        </td>
                    </tr>
                    </tbody>
                    <tfoot>
                    </tfoot>
                </table>
            </div>
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

            formInline:'',
            javaTotal:0,
            mathTotal:0,
            englishTotal:0,
            sportsTotal:0,
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
            mass:{},
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
        search(){
            this.GetData(1);
        },
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
            this.formInline=this.formInline||'math';
                this.page.pageNo=i||this.page.pageNo;
                this.$axios({
                    url:`http://localhost:8080/searchTop/${this.formInline}/${this.page.pageNo}`,
                    method:"get",
                    headers:{
                        'token':localStorage.getItem("jwt")
                    }
                }) ///////position
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
        getAlldata(){
            this.$axios({
                url:("http://localhost:8080/searchExcelTop"),
                method:"post",
                headers:{
                    'token':localStorage.getItem("jwt")
                }
            })
            .then(res=>{
                console.log(res.data)
                this.DetailsForm=res.data;
                
                for(var i=0;i<res.data.length;i++){
                    this.javaTotal += this.DetailsForm[i].java;
                    this.mathTotal+=this.DetailsForm[i].math;
                    this.englishTotal+=this.DetailsForm[i].english;
                    this.sportsTotal+=this.DetailsForm[i].sports;
                }
                this.DetailsForm[length-1].id='';
                this.DetailsForm[length-1].name='平均值';
                this.DetailsForm[i].java=res.data[i].java;
                    this.DetailsForm[i].math=res.data[i].math;
                    this.DetailsForm[i].english=res.data[i].english;
                    this.DetailsForm[i].sports=res.data[i].sports;
                    this.DetailsForm[i].total='';

            })
        },
    },

    mounted(){
        this.GetData(1)
        this.getAlldata()
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