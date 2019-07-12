// pages/mine/mine.js
const app = getApp()
var portDomain = getApp().globalData.portDomain;
var imgDomain = getApp().globalData.imgDomain;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    authorized: true,
    imgDomain: imgDomain,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // wx.openSetting({
    //   scope: 'scope.userInfo',
    //   success: function (res) {
       
    //   },
    // })
  },

  onShow:function(){
    if (wx.getStorageSync("authorized")) {
      this.getmyInfo()
    } else {
      var that = this;
      wx.getSetting({
        success: (res) => {
          console.log(res)
          if (!res.authSetting['scope.userInfo']) {
            //弹出授权弹框
            console.log("用户未曾授权")
            this.setData({
              authorized: false
            })
          } else {
            console.log("用户已经授权")
            wx.setStorageSync("authorized", true);
            that.getmyInfo()
          }
        }
      })
    }
  },
  getUserInfo: function (e) {
    var that = this;
    var phoneinfo;
    wx.getSystemInfo({
      success: (res) => {
        console.log(res)
        phoneinfo = res
      }
    })
    wx.getSetting({
      success(res) {
        if (!res.authSetting['scope.userInfo']) {//用户拒绝授权
          that.setData({
            authorized: true
          })
          that.goBack()
        } else {
          wx.setStorageSync("authorized", true);
          wx.getUserInfo({
            success: function (res) {
              console.log(res)
              wx.request({
                url: portDomain + 'users/saveUsers',
                data: {
                  "businessKey": wx.getStorageSync('businessKey'),
                  "userPortrait": res.userInfo.avatarUrl,
                  "nickName": res.userInfo.nickName,
                  "channelCode": "",
                  "clientPlatform": phoneinfo.platform,
                  "clientPlatformVersion": phoneinfo.SDKVersion,
                  "fontSize": phoneinfo.fontSizeSetting,
                  "mobileBrand": phoneinfo.brand,
                  "mobileDprHigh": phoneinfo.pixelRatio,
                  "mobileDprWide": phoneinfo.pixelRatio,
                  "mobileModel": phoneinfo.model,
                  "mobileSystem": phoneinfo.system,
                  "performanceLeval": phoneinfo.batteryLevel,
                  "screenHigh": phoneinfo.screenHeight,
                  "screenWide": phoneinfo.screenWidth,
                  "statusBarHigh": phoneinfo.statusBarHeight,
                  "wecharLanguage": phoneinfo.language,
                  "wecharVersion": phoneinfo.version,
                  "windowUseHigh": phoneinfo.windowHeight,
                  "windowUseWidth": phoneinfo.windowWidth,
                 // applicationKey: "CARD",
                },
                method: 'post',
                success: res => {
                  that.setData({
                    authorized: true
                  })
                  if (res.data.code == 0) {
                    wx.showToast({
                      title: "绑定成功",
                      duration: 1000,
                      icon: "success"
                    })

                    that.getmyInfo()

                  } else {
                    wx.showToast({
                      title: res.data.showMsg,
                      duration: 1000,
                      icon: "none"
                    })
                    console.log(res.data.showMsg)
                    that.goBack()
                  }
                },
                fail: res => {
                  that.setData({
                    authorized: true
                  })
                  wx.showToast({
                    title: "授权失败",
                    duration: 1000,
                    icon: "none"
                  })
                  that.goBack()
                }

              })
            }
          })

        }
      }
    })
  },
  onCancel: function (options) {
    this.setData({
      authorized: true
    })
    this.goBack()
  },
  goBack:function(){
    var pageIndex = wx.getStorageSync('pageindex')
    if (pageIndex == 1) {//去信用卡页面
      wx.switchTab({
        url: '../card/card'
      })
    } else if (pageIndex == 2) {//去活动页面
      wx.switchTab({
        url: '../game/game'
      })
    } else {//去首页
      wx.switchTab({
        url: '../index/index'
      })
    }
  },

  getmyInfo:function(){
    wx.request({
      url: portDomain + 'users/myInfo',
      method: 'POST',
      data: {
        "businessKey": wx.getStorageSync('businessKey'),
      },
      success: res => {
        if (res.data.code == 0) {
          var myinfo = res.data.data
          console.log(11111);
          console.log(myinfo);
          var labelList = myinfo.userChooseLable;
          if (labelList != null && labelList.length > 0) {
            myinfo.userChooseLable = labelList.split(",");
          }
          this.setData({
            myinfo: myinfo,
           
          })
        } else {
          wx.showToast({
            title: res.data.showMsg,
            icon: "none"
          })
        }


      }
    })
  },

 /**
   * 我的标签
   */
  gomyLabel: function (){
    wx.navigateTo({
      url: '../myLabel/myLabel' 
    }) 
  },
 /**
   * 我的信用卡
   */
  gomyCard: function () {
    wx.navigateTo({
      url: '../mycard/mycard'
    })
  },
  /**
   * 我的消息
   */
  gomyMessage: function () {
    wx.navigateTo({
      url: '../myMessage/myMessage'
    })
  },
  /**
  * 我的关注
  */
  gomyFollow: function () {
    wx.navigateTo({
      url: '../myFollow/myFollow'
    })
  }
})