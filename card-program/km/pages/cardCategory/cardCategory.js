// pages/game/game.js
const app = getApp()
var portDomain = getApp().globalData.portDomain;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    bankId:"",
    creditCardName:""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.data.bankId = options.bankId
    this.getCategory()
  },

  /**
   * 获取类别列表
   */
  getCategory: function () {
    wx.request({
      url: portDomain + 'creditCard/queryCreditCardList',
      method: 'POST',
      data: {
        "bankId": this.data.bankId,
        "creditCardName": this.data.creditCardName
      },
      success: res => {
        if (res.data.code == 0) {
          this.setData({
            banklist: res.data.data.creditCardInfoQueryVos
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
   * 搜索输入
   */
  iputcardname: function (e) {
    this.data.creditCardName=e.detail.value
    this.getCategory()
  },

  /**
   * 确认选择
   */
  selectedConfirm: function (e) {
    let pages = getCurrentPages();//当前页面
    let prevPage = pages[pages.length - 2];//上一页面 
    prevPage.setData({//直接给上一页面赋值
      cardCategory: e.currentTarget.dataset.value,
      cardId: e.currentTarget.dataset.id
    });
    wx.navigateBack({//返回
      delta: 1
    })
  },

})