<template>
  <div class="login-container">
    <el-form ref="formRef" :model="form" class="login-form" :rules="rules">
      <div class="title-container">
        <h3 class="title">美声电器-管理员登录</h3>
      </div>
      <el-form-item prop="userName">
        <svg-icon icon="user" class="svg-container"></svg-icon>
        <el-input v-model="form.userName" placeholder="请输入用户名..."></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <svg-icon icon="password" class="svg-container"></svg-icon>
        <el-input v-model="form.password" placeholder="请输入密码..." :type="passwordType"></el-input>
        <svg-icon
            :icon="passwordType === 'password' ? 'eye' : 'eye-open'"
            @click="changeType"
        ></svg-icon>
      </el-form-item>
      <el-button type="primary" class="login-button" @click="handleLogin">登录</el-button>
    </el-form>
  </div>
</template>

<script setup>
import { ref } from 'vue'
// import {Edit} from '@element-plus/icons-vue'
// import axios from 'axios'
import  axios from '@/util/axios'
import {ElMessage} from 'element-plus'
import {getServerUrl} from "@/config/sys";
import router from '@/router'


const form=ref({
  userName: '',
  password:''
})

const rules=ref({
  userName: [
    {
      required: true,
      message: '请输入用户名！',
      trigger: 'blur',
    }],
  password:[
    {
      required: true,
      message: '请输入密码！',
      trigger: 'blur',
    }
  ]
})

const formRef=ref(null);

const handleLogin=()=>{
  formRef.value.validate(async (valid) => {
    if (valid) {
      try{
        let result=await axios.post("adminLogin",form.value);
        let data=result.data;
        if(data.mate.code==200){
          ElMessage.success("登录成功！");
          window.sessionStorage.setItem("token",data.data);
          window.sessionStorage.setItem("userInfo",JSON.stringify(form.value));
          router.replace("/")
        }else{
          ElMessage.error(data.mate.msg);
        }
      }catch(err){
        console.log("error:"+err)
        ElMessage.error('系统运行出错，请联系管理员');
      }
    } else {
      return false
    }
  })
}

const passwordType = ref('password')
const changeType = () => {
  if (passwordType.value === 'password') {
    passwordType.value = 'text'
  } else {
    passwordType.value = 'password'
  }
}

</script>

<style lang="scss" scoped>
$bg: #2d3a4b;
$dark_gray: #889aa4;
$light_gray: #eee;
$cursor: #fff;



.login-container {
  height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;

    :deep(.el-form-item)  {
      border: 1px solid rgba(255, 255, 255, 0.1);
      background: #fff;
      border-radius: 5px;
      color: #454545;
    }


    :deep(.el-input)  {
      display: inline-block;
      height: 47px;
      width: 85%;
      padding: 0px;
      .el-input__wrapper {
        display: inline-flex;
        flex-grow: 1;
        align-items: center;
        justify-content: center;
        padding: 0px;
        background-color: var(--el-input-bg-color, var(--el-fill-color-blank));
        background-image: none;
        border-radius: var(--el-input-border-radius, var(--el-border-radius-base));
        cursor: text;
        transition: var(--el-transition-box-shadow);
        transform: translate3d(0,0,0);
        box-shadow: 0 0 0 0px var(--el-input-border-color, var(--el-border-color)) inset;
      }
      input {
        background: transparent;
        color: #000000;
        height: 47px;
        width: 350px;
        caret-color: #0f0f0f;
        padding: 0px;
      }
    }
    .login-button {
      width: 100%;
      box-sizing: border-box;
    }
  }

  .tips {
    font-size: 16px;
    line-height: 28px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }

    :deep(.lang-select)  {
      position: absolute;
      top: 4px;
      right: 0;
      background-color: white;
      font-size: 22px;
      padding: 4px;
      border-radius: 4px;
      cursor: pointer;
    }
  }

  .show-pwd {
    // position: absolute;
    // right: 10px;
    // top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
}
</style>