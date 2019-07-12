const app = getApp()
var portDomain = getApp().globalData.portDomain;
var imgDomain = getApp().globalData.imgDomain;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    searchLoading: false, //"上拉加载"的变量，默认false，隐藏
    searchLoadingComplete: false,  //“没有数据”的变量，默认false，隐藏
    totalPage: '',
    pageNum: 1,
    followList:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.data.cardId = options.cardId
    this.getFollowList()
  },

  /**
   * 获取关注列表
   */
  getFollowList: function () {
    wx.request({
      url: portDomain + 'creditCard/findCreditCardConcern',
      method: 'POST',
      data: {
        "id": this.data.cardId,
        "pageNum": this.data.pageNum,
        "pageSize": 20
      },
      success: res => {
        if (res.data.code == 0) {
          var followList = res.data.data.creditCardConcemVoList;
         
          let contact = [];
          if (this.data.pageNum > 1) {
            contact = this.data.followList || [];
          }
          contact = contact.concat(followList);
          var count = res.data.data.count;

          // console.log("count = " + count);
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
            followList: contact,
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
      }

    })
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
      that.getFollowList();
    }
  },

})