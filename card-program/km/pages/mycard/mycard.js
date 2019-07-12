const app = getApp()
var portDomain = app.globalData.portDomain;
var imgDomain = app.globalData.imgDomain;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    imgDomain: imgDomain,
    notice:false,
    cardList: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //this.getCardList()
  },

  onShow:function(){
    this.getCardList()
  },

  /**
   * 关闭提示
   */
  closeNotice: function () {
    this.setData({
      notice:true
    })
  },

  /**
   * 去添加信用卡
   */
  addCard: function () {
    wx.navigateTo({
      url: '../addCard/addCard'
    }) 
  },

  /**
   * 获取我的信用卡列表
   */
  getCardList: function () {
    wx.request({
      url: portDomain + 'users/findMyCreditCardList',
      method: 'POST',
      data: {
        "businessKey": wx.getStorageSync('businessKey'),
      },
      success: res => {
        if (res.data.code == 0) {
          this.setData({
            bankList: res.data.data.creditCardInfoHotVoPageList
          })
        } else {
          wx.showToast({
            title: res.data.showMsg,
            icon: "none"
          })
        }     
      },
    })
  },


  
})