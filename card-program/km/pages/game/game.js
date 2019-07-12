// pages/game/game.js
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
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
   
  },
  /**
    * 生命周期函数--监听页面显示
    */
  onShow: function () {
    wx.setStorageSync("pageindex", 2);
    this.getGameList();
  },
  //获取活动列表
  getGameList:function(){
    wx.request({
      url: portDomain + 'activity/findActivityList',
      method: 'POST',
      data: {},
      success: res => {
        if (res.data.code == 0) {
          var gameList = res.data.data.activityVoList4Bank
          for (var i = 0; i < gameList.length; i++) {
            gameList[i].activityTimeStart = util.formatIndexTime(new Date(gameList[i].activityTimeStart));
            gameList[i].activityTimeEnd = util.formatIndexTime(new Date(gameList[i].activityTimeEnd));
          }
          var platList = res.data.data.activityVoList4Platform
          for (var i = 0; i < platList.length; i++) {
            platList[i].activityTimeStart = util.formatIndexTime(new Date(platList[i].activityTimeStart));
            platList[i].activityTimeEnd = util.formatIndexTime(new Date(platList[i].activityTimeEnd));
          }
          this.setData({
            platList: platList,
            gameList: gameList
          })

        } else {
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

  /**
   * 去活动详情
   */
  godetail: function (e) {
    console.log(e)
    wx.navigateTo({
      url: '../gameDetail/gameDetail?gameId=' + e.currentTarget.dataset.id,
    })
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    wx.showNavigationBarLoading() //在标题栏中显示加载
    this.getGameList();
  },
  /**
* 用户点击右上角分享
*/
  onShareAppMessage: function () {
    return {
      title: '信用卡活动',
      path: '/pages/game/game',
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