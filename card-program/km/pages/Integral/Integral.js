Page({

  /**
   * 页面的初始数据
   */
  data: {
    link:""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

   this.data.link = options.link
    //console.log(this.data.link)
    
    this.setData({
      link: this.data.link
    })
  },

 
})