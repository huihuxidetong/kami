//app.js
App({
  globalData: {
    //portDomain: 'http://10.7.24.220:8080/api/',
    portDomain: 'https://api2.kamifinance.com/',
    //portDomain: 'http://192.168.1.85:8086/',
    imgDomain: 'https://file.kamifinance.com/file/down/',
    //imgDomain: 'http://10.7.24.220:8080/file/file/down/',
    uploadImg: 'https://file.kamifinance.com/file/upload/file/',
    //uploadImg: 'http://10.7.24.220:8080/file/file/upload/file/',
  },
  onLaunch: function () {

    const _this = this
    var showToast = wx.getStorageSync('showToast');
    if (showToast == '') {
      wx.setStorageSync('showToast', false);
    }
    this.getAuthKey().then(function (res) {
      console.log("businessKey初始化");
    })
  },
  getAuthKey: function () {
    const _this = this;
    return new Promise(function (resolve, reject) {
      var value = wx.getStorageSync('businessKey');
      wx.request({
        url: _this.globalData.portDomain + 'common/checkBusinessKey',
        method: 'POST',
        data: {
          "businessKey": value
        },
        success: res => {
          if (res.data.code == null || res.data.code != 0) {
            wx.login({
              success: function (res) {
                //重新获取session
                wx.request({
                  url: _this.globalData.portDomain + 'common/createSssion',
                //  method: 'POST',
                  data: {
                    wxCode: res.code,
                    //applicationKey: "CARD",
                  },
                  success: function (res) {
                    console.log(res)
                    if (res.data.code == 0) {
                      console.log(res.data.data.businessKey)
                      wx.setStorageSync("businessKey", res.data.data.businessKey);
                    }
                    var res = {
                      status: 200,
                      data: res.data.data
                    }
                    resolve(res);
                  }
                });
              },
              fail: function () {
                // session_key 已经失效，需要重新执行登录流程
                wx.login({
                  success: function (res) {
                   // method: 'POST',
                    //重新获取session
                    wx.request({
                      url: _this.globalData.portDomain + '/common/createSssion',
                      data: {
                        wxCode: res.code,
                      //  applicationKey: "CARD",
                      },
                      success: function (res) {
                        console.log(res)
                        if (res.data.data.code == 0) {
                          console.log(res.data.data.businessKey)
                          wx.setStorageSync("businessKey", res.data.data.businessKey);
                        }
                        var res = {
                          status: 200,
                          data: res.data.data
                        }
                        resolve(res);
                      }
                    });
                  }
                });
              }
            });
          } else {
            var res = {
              status: 200,
              data: value
            }
            resolve(res);
          }
        }
      })
    })
  },



})