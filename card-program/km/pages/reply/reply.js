// pages/search/search.js
var app = getApp();
var portDomain = getApp().globalData.portDomain;
Page({
  /**
   * 页面的初始数据
   */
  data: {
    evaContent:'0',
    content:'',
    cardId:"",
    commentId:'',
    toUserName:'',
    replyType: 1,//1为回复评论，2为回复别人的回复 ,
    toUid:0,
    replyId:'',
    isDisable:true,
    color:'0.4'
  },
  onLoad: function (options) {
    
    this.data.commentId = options.commentId
    this.data.replyType = options.replyType
    this.data.toUserName = options.toUserName
    this.data.toUid = options.toUid
    this.data.replyId = options.replyId
    this.data.cardId = options.cardId
   
    wx.setNavigationBarTitle({
      title:  this.data.toUserName,
    })
   
  },

  //评论
  addComment:function(){
    wx.showLoading({
      mask: true
    })
    wx.request({
      url: portDomain + 'comment/addComment',
      data: {
        "businessKey": wx.getStorageSync('businessKey'),
        "content": this.data.content,
        "objectId": this.data.cardId
      },
      method: "post",
      success: res => {
        wx.hideLoading()
        if (res.data.code == 0) {
          // console.log("回复成功")
          wx.showToast({
            icon: "none",
            title: "评论成功"
          })

          setTimeout(function () { wx.navigateBack() }, 500)
        } else {
          wx.showToast({
            title: res.data.showMsg,
            icon: 'none'
          })
          this.setData({
            color: "1",
            isDisable: false
          })
        }
      }
    })
  },


  //回复
  reply:function(){
    wx.showLoading({
      mask: true
    })
    wx.request({
      url: portDomain + 'comment/addCommentReply',
      data: {
        "businessKey": wx.getStorageSync('businessKey'),
        "commentId": this.data.commentId,
        "content": this.data.content,
        "objectId": this.data.cardId,
        "replyId": this.data.replyId,
        "replyType": this.data.replyType,
        "toUid": this.data.toUid
      },
      method: "post",
      success: res => {
        wx.hideLoading()
        if (res.data.code == 0) {
          // console.log("回复成功")
          wx.showToast({
            icon: "none",
            title: "回复成功"
          })

          setTimeout(function () { wx.navigateBack() }, 500)
        } else {
          wx.showToast({
            title: res.data.showMsg,
            icon: 'none'
          })
          this.setData({
            color: "1",
            isDisable: false
          })
        }
      }
    })

  },

  

  listenButton:function(){
    this.setData({
      color: "0.4",
      isDisable: true
    })
    if (this.data.toUserName=="评论"){//评论
      this.addComment()
    }else{//回复
      this.reply()
    }
  },

textBlur:function(e){
  this.data.content = e.detail.value
  if (this.data.content.length > 0){
   this.setData({
     color:"1",
     isDisable: false
   })
  }else{
    this.setData({
      color: "0.4",
      isDisable: true
    })
  }
  this.setData({
    evaContent:e.detail.value.length
  })
},
})