<template>
  <el-dialog
      :model-value="dialogVisible"
      :title="dialogTitle"
      width="60%"
      @close="handleClose"
      :before-close="handleClose"
  >
    <el-form ref="formRef" :model="form" label-width="100px" :rules="rules"
             v-loading="loading"
             element-loading-text="Loading..."
             :element-loading-spinner="svg"
             element-loading-svg-view-box="-10, -10, 50, 50"
             element-loading-background="rgba(122, 122, 122, 0.8)"
    >

      <el-form-item label="商品名称" prop="goodsName">
        <el-input v-model="form.goodsName" style="width: 400px"></el-input>
      </el-form-item>

      <el-form-item label="商品价格" prop="goodsPrice">
        <el-input v-model="form.goodsPrice" style="width: 100px"></el-input>
      </el-form-item>
      <el-form-item label="所属大类">
        <el-select v-model="form.sortName" class="m-2" placeholder="请选择...">
          <el-option
              v-for="item in bigTypeSelectOptions"
              :key="item.sortId"
              :label="item.sortName"
              :value="item.sortId"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="所属小类">
        <el-select v-model="form.categoryName" class="m-2" placeholder="请选择...">
          <el-option
              v-for="item in smallTypeSelectOptions"
              :key="item.categoryId"
              :label="item.categoryName"
              :value="item.categoryId"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="商品图片">
        <el-upload
            :headers="headers"
            class="avatar-uploader"
            :action="getServerUrl()+'admin/product/uploadImage'"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
        ><img v-if="imageUrl" :src="imageUrl" class="avatar"/>
          <el-icon v-else class="avatar-uploader-icon">
            <plus/>
          </el-icon>
        </el-upload>
      </el-form-item>

      <el-form-item label="商品描述" prop="goodsDesc">
        <el-input
            v-model="form.goodsDesc"
            :rows="4"
            type="textarea"
        />
      </el-form-item>

      <el-form-item label="商品视频">
        <el-upload
            :headers="headers"
            class="video-uploader"
            :action="getServerUrl()+'admin/product/uploadVideo'"
            :show-file-list="false"
            :on-success="handleVideoSuccess"
            :before-upload="beforeVideoUpload"
        >
          <video controls v-if="videoUrl" class="video">
            <source :src="videoUrl"/>
          </video>
          <el-icon v-else class="video-uploader-icon">
            <plus/>
          </el-icon>
        </el-upload>
      </el-form-item>
      <el-form-item label="商品类别">
        <el-select v-model="form.goodsType" class="m-2" placeholder="请选择...">
          <el-option
              v-for="item in goodsTypeList"
              :key="item"
              :label="item"
              :value="item"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="商品标签" prop="goodsTitle">
        <el-input v-model="form.goodsTitle" style="width: 100px"></el-input>
      </el-form-item>
      <el-form-item label="幻灯图片">
        <el-upload
            :headers="headers"
            class="avatar-uploader"
            v-model:file-list="swiperList"
            list-type="picture-card"
            :action="getServerUrl()+'admin/product/uploadSwiper'"
            :on-preview="handlePictureCardPreview"
            :on-remove="handleRemove"
            :on-success="handleSwiperSuccess"
        >
          <el-icon class="avatar-uploader-icon">
            <plus/>
          </el-icon>
        </el-upload>
        <el-dialog v-model="dialogVisible">
          <img w-full :src="dialogImageUrl" alt="Preview Image"/>
        </el-dialog>
      </el-form-item>

      <el-form-item label="商品详情">
        <el-upload
            :headers="headers"
            class="avatar-uploader"
            v-model:file-list="detailList"
            list-type="picture-card"
            :action="getServerUrl()+'admin/product/uploadDetail'"
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

      <el-form-item label="商品参数">
        <el-upload
            :headers="headers"
            class="avatar-uploader"
            v-model:file-list="paramList"
            list-type="picture-card"
            :action="getServerUrl()+'admin/product/uploadParam'"
            :on-preview="handlePictureCardParamPreview"
            :on-remove="handleParamRemove"
            :on-success="handleParamSuccess"
        >
          <el-icon class="avatar-uploader-icon">
            <plus/>
          </el-icon>
        </el-upload>
        <el-dialog v-model="dialogVisible">
          <img w-full :src="dialogParamImageUrl" alt="Preview Image"/>
        </el-dialog>
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


const loading = ref(false)
const svg = `
        <path class="path" d="
          M 30 15
          L 28 17
          M 25.61 25.61
          A 15 15, 0, 0, 1, 15 30
          A 15 15, 0, 1, 1, 27.99 7.5
          L 15 15
        " style="stroke-width: 4px; fill: rgba(0, 0, 0, 0)"/>
      `

const props = defineProps({
  dialogTitle: {
    type: String,
    default: '',
    required: true
  },
  dialogVisible: {
    type:Boolean,
    default:false,
    required:true
  },
  dialogValue: {
    type: Object,
    default: () => {
    }
  }
})

const form = ref({
  goodsId: -1,
  goodsName: '',
  goodsPrice: 0,
  goodsAvatar: '',
  goodsDesc: '',
  goodsType: '',
  goodsTitle: '',
  goodsVideo: '',
  goodsPhoto: [],
  goodsDetail: [],
  goodsParam: [],
  sortName: '',
  categoryName: '',
})

const goodsTypeList = ref([
  'swiper',
  'floor'
])
const emits = defineEmits(['update:modelValue', 'initProductList'])

const formRef = ref(null)


const handleClose = () => {

  emptyList();
  emits('update:modelValue', false)
  formRef.value.resetFields();
}


const rules = ref({
  goodsName: [
    {
      required: true,
      message: '请输入商品小类名称！',
    }
  ],
  goodsPrice: [
    {required: true, message: '请输入商品价格!'},
    {type: 'number', message: '商品价格是数值类型！', transform: (value) => Number(value)},
  ],

  goodsDesc: [
    {
      required: true,
      message: '请输入商品描述！',
    },
  ]

})
const bigTypeSelectOptions = ref([])
const smallTypeSelectOptions = ref([])
const initBigTypeSelectList = async () => {
  const res = await axios.post("admin/bigType/listAll");
  bigTypeSelectOptions.value = res.data.data;
}
const initSmallTypeSelectList = async () => {
  const res = await axios.post("admin/smallType/listAll");
  smallTypeSelectOptions.value = res.data.data;
}
const initList = () => {
  if (form.value.goodsPhoto.length > 0) {
    let listPre = []
    for (let i = 0; i < form.value.goodsPhoto.length; i++) {
      listPre.push({url: form.value.goodsPhoto[i]})
    }
    swiperList.value = listPre
  }
  if (form.value.goodsParam.length > 0) {
    let listPre = []
    for (let i = 0; i < form.value.goodsParam.length; i++) {
      listPre.push({url: form.value.goodsParam[i]})
    }
    paramList.value = listPre
  }
  if (form.value.goodsDetail.length > 0) {
    let listPre = []
    for (let i = 0; i < form.value.goodsDetail.length; i++) {
      listPre.push({url: form.value.goodsDetail[i]})
    }
    detailList.value = listPre
  }
}
const emptyList = () => {
  videoUrl.value = '';
  imageUrl.value = '';
  swiperList.value = [];
  detailList.value = [];
  paramList.value = [];
}
initBigTypeSelectList();
initSmallTypeSelectList();

const handleConfirm = () => {

  if (form.value.sortName === "") {
    ElMessage.error("请选择商品大类！");
    return;
  }
  if (form.value.categoryName === "") {
    ElMessage.error("请选择商品小类！");
    return;
  }
  if (form.value.goodsType === "") {
    ElMessage.error("请选择商品类别！");
    return;
  }
  formRef.value.validate(async (valid) => {
    if (valid) {
      try {
        let result = await axios.post("admin/product/save", form.value);
        let data = result.data;
        if (data.mate.code === 200) {
          ElMessage.success("执行成功");
          emits("initProductList")
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

watch(
    () => props.dialogValue,
    () => {
      form.value = props.dialogValue;
      if (form.value.goodsId === undefined) {
        console.log("是添加")
      } else {
        console.log("是修改")
        initList();
      }
    },
    {deep: true, immediate: true}
)


const imageUrl = ref("")
const videoUrl = ref("")

const headers = ref({
  token: window.sessionStorage.getItem('token')
})


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
const beforeVideoUpload = (file) => {
  const isMP4 = file.type === 'video/mp4'
  if (!isMP4) {
    ElMessage.error('视频应为MP4格式')
  }
  loading.value = true;
  return isMP4;
}
const handleAvatarSuccess = (res) => {
  imageUrl.value = res.data;
  form.value.goodsAvatar = res.data;
  loading.value = false
}
const handleVideoSuccess = (res) => {
  videoUrl.value = res.data;
  form.value.goodsVideo = res.data;
  loading.value = false
}
const swiperList = ref([])
const paramList = ref([])
const detailList = ref([])
const dialogImageUrl = ref('')
const dialogDetailImageUrl = ref('')
const dialogParamImageUrl = ref('')
const dialogVisible = ref(false)
const dialogDetailVisible = ref(false)
const dialogParamVisible = ref(false)
const handleSwiperSuccess = (res) => {
  if(form.value.goodsPhoto === undefined){
    form.value.goodsPhoto = []
    form.value.goodsPhoto.push(res.data)
  }else {
    form.value.goodsPhoto.push(res.data)
  }
}
const handleDetailSuccess = (res) => {
  if(form.value.goodsDetail === undefined){
    form.value.goodsDetail = []
    form.value.goodsDetail.push(res.data)
  }else {
    form.value.goodsDetail.push(res.data)
  }
}
const handleParamSuccess = (res) => {
  if(form.value.goodsParam === undefined){
    form.value.goodsParam = []
    form.value.goodsParam.push(res.data)
  }else {
    form.value.goodsParam.push(res.data)
  }
}
const handleRemove = (uploadFile, uploadFiles) => {
  console.log(uploadFile, uploadFiles);
  form.value.goodsPhoto = form.value.goodsPhoto.filter(item => item !== uploadFile.url)
};
const handleDetailRemove = (uploadFile, uploadFiles) => {
  console.log(uploadFile, uploadFiles);
  form.value.goodsDetail = form.value.goodsDetail.filter(item => item !== uploadFile.url)
};
const handleParamRemove = (uploadFile, uploadFiles) => {
  console.log(uploadFile, uploadFiles);
  form.value.goodsParam = form.value.goodsParam.filter(item => item !== uploadFile.url)

};
const handlePictureCardPreview = (uploadFile) => {
  dialogImageUrl.value = uploadFile.url;
  dialogVisible.value = true;
};
const handlePictureCardDetailPreview = (uploadFile) => {
  dialogDetailImageUrl.value = uploadFile.url;
  dialogDetailVisible.value = true;
}
const handlePictureCardParamPreview = (uploadFile) => {
  dialogParamImageUrl.value = uploadFile.url;
  dialogParamVisible.value = true;
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

.video-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.video-uploader .el-upload:hover {
  border-color: #409eff;
}

.el-icon.video-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}

.video {
  width: 178px;
  height: 178px;
  display: block;
}

.swiper {
  width: 178px;
  height: 178px;
  display: block;
}
</style>