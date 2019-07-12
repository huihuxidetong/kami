const app = getApp()
var portDomain = getApp().globalData.portDomain;
var imgDomain = getApp().globalData.imgDomain;
var util = require('../../utils/util.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    searchLoading: false, //"上拉加载"的变量，默认false，隐藏
    searchLoadingComplete: false,  //“没有数据”的变量，默认false，隐藏
    totalPage: '',
    pageNum:1,
    messageList:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getMyMessage()
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  getMyMessage: function () {
    wx.request({
      url: portDomain + 'comment/myCommentReply',
      method: 'POST',
      data: {
        "businessKey": wx.getStorageSync('businessKey'),
        "pageNum": this.data.pageNum,
        "pageSize": 20
      },
      success: res => {
        if (res.data.code == 0) {
          var messageList = res.data.data.commentReplyVoList;
          for (var i = 0; i < messageList.length; i++) {
            var updateTime = util.formatTime(new Date(messageList[i].createTime));
            messageList[i].createTime = updateTime;
          }

          let contact = [];
          if (this.data.pageNum > 1) {
            contact = this.data.messageList || [];
          }
          contact = contact.concat(messageList);
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
            messageList: contact,
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
      that.getCardComment();
    }
  },

  updatemessageStatus:function(e){
    var id = e.currentTarget.dataset.id
    wx.request({
      url: portDomain + 'comment/updateCommentReply',
      method: 'POST',
      data: {
        "businessKey": wx.getStorageSync('businessKey'),
        "id": id
      },
      success: res => {
        if (res.data.code == 0) {
          if(id==""){
            wx.showToast({
              title: "标记成功",
              icon: "none"
            })
            this.setData({
              pageNum:1    
            });
            this.getMyMessage()
          }else{
            var index = e.currentTarget.dataset.index
            this.data.messageList[index].isRead=1
            this.setData({
              messageList: this.data.messageList
            });
            wx.navigateTo({
              url: '../cardDetail/cardDetail?cardId=' + e.currentTarget.dataset.cardid
            })
          }
        } else {
          wx.showToast({
            title: res.data.showMsg,
            icon: "none"
          })
        }
      },
    })
  },

  readMessage:function(e){

  }
  
})