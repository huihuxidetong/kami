const app = getApp()
var portDomain = getApp().globalData.portDomain;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    cardId: "",
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.data.cardId = options.cardId
  },

  /**
    * 发送客服消息
    */
  applyCard: function () {
    console.log("我被调用了")
    // wx.request({
    //   url: portDomain + 'users/getOpenId',
    //   method: 'POST',
    //   data: {
    //     "businessKey": wx.getStorageSync('businessKey'),
    //   },
    //   success: res => {
    //     if(res.data.code==0){
    //       var openID = res.data.data.openId
    //       console.log("openID=" + openID)
    this.addApplyData()
    wx.request({
      url: portDomain + 'users/sendServiceMsg',
      method: 'POST',
      data: {
        "businessKey": wx.getStorageSync('businessKey'),
        "creditCardId": this.data.cardId,
        "msgtype": "link"
      },
      success: res => {
       
      }
    })
    // }else{
    //   wx.showToast({
    //     title: res.data.showMsg,
    //     duration: 1000,
    //     icon: "none"
    //   })
    // }

    //   }
    // })

  },

  addApplyData:function(){
    wx.request({
      url: portDomain + 'creditCard/addCreditCardApplyData',
      method: 'POST',
      data: {
        "businessKey": wx.getStorageSync('businessKey'),
        "creditCardId": this.data.cardId,
      },
      success: res => {
       
      }
    })
  }

})