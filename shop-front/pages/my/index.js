// 导入request请求工具方法
import api from '../../utils/api'
import {getBaseUrl, requestUtil,getLogin,getUserProfile, requestPay} from "../../utils/requestUtil.js";
import regeneratorRuntime from '../../lib/runtime/runtime';

Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo:{}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

    // 判断缓存中是否有token
    const token=wx.getStorageSync('token');
    if(!token){
      wx.showModal({
        title:'友情提示',
        content:'微信授权登录后，才可进入个人中心',
        success:(res)=>{
          Promise.all([getLogin(),getUserProfile()]).then((res)=>{
            console.log(res)
            let loginParam={
              code:res[0].code,
              nickName:res[1].userInfo.nickName,
              avatarUrl:res[1].userInfo.avatarUrl
            }
            console.log(loginParam);
            // 把用户信息放到缓存中
            wx.setStorageSync('userInfo', res[1].userInfo);
            console.log(loginParam)
            this.wxlogin(loginParam);
            this.setData({userInfo:res[1].userInfo});
          })
        }
      })
    }else{
      console.log("token:"+token);
    }
  },

  
  // 点击 编辑收货地址
  handleEditAddress(){
    console.log("编辑收货地址")
    wx.chooseAddress({
    
    });

  },

    /**
   * 请求后端获取用户token
   * @param {} loginParam 
   */
  async wxlogin(loginParam){
    // 发送请求 获取用户的token
    api.login(loginParam).then(result=>{
      let token=result.data;
      console.log(result)
      console.log(token)
      if(result.mate.code===200){
        // 存储token到缓存
        wx.setStorageSync('token',token);
      }
    })
  },
  onReLogin (){
    try {  
      wx.removeStorageSync('token');  
      console.log('Token has been removed from storage.');  
      // ... 其他重新登录前的准备工作 ...  
    } catch (e) {  
      console.error('Failed to remove token from storage:', e);  
    }  
    wx.reLaunch({  
      url: '/pages/my/index'
    });  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    console.log("onShow")
    const userInfo=wx.getStorageSync('userInfo');
    this.setData({userInfo});
  }

})