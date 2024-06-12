<template>
  <div>
    <TheHeader></TheHeader>
  <TecherBody></TecherBody>
</div>
  </template>
  
  <style>
  
  .title{
    color: white;
    font-size: large;
    margin-left: 50px;
  }
  .header{
    height: 60px;
    line-height: 60px;
    font-weight: 800;
    background-color:#424967;
  }
  *{
    margin: 0;
    padding: 0;
  }
  </style>
  
  <script>
  import TheHeader from './components/TheHeader.vue';
import TecherBody from './components/TecherBody.vue';
    export default {
      components:{
          TecherBody,
          TheHeader
      },
    };
  </script>