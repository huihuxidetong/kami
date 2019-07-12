const app = getApp()
var portDomain = getApp().globalData.portDomain;
var imgDomain = getApp().globalData.imgDomain;
var util = require('../../utils/util.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    imgDomain:imgDomain,
    cardId:"",
    channelCode: "",
    searchLoading: false, //"上拉加载"的变量，默认false，隐藏
    searchLoadingComplete: false,  //“没有数据”的变量，默认false，隐藏
    totalPage: '',
    pageNum: 1,
    concernTotal:"",//关注人数
    concernStatus:0,//关注状态
    concernText:"关注",
    openCreditCardUrl:"",
    shareTitle:'',
    goIndex: true, //非分享页点进来的不展示返回首页按钮
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.data.cardId = options.cardId
    if (options.share) {
      this.setData({
        goIndex: false
      })
    }
    this.getCardDetail()
    wx.getSetting({
      success: (res) => {
        console.log(res)
        if (!res.authSetting['scope.userInfo']) {
          //弹出授权弹框
          console.log("用户未曾授权")
    
        } else {
          console.log("用户已经授权")
    
        }
      }
    })
  },
  goIndex: function () {
    wx.reLaunch({
      url: '../index/index',
    })
  },

  onShow:function(){
    console.log("11111111111111111")
    this.data.pageNum =1
    this.getCardComment()
  },

  /**
   * 评论或回复
   */
  goreply: function () {
    wx.navigateTo({
      url: '../reply/reply'
    })
  },

  /**
   * 去关注列表
   */
  gofollowList: function () {
    // if (this.data.concernTotal>0){
    //   wx.navigateTo({
    //     url: '../followList/followList'
    //   })
    // }
    wx.navigateTo({
      url: '../followList/followList?cardId=' + this.data.cardId
    })
    
  },

  /**
   * 发送客服消息
   */
  applyCard: function () {
   wx.navigateTo({
     url: '../applyTips/applyTips?cardId=' + this.data.cardId,
   })
    
  },

  /**
   * 获取信用卡详情
   */
  getCardDetail: function () {
    wx.request({
      url: portDomain + 'creditCard/findCreditCardDetail',
      method: 'POST',
      data: {
        "id": this.data.cardId,
        "businessKey": wx.getStorageSync('businessKey'),
      },
      success: res => {
        if (res.data.code == 0) {
          var cardDetail = res.data.data
          this.data.concernStatus = cardDetail.userConcernStatus
          this.data.shareTitle = cardDetail.bankName+cardDetail.cardName
          if (this.data.concernStatus==1){//已关注
            this.data.concernText="已关注"
          }else{
            this.data.concernText = "关注"
          }
          var labelList = cardDetail.creditCharacters;
          if (labelList != null && labelList.length > 0) {
            cardDetail.creditCharacters = labelList.split(",");
          }
          var privilege = cardDetail.cardPrivilege;
          if (privilege != null && privilege.length > 0) {
            cardDetail.cardPrivilege = privilege.split(";");
          }
          var process = cardDetail.oprProcess
          if (process != null && process.length > 0) {
            cardDetail.oprProcess = process.split(",");
          }
          var gameList = cardDetail.creditDetailActivityVoList
          for (var i = 0; i < gameList.length; i++) {
            gameList[i].activityTimeStart = util.formatIndexTime(new Date(gameList[i].activityTimeStart));
            gameList[i].activityTimeEnd = util.formatIndexTime(new Date(gameList[i].activityTimeEnd));
          }
          cardDetail.creditDetailActivityVoList = gameList
          this.data.concernTotal = cardDetail.creditCardConcernTotal
          this.setData({
            cardDetail: cardDetail,
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
  //获取信用卡评论
  getCardComment:function(){
   // console.log('this.data.pageNum=' + this.data.pageNum)
    wx.request({
      url: portDomain + 'creditCard/findCreditCardComment',
      method: 'POST',
      data: {
        "id": this.data.cardId,
        "pageNum": this.data.pageNum,
        "pageSize": 20
      },
      success: res => {
        if (res.data.code == 0) {
          var messageList = res.data.data.commentVos;
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
  //去活动详情
  godetail:function(e){
    wx.navigateTo({
      url: '../gameDetail/gameDetail?gameId=' + e.currentTarget.dataset.id
    })
  },
  //回复
  answerQuestion:function(e){
    console.log(e)
    wx.navigateTo({
      url: '../reply/reply?cardId=' + this.data.cardId + "&commentId=" + e.currentTarget.dataset.id + '&replyId=' + e.currentTarget.dataset.index + "&toUid=" + e.currentTarget.dataset.key + "&replytype=" + e.currentTarget.dataset.replytype + "&toUserName=" + e.currentTarget.dataset.name
    })
  },
  //评论
  addComment:function(e){
    wx.navigateTo({
      url: '../reply/reply?toUserName=评论&cardId=' + this.data.cardId 
    })

  },

  //关注
  followCard:function(){
    var url= ""
    var content = ""
    if (this.data.concernStatus == 1) {//已关注
      url = portDomain + 'users/updateUserConcernBasic'
      content = "取消关注成功"
    }else{
      url =portDomain + 'users/addUserConcernBasic'
      content = "关注成功"
    }
    wx.request({
      url: url,
      data: {
        "businessKey": wx.getStorageSync('businessKey'),
        "concernId": this.data.cardId,
        "concernType": 1
      },
      method: "post",
      success: res => {
        if (res.data.code == 0) {
          // console.log("回复成功")
          wx.showToast({
            icon: "none",
            title: content
          })
          this.getCardDetail()
        } else {
          wx.showToast({
            title: res.data.showMsg,
            icon: 'none'
          })
        }
      }
    })
  },


  getUserInfo: function (e) {
    var that = this;
    var buttonindex = e.currentTarget.dataset.buttonindex
    console.log("buttonindex=" + buttonindex)
    var phoneinfo;
    wx.getSystemInfo({
      success: (res) => {
       // console.log(res)
        phoneinfo = res
       // console.log("111111111111111111")
        //console.log(phoneinfo)
      }
    })
    // wx.getSetting({
    //   success(res) {
    //     if (!res.authSetting['scope.userInfo']) {//未授权
    //     console.log("1111111111111")
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
                //  applicationKey: "CARD",
                },
                method: 'post',
                success: res => {
                  wx.setStorageSync("authorized", true);
                  wx.hideLoading();
                  if (res.data.code == 0) {
                    if (buttonindex == 1) {//关注
                     that.followCard()
                    } else if (buttonindex == 2) {//评论
                      that.addComment(e)
                      
                    } else if (buttonindex == 3){//回复
                    console.log(e)
                      console.log("1111111111111")
                      that.answerQuestion(e)
                    }else{//发送客服消息
                      that.applyCard()
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
    //     } else {//已授权
    //       console.log("22222222222")
    //       if (buttonindex == 1) {//关注
    //         that.followCard()
    //       } else if (buttonindex == 2) {//评论
    //         that.addComment(e)
    //       } else {//回复          
    //         that.answerQuestion(e)
    //       }

    //     }
    //   }
    // })

  },
  /**
    * 页面上拉触底事件的处理函数
    */
  onReachBottom: function () {
    //如果已经是最后一页，则展示已加载完成，几秒后消失
    // console.log("this.data.totalPage=" + this.data.totalPage)
    // console.log("this.data.pageNum2222222222=" + this.data.pageNum)
    
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
  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    return {
      title: this.data.shareTitle,
      path: '/pages/cardDetail/cardDetail?cardId=' + this.data.cardId+"&share=1",
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