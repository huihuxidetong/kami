const app = getApp()
var portDomain = getApp().globalData.portDomain;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userName: "",
    cardNo: "",
    phoneNo:"",
    gameId:""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.data.gameId = options.gameId
  },

  //输入持卡人姓名
  iputname: function (e) {
    this.data.userName = e.detail.value
  },
  //输入电话
  iputphoneNo: function (e) {
    this.data.phoneNo = e.detail.value
  },
  //输入卡号
  iputcard: function (e) {
    this.data.cardNo = e.detail.value
  },
  /**
   *提交
   */
  submit: function () {
    var toastContent = this.verifyContent()
    if (toastContent==""){
      this.joinGame()
    }else{
      wx.showToast({
        title: toastContent,
        duration: 1000,
        icon: "none"
      })
    }
  },

  //输入验证
  verifyContent: function () {
    var toastContent = '';
    if (this.data.userName == "") {
      toastContent = '请填写10个字以内的参与人姓名'
      return toastContent;
    }

    if (this.data.phoneNo == '' || this.data.phoneNo.length < 11) {
      toastContent = '请填写11位正确手机号'
      return toastContent;
    }
    if (this.data.cardNo == "" || this.data.cardNo.length < 13) {
      toastContent = '请填写13-19位之间的信用卡号'
      return toastContent;
    }

    return toastContent;
  },

  //参加活动
  joinGame: function () {
    wx.showLoading({
      mask: true
    })
    wx.request({
      url: portDomain + 'activity/joinActivity',
      method: 'POST',
      data: {
        "activityId": this.data.gameId,
        "businessKey": wx.getStorageSync('businessKey'),
        "joinUserCreditCard": this.data.cardNo,
        "joinUserMobile": this.data.phoneNo,
        "joinUserName": this.data.userName
      },
      success: res => { 
        wx.hideLoading()
        if (res.data.code == 0) {
          wx.showToast({
            title: "参与成功",
            duration: 1000,
            icon: "none"
          })
          wx.navigateBack()
        }else{
          wx.showToast({
            title: res.data.showMsg,
            duration: 1000,
            icon: "none"
          })
        }
      },
      fail:res=>{
        wx.hideLoading()
      }
    })
  }

})