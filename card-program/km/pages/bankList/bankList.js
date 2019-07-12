var portDomain = getApp().globalData.portDomain;
var imgDomain = getApp().globalData.imgDomain;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    imgDomain: imgDomain,
    pageNum: 1,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getBankList()
  },

  //获取信用卡列表
  getBankList: function () {
    wx.request({
      url: portDomain + 'creditCard/findCreditCardList',
      method: 'POST',
      data: {
        "pageNum": this.data.pageNum,
        "pageSize": 20
      },
      success: res => {
        if (res.data.code == 0) {
         

         // var bankList = res.data.data.bankVoLiist
          
          this.setData({
            bankList: res.data.data.bankVoLiist,
          });
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
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    
  },
  //去银行列表
  goCardList: function (e) {
    wx.navigateTo({
      url: '../bankCardList/bankCardList?bankId=' + e.currentTarget.dataset.id
    })
  },
  

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    wx.showNavigationBarLoading() //在标题栏中显示加载
    this.getBankList()
  },
})