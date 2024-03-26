<template>
  <el-dialog
      :model-value="dialogVisible"
      :title="dialogTitle"
      width="60%"
      @close="handleClose"
      :before-close="handleClose"
  >
    <el-form ref="formRef" :model="form" label-width="100px" :rules="rules">
      <el-form-item label="场景管名称" prop="sceneName">
        <el-input v-model="form.sceneName"></el-input>
      </el-form-item>

      <el-form-item label="场景管图片">
        <el-upload
            :headers="headers"
            class="avatar-uploader"
            :action="getServerUrl()+'admin/scene/uploadImage'"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
        ><img v-if="imageUrl" :src="imageUrl" class="avatar"/>
          <el-icon v-else class="avatar-uploader-icon">
            <plus/>
          </el-icon>
        </el-upload>
      </el-form-item>

      <el-form-item label="场景管描述" prop="sceneDesc">
        <el-input
            v-model="form.sceneDesc"
            :rows="4"
            type="textarea"
        />
      </el-form-item>

      <el-form-item label="场景管类型">
        <el-select v-model="form.sceneType" class="m-2" placeholder="请选择...">
          <el-option
              v-for="item in sceneTypeList"
              :key="item"
              :label="item"
              :value="item"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="场景管详情">
        <el-upload
            :headers="headers"
            class="avatar-uploader"
            v-model:file-list="detailList"
            list-type="picture-card"
            :action="getServerUrl()+'admin/scene/uploadDetail'"
            :on-preview="handlePictureCardDetailPreview"
            :on-remove="handleDetailRemove"
            :on-success="handleDetailSuccess"
        >
          <el-icon class="avatar-uploader-icon">
            <plus/>
          </el-icon>
        </el-upload>
        <el-dialog v-model="dialogDetailVisible">
          <img w-full :src="dialogDetailImageUrl" alt="Preview Image"/>
        </el-dialog>
      </el-form-item>

      <el-form-item label="场景管商品" class="goods">

        <el-input placeholder="请输入商品完整名称..." clearable v-model="queryForm.query" style="width: 80%"></el-input>

        <el-button type="primary" @click="search" style="width: 15%">添加</el-button>

        <div class="checkbox"
             v-for="item in form.sceneGoods"
             :key="item"
             style="margin-top: 10px"
        >
          <el-checkbox v-model="choiceGoods" :label="item.goodsName" size="large" border style="margin-right: 5px"/>
        </div>


      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="handleClose">取消</el-button>
        <el-button type="primary" @click="handleConfirm"
        >确认</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import {defineEmits, ref, defineProps, watch} from 'vue'
import axios from "@/util/axios";
import {ElMessage} from "element-plus";
import {getServerUrl} from "@/config/sys";
import {Plus} from "@element-plus/icons-vue";

const queryForm = ref({
  query: ''
})
const choiceGoods = ref(true)

const props = defineProps({
  dialogTitle: {
    type: String,
    default: '',
    required: true
  },
  dialogVisible: {
    type: Boolean,
    default: false,
    required: true
  },
  dialogValue: {
    type: Object,
    default: () => {
    }
  }
})

const headers = ref({
  token: window.sessionStorage.getItem('token')
})


const sceneTypeList = ref([
  'swiper',
  'floor'
])

const form = ref({
  sceneId: -1,
  sceneAvatar: '',
  sceneName: '',
  sceneDesc: '',
  sceneType: '',
  sceneGoods: [],
  sceneDetail: []
})

watch(
    () => props.dialogVisible,
    () => {
      form.value = props.dialogValue;

      if (form.value.sceneId === undefined) {
        console.log("是添加")
      } else {
        console.log("是修改")
        initList();
      }
    },
    {deep: true, immediate: true}
)


const search = async () => {
  if(form.value.sceneGoods === undefined){
    form.value.sceneGoods = []
  }
  try {
    const res = await axios.post("admin/product/goods", queryForm.value)
    if (res.data.mate.code === 200) {

      if (form.value.sceneGoods.find(item => item.goodsId === res.data.data.goodsId)) {
        ElMessage.warning("商品已存在")
      } else {
        ElMessage.success("添加成功")
        form.value.sceneGoods.push(res.data.data)
      }

    } else {
      ElMessage.error(res.data.mate.msg)
    }

  } catch (e) {
    console.log(e)
    console.log(e.error)
    ElMessage.error("系统错误请联系管理员")
  }

}
const initList = () => {
  if (form.value.sceneDetail.length > 0) {
    let listPre = []
    for (let i = 0; i < form.value.sceneDetail.length; i++) {
      listPre.push({url: form.value.sceneDetail[i]})
    }
    detailList.value = listPre
  }
}
const emptyList = () => {
  imageUrl.value = '';
  detailList.value = [];
}
const emits = defineEmits(['update:modelValue', 'initSceneList'])

const formRef = ref(null)

const handleClose = () => {
  emptyList();
  emits('update:modelValue', false)
  formRef.value.resetFields();
}


const rules = ref({
  sceneName: [
    {
      required: true,
      message: '请输入场景管名称！',
    }
  ],
  sceneDesc: [
    {
      required: true,
      message: '请输入场景管描述！',
    },
  ]
})

const handleConfirm = () => {
  formRef.value.validate(async (valid) => {
    if (valid) {
      try {
        let result = await axios.post("admin/scene/save", form.value);
        let data = result.data;
        if (data.mate.code == 200) {
          ElMessage.success("执行成功");
          emits("initSceneList")
          handleClose();
        } else {
          ElMessage.error(data.mate.msg);
        }
      } catch (err) {
        console.log("error:" + err)
        ElMessage.error('系统运行出错，请联系管理员');
      }
    } else {
      return false
    }
  })
}

const imageUrl = ref("")

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
  form.value.sceneAvatar = res.data;
}

const detailList = ref([])
const dialogDetailImageUrl = ref('')
const dialogDetailVisible = ref(false)
const list = []
const handleDetailSuccess = (res) => {
  if(form.value.sceneDetail === undefined){
    form.value.sceneDetail = []
    form.value.sceneDetail.push(res.data)
  }else {
    form.value.sceneDetail.push(res.data)
  }
}
const handleDetailRemove = (uploadFile, uploadFiles) => {
  console.log(uploadFile, uploadFiles);
  form.value.sceneDetail = form.value.sceneDetail.filter(item => item !== uploadFile.url)
}
const handlePictureCardDetailPreview = (uploadFile) => {
  dialogDetailImageUrl.value = uploadFile.url;
  dialogDetailVisible.value = true;
}
</script>

<style scoped>
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