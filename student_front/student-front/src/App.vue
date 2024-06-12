<template>
  <el-row class="tac">
    <el-col :span="4">
      <h3 class="mb-2" style="margin-left: 15%;">学生成绩管理</h3>
      <el-menu
        default-active="2"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose">
        
        <el-menu-item index="1">
          <template #title>
            <el-icon><location /></el-icon>
            <router-link to="/components/Baseinfor.vue">基本信息管理</router-link>
            <router-view></router-view>
          </template>
        </el-menu-item>

        <el-menu-item index="2">
          <el-icon><icon-menu /></el-icon>
          <router-link to="/components/Gradeinfor.vue"><span>成绩信息管理</span></router-link>
          <router-view></router-view>
        </el-menu-item>

      </el-menu>
    </el-col>
  </el-row>
</template>

<script lang="ts" setup>
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'
const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
</script>
