//index.js
//获取应用实例
const app = getApp()
var portDomain = getApp().globalData.portDomain;
var imgDomain = getApp().globalData.imgDomain;
var util = require('../../utils/util.js');
Page({

  data: {
    banner: [],
    imgDomain: imgDomain,
    channelCode:"",
    isCanClickDetail: 1
  },

  onLoad: function (options) {
    console.log(options.channelCode)
    if (options.channelCode != undefined && options.channelCode != ""){
      this.data.channelCode = options.channelCode
      this.savechannelCode()
    }
  },
//保存渠道信息
  savechannelCode: function () {
    wx.request({
      url: portDomain + 'users/saveChannelData',
      method: 'POST',
      data: {
        "businessKey": wx.getStorageSync('businessKey'),
        "channelCode": this.data.channelCode
      },
      success: res => {
        console.log("渠道数据已统计")
      }
    })
  },


  /** 
  * 生命周期函数--监听页面显示
  */
  onShow: function () {
    wx.setStorageSync("pageindex", 0);
    this.getIndexInfo()
  },
  //获取首页信息
  getIndexInfo: function () {
    // wx.showLoading({
    //   mask: true
    // })
    wx.request({
      url: portDomain + 'index/findIndexInfo',
      method: 'POST',
      data: {},
      success: res => {
        wx.hideLoading();
        if(res.data.code==0){
          console.log(res.data.data.isCanClickDetail)
          this.data.banner = res.data.data.bannerList 
          this.data.isCanClickDetail = res.data.data.isCanClickDetail
          var gameList = res.data.data.activityVoList 
          for (var i = 0; i < gameList.length; i++) {
            gameList[i].activityTimeStart = util.formatIndexTime(new Date(gameList[i].activityTimeStart));
            gameList[i].activityTimeEnd = util.formatIndexTime(new Date(gameList[i].activityTimeEnd));   
          }
          this.setData({
            banner: this.data.banner,
            cardlist: res.data.data.creditCardInfoVoList,
            gamelist: gameList
          })

        }else{
          wx.showToast({
            title: res.data.showMsg,
            duration: 1000,
            icon: "none"
          })
        }
      },
      complete: res => {
        // complete
        wx.hideNavigationBarLoading() //完成停止加载
        wx.stopPullDownRefresh() //停止下拉刷新
      }
    })
  },

  goCardList:function(){
    wx.switchTab({
      url: '../card/card'
    })
  },
  goGameList: function () {
    wx.switchTab({
      url: '../game/game'
    })
  },
  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    wx.showNavigationBarLoading() //在标题栏中显示加载
    this.getIndexInfo();
  },
  clickbanner:function(e){
    console.log(111111)
    console.log(e.currentTarget.dataset.type)
    if (this.data.isCanClickDetail) {
      if (1 == e.currentTarget.dataset.type){
        wx.switchTab({
          url: "../../" + e.currentTarget.dataset.link
        }) 
      }else{
        wx.navigateTo({
          url: '../Integral/Integral?link=' + e.currentTarget.dataset.link
        }) 
      }
    }
  },
  /**
  * 信用卡详情
  */
  goCardDetail: function (e) {
    if (this.data.isCanClickDetail == 1) {
      wx.navigateTo({
        url: '../cardDetail/cardDetail?cardId=' + e.currentTarget.dataset.id
      })
    }
  },
  /**
  * 信用卡详情
  */
  goGameDetail: function (e) {
    wx.navigateTo({
      url: '../gameDetail/gameDetail?gameId=' + e.currentTarget.dataset.id
    })
  },

  /**
  * 用户点击右上角分享
  */
  onShareAppMessage: function () {
    return {
      title: '信用卡申请和活动,有你想要的',
      path: '/pages/index/index',
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


})
