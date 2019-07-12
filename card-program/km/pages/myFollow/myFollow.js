const app = getApp()
var portDomain = getApp().globalData.portDomain;
var imgDomain = getApp().globalData.imgDomain;
var util = require('../../utils/util.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    currentTab: 0,
    imgDomain: imgDomain,
    searchLoading: false, //"上拉加载"的变量，默认false，隐藏
    searchLoadingComplete: false,  //“没有数据”的变量，默认false，隐藏
    totalPage: '',
    pageNum: 1,
    cardList:[],
    gameList:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
  },

  onShow: function (options){
    //if (this.data.currentTab==0){
      this.data.pageNum=1
      this.getUserConcern() 
   // }
    
  },

  //tab标题点击监听
  switch: function (e) {
    //console.log("点击了 " + e.target.dataset.current)
    this.setData({
      currentTab: e.target.dataset.current
    })
    this.setData({
      pageNum:1
    })
    this.getUserConcern()
  },

  onSwiperChanged:function(e){
   console.log(e)
    this.setData({
      currentTab: e.detail.current
    })
    this.setData({
      pageNum: 1
    })
    this.getUserConcern()
  },

  /**
   * 获取用户关注信息
   */
  getUserConcern: function () {
    wx.request({
      url: portDomain + 'users/findUserConcernBasic',
      method: 'POST',
      data: {
        "businessKey": wx.getStorageSync('businessKey'),
        "concernType": parseInt(this.data.currentTab)+1,
        "pageNum": this.data.pageNum,
        "pageSize": 20
      },
      success: res => {
        if (res.data.code == 0) {
          if (this.data.currentTab==0){//信用卡
            var cardList = res.data.data.creditCardInfoHotVoList;
            //console.log("cardList=" + cardList)
           
            let contact = [];
            if (this.data.pageNum > 1) {
              contact = this.data.cardList || [];
            }
           // console.log("contact = " + contact.length);
            if (cardList!=null){
              contact = contact.concat(cardList);
            }
           // contact = contact.concat(cardList);
            //console.log("1111111111111111 = " + contact);
            var count = res.data.data.count;
            //console.log("contact = " + contact.length);

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
              cardList: contact,
              searchLoading: false,
              totalPage: res.data.data.totalPage,
            });

          }else{//活动
            var gameList = res.data.data.activityIndexVoList;
            console.log("gameList=" + gameList)
            if (gameList!=null){
              for (var i = 0; i < gameList.length; i++) {
                gameList[i].activityTimeStart = util.formatIndexTime(new Date(gameList[i].activityTimeStart));
                gameList[i].activityTimeEnd = util.formatIndexTime(new Date(gameList[i].activityTimeEnd));
              }
            }

              let contact = [];
              if (this.data.pageNum > 1) {
                contact = this.data.gameList || [];
              }
              if (gameList != null) {
                contact = contact.concat(gameList);
              }
             // contact = contact.concat(gameList);
              var count = res.data.data.count;
              console.log("contact = " + contact.length);

              console.log("count = " + count);
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
            gameList: contact,
            searchLoading: false,
            totalPage: res.data.data.totalPage,
          });
          }
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
      that.getUserConcern();
    }
  },
  
  goCardDetail: function (e) {
    wx.navigateTo({
      url: '../cardDetail/cardDetail?cardId=' + e.currentTarget.dataset.id
    })
  },
  goGameDetail: function (e) {
    wx.navigateTo({
      url: '../gameDetail/gameDetail?gameId=' + e.currentTarget.dataset.id
    })
  },
})