<template>
    <el-container style="height: 762px; border: 1px solid #eee">
    <el-aside width="230px" >
        <el-menu :default-openeds="['1', '3']" >
        <el-submenu index="1">    
                <template slot="title"  ><i class="el-icon-message"></i>系统简介</template>
                <router-link to="/themain">
                    <el-menu-item index="1-1" >首页</el-menu-item>
                </router-link> 
        </el-submenu>
        <el-submenu index="2">
            <template slot="title"><i class="el-icon-menu"></i>个人中心</template>
                 
                <router-link to="/info">
                    <el-menu-item index="2-1" >个人信息</el-menu-item>
                </router-link>
        </el-submenu>

        <el-submenu index="3">
            <template slot="title"><i class="el-icon-setting"></i>学生管理</template>
            <router-link to="/student">
            <el-menu-item index="3-1" >学生信息</el-menu-item>
            </router-link>
        </el-submenu>
        <el-submenu index="4">
            <template slot="title"><i class="el-icon-setting"></i>综合成绩管理</template>
            <router-link to="/score">
            <el-menu-item index="4-1" >综合成绩</el-menu-item>
            </router-link>
        </el-submenu>
        </el-menu>
    </el-aside>
    
    <el-container>
        <el-header style="text-align: right;">
        </el-header>
        
        <el-main style="padding: 4px;">
            <router-view/>
        </el-main>

        
    </el-container>
    </el-container>
</template>

<script>
    export default {

    };
</script>

<style>
    .el-container {
        background-color: #B3C0D1;
    }
    .el-aside{
        background-color:   #424967;
    }
    .el-menu > .el-submenu , .el-menu-item{
        background-color: #C0C4CC;
    }
    
</style>