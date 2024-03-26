// 导入request请求工具方法
import api from "../../utils/api"
Page({
  data: {
    avatar: '',
    swiperList: [],
    floorList: [],
  },
  getAvatr(){
    api.getAvatar().then(res =>{
      this.setData({
        avatar: res.data.choiceAvatar
      })
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.getAvatr();
    this.getSwiperList();
    this.getFloorList();
  },
 // 获取轮播图数据
 async getSwiperList(){
  api.getSceneSwiper().then(result=>{
    this.setData({
      swiperList: result.data
    })
  })
},
// 获取楼层商品
async getFloorList(){
  api.getSceneFloor().then(result=>{
    this.setData({
      floorList: result.data
    })
  })
},
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})