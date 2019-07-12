// pages/game/game.js
const app = getApp()
var portDomain = getApp().globalData.portDomain;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userName: "",
    cardNo:"",
    bankName:"",
    bankId:'',
    cardCategory:"",
    cardId:"",
    isChoose:false,
    banklist:[],
    bankIndex:0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getBankList()
  },

  onShow: function (options) {
    let pages = getCurrentPages();
    let currPage = pages[pages.length - 1];
    if (currPage.data.cardCategory != "") {
      this.data.cardCategory = currPage.data.cardCategory,
      this.data.cardId = currPage.data.cardId
    }
  },
  /**
   * 获取银行卡列表
   */
  getBankList: function () {
    wx.request({
      url: portDomain + 'creditCard/findBankList',
      method: 'POST',
      data: {},
      success: res => {
        if (res.data.code == 0){
          this.setData({
            banklist: res.data.data.bankVoList
          })
        }else{
          wx.showToast({
            title: res.data.showMsg,
            duration: 1000,
            icon: "none"
          })
        }

      }
    })
  },

  bankChange:function(e){
    this.data.bankIndex = e.detail.value   
  },



  //选择发卡银行
  choosebankName: function () {
    this.setData({
      isChoose:true
    })
   
  },

  //取消选择
  chooseCancle: function () {
    this.setData({
      isChoose: false 
    })
  },
  //确定选择
  chooseConfirm: function () {
    this.setData({
      isChoose: false
    })
     this.data.bankName = this.data.banklist[this.data.bankIndex].bankName

     //this.data.bankName = "广发银行"
     this.setData({
       bankName: this.data.bankName
     })

     this.data.bankId = this.data.banklist[this.data.bankIndex].id   
  },

  //选择信用卡类别
  choosecardCategory:function(){
    if(this.data.bankId==""){
      wx.showToast({
        title: "请先选择发卡银行",
        icon: "none"
      })
    }else{
      wx.navigateTo({
        url: '../cardCategory/cardCategory?bankId=' + this.data.bankId,
      })
    }
   

  },
  //输入持卡人姓名
  iputname:function(e){
    this.data.userName = e.detail.value
  },
  //输入卡号
  iputcard: function (e) {
    this.data.cardNo = e.detail.value
  },

  //点击下一步
  gonext:function(){
    var content = this.verifyContent()
    if (content==""){
      //this.saveInfo()
      wx.navigateTo({
        url: '../confirmCard/confirmCard?cardName=' + this.data.bankName + this.data.cardCategory + "&cardNo=" + this.data.cardNo + "&bankId=" + this.data.bankId + "&cardId=" + this.data.cardId + "&userName=" + this.data.userName,
      })
    }else{
      wx.showToast({
        title: content,
        icon: "none"
      })
    }
   
  },

  //保存卡片信息
  // saveInfo:function(){
  //   wx.showLoading({
  //     mask: true
  //   })
  //   wx.request({
  //     url: portDomain + 'users/addMyCreditCard',
  //     method: 'POST',
  //     data: {
  //       "businessKey": wx.getStorageSync('businessKey'),
  //       "cardIssuingBank": this.data.bankId,
  //       "cardIssuingBankType": this.data.cardId,
  //       "creditCardHolder": this.data.userName,
  //       "creditCardNumber": this.data.cardNo
  //     },
  //     success: res => {
  //       wx.hideLoading()
  //       if (res.data.code == 0) {
  //         wx.showToast({
  //           title: "参与成功",
  //           duration: 1000,
  //           icon: "none"
  //         })
  //         wx.navigateBack()
  //       } else {
  //         wx.showToast({
  //           title: res.data.showMsg,
  //           duration: 1000,
  //           icon: "none"
  //         })
  //       }
  //     },
  //     fail: res => {
  //       wx.hideLoading()
  //     }
  //   })
  // },

  //输入验证
  verifyContent: function () {
    var toastContent = '';
    if (this.data.userName == "") {
      toastContent = '请填写10个字以内的参与人姓名'
      return toastContent;
    }
    if (this.data.cardNo == "" || this.data.cardNo.length < 13) {
      toastContent = '请填写13-19位之间的信用卡号'
      return toastContent;
    }

    if (this.data.bankName == '') {
      toastContent = '请选择发卡银行'
      return toastContent;
    }

    if (this.data.cardCategory == '') {
      toastContent = '请选择信用卡类型'
    }

    
    return toastContent;
  },
})