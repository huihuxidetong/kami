// pages/card/card.js
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
    searchLoading: false, //"上拉加载"的变量，默认false，隐藏
    searchLoadingComplete: false,  //“没有数据”的变量，默认false，隐藏
    totalPage: '',
    pageNum: 1,
    allList:[],
    isCanClickDetail:1
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
    wx.setStorageSync("pageindex", 1);//我的页面返回变量
    this.data.pageNum=1
    this.getCardList()
  },

  /**
   * 信用卡详情
   */
  goCardDetail: function (e) {
    if (this.data.isCanClickDetail == 1) {
      wx.navigateTo({
        url: '../cardDetail/cardDetail?cardId=' + e.currentTarget.dataset.id
      })
    }
  
  },
  goBankList: function () {
    wx.navigateTo({
      url: '../bankList/bankList'
    })
  },
  //获取信用卡列表
  getCardList: function () {
    wx.request({
      url: portDomain + 'creditCard/findCreditCardList',
      method: 'POST',
      data: {
        "pageNum": this.data.pageNum,
        "pageSize": 20
      },
      success: res => {
        if(res.data.code ==0){
          var allList = res.data.data.creditCardInfoHotVoPageList;
          this.data.isCanClickDetail = res.data.data.isCanClickDetail
          let contact = [];
          if (this.data.pageNum > 1) {
            contact = this.data.allList || [];
          }
          if (allList != null) {
            contact = contact.concat(allList);
          }
         // contact = contact.concat(allList);
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

          var bankList = res.data.data.bankVoLiist
          var bankArray = []
          if (bankList.length>4){
            bankArray.push(bankList[0])
            bankArray.push(bankList[1])
            bankArray.push(bankList[2])
            bankArray.push(bankList[3])   
          }else{
            bankArray = bankList
          }
          this.setData({
            bankList: bankArray,
            hotList: res.data.data.creditCardInfoHotVoList,
            allList: contact,
            searchLoading: false,
            totalPage: res.data.data.totalPage,
          });
        }else{
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
  //去银行列表
  goCardList: function (e) {
    if (this.data.isCanClickDetail==1){
      wx.navigateTo({
        url: '../bankCardList/bankCardList?bankId=' + e.currentTarget.dataset.id
      })
    }
    
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
      that.getCardList();
    }
  },
  //下拉刷新
  onPullDownRefresh: function () {
    wx.showNavigationBarLoading() //在标题栏中显示加载
    this.setData({
      pageNum: 1
    })
    this.getCardList();
  },
  /**
 * 用户点击右上角分享
 */
  onShareAppMessage: function () {
    return {
      title: '信用卡申请',
      path: '/pages/card/card',
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