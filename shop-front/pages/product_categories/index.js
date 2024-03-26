import api from '../../utils/api'
import regeneratorRuntime from '../../lib/runtime/runtime';
// pages/product_categories/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    categoryFloor: [],
    //一次请求返回数量  
    responseNum: 10, // 初始化为10  
    selectNum: 0,     // 初始化为0  
    //是否可以进行上拉触底  
    isFlag: true 
  },
  async getCategoryGoods(categoryId){
    api.getCategoryGoods(categoryId).then(res=>{
      this.setData({
        categoryFloor: [...this.data.categoryFloor,...res.data]
      })
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getCategoryGoods(options.id)
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
  onReachBottom(){
    if(this.isFlag){
     this.data.selectNum+=1
      this.getFloorList()
    }
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})