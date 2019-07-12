const app = getApp()
var portDomain = getApp().globalData.portDomain;
var imgDomain = getApp().globalData.imgDomain;
var util = require('../../utils/util.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    imgDomain: imgDomain,
    gameId:'',
    shareTitle:'',
    channelCode: "",
    concernStatus: 0,//关注状态
    concernText: "关注",
    goIndex: true, //非分享页点进来的不展示返回首页按钮
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.data.gameId = options.gameId
    if (options.share) {
      this.setData({
        goIndex: false
      })
    }
    this.getGameDetail()
  },

  goIndex: function () {
    wx.reLaunch({
      url: '../index/index',
    })
  },

  //获取活动详情
  getGameDetail: function () {
    wx.request({
      url: portDomain + 'activity/findActivityDetail',
      method: 'POST',
      data: {
        "activityId": this.data.gameId,
        "businessKey": wx.getStorageSync('businessKey'),
      },
      success: res => {
        if (res.data.code == 0) {
          var gameDetail = res.data.data
          this.data.shareTitle = gameDetail.activityName
          this.data.concernStatus = gameDetail.userConcernStatus
          if (this.data.concernStatus == 1) {//已关注
            this.data.concernText = "已关注"
          } else {
            this.data.concernText = "关注"
          }
          gameDetail.activityTimeStart = util.formatIndexTime(new Date(gameDetail.activityTimeStart));
          gameDetail.activityTimeEnd = util.formatIndexTime(new Date(gameDetail.activityTimeEnd));
          var labelList = gameDetail.describe;
          if (labelList != null) {
            gameDetail.describe = labelList.split(";");
          }
          this.setData({
            gameDetail: gameDetail,
            concernText: this.data.concernText
          })
        
          
        } else {
          wx.showToast({
            title: res.data.showMsg,
            duration: 1000,
            icon: "none"
          })
        }
      }
     
    })
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    return {
      title: this.data.shareTitle,
      path: '/pages/gameDetail/gameDetail?gameId=' + this.data.gameId + "&share=1",
      success: function (res) {
        // 转发成功
        wx.showToast({
          title: "分享成功",
          duration: 1000,
          icon: "success"
        })
      },
      fail: function (res) {
        wx.showToast({
          title: "分享失败",
          duration: 1000,
          icon: "none"
        })
      }
    }
  },

  getUserInfo: function (e) {
    var that = this;
    var buttonindex = e.currentTarget.dataset.buttonindex
    var phoneinfo;
    wx.getSystemInfo({
      success: (res) => {
        console.log(res)
        phoneinfo = res
        console.log("111111111111111111")
        console.log(phoneinfo)
      }
    })
    // wx.getSetting({
    //    success(res) {
        // if (!res.authSetting['scope.userInfo']) {//未授权
          wx.getUserInfo({
            success: function (res) {
              wx.request({
                url: portDomain + 'users/saveUsers',
                  data: {
                  "businessKey": wx.getStorageSync('businessKey'),
                  "userPortrait": res.userInfo.avatarUrl,
                  "nickName": res.userInfo.nickName,
                  "channelCode": that.data.channelCode,
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
                    "window_use_high": phoneinfo.windowHeight,
                    "window_use_width": phoneinfo.windowWidth,
                   // applicationKey: "CARD",
                  },
                  method: 'post',
                  success: res => {
                    wx.setStorageSync("authorized", true);
                    wx.hideLoading();
                    if (res.data.code == 0) {
                      if (buttonindex == 1) {//关注
                        that.followGame()
                        } else {//参与活动
                        that.joinGame()
                      }

                    } else {
                       wx.showToast({
                        title: "授权失败",
                       duration: 1000,
                       icon: "none"
                       })
                    }
                  },
                  fail: res => {


                   }

                })
               }
              })
      //        } else {//已授权
      //         if (buttonindex == 1) {//关注
      //           that.followGame()
      //          } else {//参与活动
      //           that.joinGame()
      //           }

      //       }
      //      }
      //  })
  
   

  },
  //关注
  followGame: function () {
    var url = ""
    var content = ""
    if (this.data.concernText == "已关注") {//已关注
      url = portDomain + 'users/updateUserConcernBasic'
      content = "取消关注成功"
      this.data.concernText = "关注"
    } else {
      url = portDomain + 'users/addUserConcernBasic'
      content = "关注成功"
      this.data.concernText = "已关注"
    }
    wx.request({
      url: url,
      data: {
        "businessKey": wx.getStorageSync('businessKey'),
        "concernId": this.data.gameId,
        "concernType": 2
      },
      method: "post",
      success: res => {
        if (res.data.code == 0) {
          // console.log("回复成功")
          wx.showToast({
            icon: "none",
            title: content
          })
          this.setData({
            concernText: this.data.concernText
          })
      
        } else {
          wx.showToast({
            title: res.data.showMsg,
            icon: 'none'
          })
        }
      }
    })
  },

  //去信用卡详情
  goCardDetail: function (e){
    wx.navigateTo({
      url: '../cardDetail/cardDetail?cardId=' + e.currentTarget.dataset.id
    }) 
  },

  /**
   * 更多信用卡
   */
  moreCard: function () {
    wx.navigateTo({
      url: '../relatedCard/relatedCard?gameId=' + this.data.gameId
    }) 
  },

  /**
   * 参与活动
   */
  joinGame: function () {
    wx.navigateTo({
      url: '../joinGame/joinGame?gameId=' + this.data.gameId
    }) 
  },

})