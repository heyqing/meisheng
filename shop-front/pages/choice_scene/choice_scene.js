import api from '../../utils/api'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    avatar: '',
    currentRegion: '请选择省市区' ,
    isSwitch: false,
    shopType:'',
    goodsType: '',
    phone: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
this.getAvatr();
  },
  getAvatr(){
    api.getAvatar().then(res =>{
      this.setData({
        avatar: res.data.choicePhoto
      })
    })
  },
  regionChange: function (e) {  
    this.setData({  
      currentRegion: e.detail.value // 当用户选择地区后，更新数据  
    });  
  },
  bindchangeSwitch(){
    this.setData({
      isSwitch : this.data.isSwitch ? false : true 
    })
  },
  inputNumber: function(e){
    this.setData({
        phone : e.detail.value
    })
  },
  inputShopType: function(e){
    this.setData({
      shopType: e.detail.value
    })
  },
  inputGoodsType: function(e){
    this.setData({
      goodsType: e.detail.value
    })
  },
  bindconfirm	(){
    if(this.data.shopType === ''){
      wx.showToast({
        title: '未填写商铺类型',
        icon: 'error',
      })
      return;
    }
    if(this.data.currentRegion === '请选择省市区'){
      wx.showToast({
        title: '未选择地址',
        icon: 'error',
      })
      return;
    }
    if(this.data.goodsType === ''){
      wx.showToast({
        title: '未填写产品类型',
        icon: 'error',
      })
      return;
    }
    if(this.data.phone === ''){
      wx.showToast({
        title: '未填写手机号',
        icon: 'error',
      })
      return;
    }
    const param ={
      "currentRegion": this.data.currentRegion,
    "isSwitch": this.data.isSwitch,
    "shopType": this.data.shopType,
    "goodsType": this.data.goodsType,
    "phone": this.data.phone
    }
    api.saveCustom(param).then(res =>{
      if(res.mate.code === 200){
        wx.showToast({
          title: '提交成功',
          icon: 'success',
          duration: 3000
        })
        this.setData({
          currentRegion: '请选择省市区' ,
          isSwitch: false,
          shopType:'',
          goodsType: '',
          phone: ''
        })
      }
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