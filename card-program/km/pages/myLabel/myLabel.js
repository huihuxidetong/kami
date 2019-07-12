const app = getApp()
var portDomain = getApp().globalData.portDomain;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    label: [],
    allLabel: [],
    isshowadd:true,
    myLable:"",
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getmyLable()
  },

  //获取我的标签
  getmyLable: function () {
    wx.request({
      url: portDomain + 'users/findMyLableList',
      method: 'POST',
      data: {
        "businessKey": wx.getStorageSync('businessKey'),
      },
      success: res => {
        if (res.data.code == 0) {
          var label = ""
          var labelList = res.data.data.userChooseLable;
          this.data.label = res.data.data.userChooseLable;
          this.data.allLabel = res.data.data.userCustomLableVoList;
          this.setData({
            label: this.data.label,
            allLabel: this.data.allLabel
          })
        } else {
          wx.showToast({
            title: res.data.showMsg,
            icon: "none"
          })
        }
      }
    })
  },


  chooseLable: function (e) {
    var index = e.currentTarget.dataset.index
     if(e.currentTarget.dataset.ischoose ==0){//未选中
       if (this.data.label.length < 5) {
         this.addLable(e,0)
     
         
         
       } else {
         wx.showToast({
           title: "最多添加5个标签",
           icon: "none"
         })
       }
     } else {//已选中,取消选中 
       this.deleteLable(e,2)  
      }  
  },

  chooseCancle:function(e){
    this.deleteLable(e,1)   
  },

  //删除用户标签
  deleteLable:function(e,diff){
    wx.showLoading({
      mask: true
    })
    wx.request({
      url: portDomain + 'users/delUserChooseLable',
      method: 'POST',
      data: {
        "businessKey": wx.getStorageSync('businessKey'),
        "customLable": e.currentTarget.dataset.content
      },
      success: res => {
        wx.hideLoading();
        if (res.data.code == 0) {
          if (diff==1){ //点击上面取消选中
            var allindex = e.currentTarget.dataset.allindex
            var myindex = e.currentTarget.dataset.myindex
            this.data.label.splice(myindex, 1)
            this.data.allLabel[allindex].isChooseLable = 0
            this.setData({
              label: this.data.label,
              allLabel: this.data.allLabel
            })
          }else{ //点击下面取消选中
            var index = e.currentTarget.dataset.index
            this.data.allLabel[index].isChooseLable = 0
            var chooseIndex = 0
            for (var i = 0; i < this.data.label.length; i++) {
              if (this.data.label[i].userCustomLableName == e.currentTarget.dataset.content) {
                chooseIndex = i
                break;
              }
            }
            this.data.label.splice(chooseIndex, 1)
            this.setData({
              label: this.data.label,
              allLabel: this.data.allLabel
            })
          }

        } else {
          wx.showToast({
            title: res.data.showMsg,
            icon: "none"
          })
   
        }
      }
    })
   
  },

  //添加用户标签
  addLable: function (e, isNewLable){
    wx.showLoading({
      mask: true
    })
    var content = ""
    if (isNewLable==0){
      content = e.currentTarget.dataset.content
    }else{
      content = e
    }
    wx.request({
      url: portDomain + 'users/addCustomLable',
      method: 'POST',
      data: {
        "businessKey": wx.getStorageSync('businessKey'),
        "customLable": content,
        "isNewLable": isNewLable
      },
      success: res => {
        wx.hideLoading();
        if (res.data.code == 0) {
          if (isNewLable==0){//添加已有标签
            var index = e.currentTarget.dataset.index
            var obj = {}
            obj.isChooseLable = 1
            obj.userCustomLableName = e.currentTarget.dataset.content
            obj.index = e.currentTarget.dataset.index
            this.data.label = this.data.label.concat(obj);

            this.data.allLabel[index].isChooseLable = 1
            this.setData({
              label: this.data.label,
              allLabel: this.data.allLabel
            })

          } else {//添加自定义标签
            var obj = {}
            obj.isChooseLable = 1
            obj.userCustomLableName = this.data.myLable
            obj.index = this.data.allLabel.length
            this.data.label = this.data.label.concat(obj);
            this.data.allLabel = this.data.allLabel.concat(obj);
            this.data.myLable = ""
            this.setData({
              label: this.data.label,
              allLabel: this.data.allLabel,
              labelContent:""
            })

          }
   
        } else {
          wx.showToast({
            title: res.data.showMsg,
            icon: "none"
          })
         
        }
      }
    })
  },

  

  /**
   * 显示添加标签对话框
   */
  showModal: function () {
    if (this.data.label.length < 5){
      this.setData({
        isshowadd: false
      })
    }else{
      wx.showToast({
        title: "最多添加5个标签",
        icon: "none"
      })
    }
   
  },
  /**
     * 隐藏添加标签对话框
     */
  hideModal: function () {
    this.setData({
      isshowadd: true
    })
  },

  //输入自定义标签
  inputmylable:function(e){
    this.data.myLable =e.detail.value
  },
  /**
     * 添加自定义标签
     */
  addmyLabel: function () {
     
    if(this.data.myLable!=""){
      this.setData({
        isshowadd: true
      })
      this.addLable(this.data.myLable,1)
    
    }else{
      wx.showToast({
        title: '请输入标签名称',
        icon: "none"
      })
    }
    
  },
  

 
})