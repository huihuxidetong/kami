const app = getApp()
var portDomain = app.globalData.portDomain;
var imgDomain = app.globalData.imgDomain;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    imgDomain: imgDomain,
    gameId: "",
    searchLoading: false, //"上拉加载"的变量，默认false，隐藏
    searchLoadingComplete: false,  //“没有数据”的变量，默认false，隐藏
    totalPage: '',
    pageNum: 1,
    cardList:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.data.gameId = options.gameId
    //this.getCardLsit()
  },
  onShow: function (options) {
    this.getCardLsit()
  },

  getCardLsit:function(){
    wx.request({
      url: portDomain + 'activity/findActivityCreditCardPage',
      method: 'POST',
      data: {
        "activityId": this.data.gameId,
        "pageNum": this.data.pageNum,
        "pageSize":20
      },
      success: res => {
        if (res.data.code == 0) {
          var cardList = res.data.data.activityCreditCardVoList;
          let contact = [];
          if (this.data.pageNum > 1) {
            contact = this.data.cardList || [];
          }
          contact = contact.concat(cardList);
          var count = res.data.data.count;
          if (count == contact.length && this.data.pageNum != 1) {
            this.setData({
              searchLoading: false,
              searchLoadingComplete: true
            });
            const that = this;
            setTimeout(function () {
              that.setData({
                searchLoadingComplete: false
              });
            }, 1500);
          }
          this.setData({
            cardList: contact,
            searchLoading: false,
            totalPage: res.data.data.totalPage,
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
   * 信用卡详情
   */
  goCardDetail: function (e) {
    wx.navigateTo({
      url: '../cardDetail/cardDetail?cardId=' + e.currentTarget.dataset.id
    })
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    wx.showNavigationBarLoading() //在标题栏中显示加载
    this.setData({
      pageNum: 1
    })
    this.getCardLsit();
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    //如果已经是最后一页，则展示已加载完成，几秒后消失
    if (this.data.totalPage == this.data.pageNum) {
      this.setData({
        searchLoadingComplete: true
      });
      const that = this;
      setTimeout(function () {
        that.setData({
          searchLoadingComplete: false
        });
      }, 1500);
      return;
    }
    this.setData({
      searchLoading: true,
      searchLoadingComplete: false,
      showSearchResult: false
    });
    let that = this;
    if (that.data.searchLoading && !that.data.searchLoadingComplete) {
      that.setData({
        pageNum: that.data.pageNum + 1,  //每次触发上拉事件，把pageNum+1
        isFromSearch: false,  //触发到上拉事件，把isFromSearch设为为false
      });
      that.getCardLsit();
    }
  },

})