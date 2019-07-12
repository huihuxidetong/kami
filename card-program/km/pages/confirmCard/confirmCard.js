var portDomain = getApp().globalData.portDomain;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    cardName:"",
    cardNo:'',
    bankId:"",
    cardId:"",
    userName:""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.data.cardName = options.cardName
    this.data.cardNo = options.cardNo
    this.data.bankId = options.bankId
    this.data.cardId = options.cardId
    this.data.userName = options.userName
    this.setData({
      cardName: this.data.cardName,
      cardNo: this.data.cardNo
    })
    
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    
  },

  //保存卡片信息
  saveInfo: function () {
    wx.showLoading({
      mask: true
    })
    wx.request({
      url: portDomain + 'users/addMyCreditCard',
      method: 'POST',
      data: {
        "businessKey": wx.getStorageSync('businessKey'),
        "cardIssuingBank": this.data.bankId,
        "cardIssuingBankType": this.data.cardId,
        "creditCardHolder": this.data.userName,
        "creditCardNumber": this.data.cardNo
      },
      success: res => {
        wx.hideLoading()
        if (res.data.code == 0) {
          wx.showToast({
            title: "参与成功",
            duration: 1000,
            icon: "none"
          })
          wx.navigateBack({
            delta: 2
          })
        } else {
          wx.showToast({
            title: res.data.showMsg,
            duration: 1000,
            icon: "none"
          })
        }
      },
      fail: res => {
        wx.hideLoading()
      }
    })
  },

  /**
   * 确定
   */
  confirm: function () {
    this.saveInfo()
  
  },


})