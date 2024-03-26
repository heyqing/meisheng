// 导入request请求工具方法
import api from "../../utils/api"
Page({
  data: {  
    // 轮播图数组  
    swiperList: [],  
    sortList: [],  
    floorList: [],  
    //一次请求返回数量  
    responseNum: 10, // 初始化为10  
    selectNum: 0,     // 初始化为0  
    //是否可以进行上拉触底  
    isFlag: true  
 },  
 onLoad:function(){
  this.getSwiperList();
  this.getSortList();
  this.getFloorList();
 },
 // 获取轮播图数据
 async getSwiperList(){
    api.getSwiper().then(result=>{
      this.setData({
        swiperList: result.data
      })
    })
  },

  // 获取分类
  async getSortList(){
    api.getSort().then(result=>{
      this.setData({
        sortList: result.data
      })
    })
  },

  // 获取楼层商品
  async getFloorList(){
    this.data.isFlag = false
    
    const param = {  
      "responseNum": this.data.responseNum,  
      "selectNum": this.data.selectNum
    };  
    console.log(param)
    api.getFloor(param).then(result=>{
      this.setData({
        floorList: [...this.data.floorList,...result.data]
      })
    })
    this.data.isFlag = true
  },

  // 大类点击事件处理 存储商品类别到全局数据
  handleTypeJump(event){
    var index=event.currentTarget.dataset.index;
    const app=getApp();
    app.globalData.index=index;
    wx.switchTab({
      url: '/pages/category/index'
    })
  },
 onReachBottom(){
   if(this.data.isFlag){
    this.data.selectNum+=1
     this.getFloorList()
   }
 }
})