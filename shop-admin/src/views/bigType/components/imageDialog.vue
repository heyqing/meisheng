<template>

  <el-dialog
    :model-value="imageDialogVisible"
    title="商品大类图片更换"
    width="30%"
    @close="handleClose"
    center
  >

    <el-form ref="formRef" :model="form" label-width="100px" style="text-align: center">

      <el-upload
        :headers="headers"
        class="avatar-uploader"
        :action="getServerUrl()+'admin/bigType/uploadImage'"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload"
      > <img v-if="imageUrl" :src="imageUrl" class="avatar" />
        <el-icon v-else class="avatar-uploader-icon"><plus /></el-icon>
      </el-upload>


    </el-form>

    <template #footer>

      <span class="dialog-footer">

        <el-button type="primary" @click="handleConfirm"
        >确认更换</el-button
        >
      </span>

    </template>

  </el-dialog>

</template>

<script setup>
import { defineEmits,ref ,defineProps,watch} from 'vue'
import axios from "@/util/axios";
import { ElMessage  } from "element-plus";
import {getServerUrl} from "@/config/sys";
import { Plus } from '@element-plus/icons-vue'

const emits=defineEmits(['update:modelValue','initBigTypeList'])

const props=defineProps({
  imageDialogValue:{
    type:Object,
    default:()=>{}
  }
})

const form=ref({
  sortId:-1,
  sortAvatar:''
})

const imageUrl=ref("")

const headers=ref({
  token:window.sessionStorage.getItem('token')
})

const actionData=ref({id:form.value.sortId})

const imageSrc=ref("")



watch(
  ()=>props.imageDialogValue,
  ()=>{
    form.value=props.imageDialogValue;
    actionData.value.id= form.value.sortId;
    imageSrc.value=form.value.sortAvatar;
  },
  {deep:true,immediate:true}
)

const beforeAvatarUpload = (file) => {
  const isJPG = file.type === 'image/jpeg'
  const isLt2M = file.size / 1024 / 1024 < 2

  if (!isJPG) {
    ElMessage.error('图片必须是jpg格式')
  }
  if (!isLt2M) {
    ElMessage.error('图片大小不能超过2M!')
  }
  return isJPG && isLt2M
}

const handleAvatarSuccess = (res) => {
  imageUrl.value = res.data;
  form.value.sortAvatar = res.data;
}


const handleConfirm = async() => {
  let result=await axios.post("admin/bigType/save",form.value);
  let data=result.data;
  if(data.mate.code===200){
    ElMessage.success("执行成功");
    imageUrl.value=""
   emits("initBigTypeList")
   handleClose();
  }else{
    ElMessage.error(data.mate.msg);
  }
}

const handleClose = () => {
  emits('update:modelValue',false)
}

</script>

<style >

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}

</style>