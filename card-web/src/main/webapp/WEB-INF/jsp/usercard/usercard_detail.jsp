<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="../common/comm_head.jsp"%>
    <base href="<%=basePath%>">
    <!-- 下拉框 -->
    <link rel="stylesheet" href="static/ace/css/chosen.css" />
    <!-- jsp文件头和头部 -->
    <%@ include file="../system/index/top.jsp"%>
    <!-- 日期框 -->
    <link rel="stylesheet" href="static/ace/css/datepicker.css" />
</head>
<body class="no-skin">
<div style="width:100%;height:20px;"></div>
<ul id="myTab" class="nav nav-tabs">
    <li class="active">
        <a href="#jbxx" data-toggle="tab">用户详情</a>
    </li>
</ul>
<div id="myTabContent" class="tab-content">
    <div class="tab-pane fade in active" id="jbxx">
        <!-- /section:basics/navbar.layout -->
        <div class="main-container" id="main-container">
            <!-- /section:basics/sidebar -->
            <div class="main-content">
                <div class="main-content-inner">
                    <div class="page-content">
                        <div class="row">
                            <div class="col-xs-12">
                                <!-- 存放生成的hmlt开头  -->
                                <div class="col-md-6">
                                    <div style="width: 1000px;">
                                        <div class="form-group" style="width: 100%; height: 30px; line-height: 30px;">
                                            <label class="col-sm-2 control-label" style="text-align: left">用户头像：</label>
                                            <div class="col-sm-9">
                                                <img src="${pd.downloand_source_file_url}" style="width: 50px;height: 50px;">
                                            </div>
                                        </div>
                                        <div class="form-group" style="width: 100%; height: 30px; line-height: 30px;">
                                            <label class="col-sm-2 control-label " style="text-align: left;white-space: nowrap;">用户名称：</label>
                                            <div class="col-sm-9" >
                                                ${pd.nick_name}
                                            </div>
                                        </div>
                                        <div class="form-group" style="width: 100%; height: 30px; line-height: 30px;">
                                            <label class="col-sm-2 control-label "  style="text-align: left">注册时间：</label>
                                            <div class="col-sm-9">
                                                ${pd.create_time}
                                            </div>
                                        </div>
                                        <div class="form-group" style="width: 100%; height: 30px; line-height: 30px;">
                                            <label class="col-sm-2 control-label " style="text-align: left;">手机品牌：</label>
                                            <div class="col-sm-9">
                                                ${pd.mobile_brand}
                                            </div>
                                        </div>
                                        <div class="form-group" style="width: 100%; height: 30px;line-height: 30px;">
                                            <label class="col-sm-2 control-label " style="white-space: nowrap;">手机型号：</label>
                                            <div class="col-sm-9">
                                                ${pd.mobile_model}
                                            </div>
                                        </div>
                                        <div class="form-group" style="width: 100%; height: 30px;line-height: 30px;">
                                            <label class="col-sm-2 control-label" style="text-align: left">手机系统：</label>
                                            <div class="col-sm-9">
                                                ${pd.mobile_system}
                                            </div>
                                        </div>
                                        <div class="form-group" style="width: 100%; height: 30px;line-height: 30px;">
                                            <label class="col-sm-2 control-label" style="text-align: left">设备像素比：</label>
                                            <div class="col-sm-9">
                                                ${pd.mobile_dpr_wide}*${pd.mobile_dpr_high}
                                            </div>
                                        </div>
                                        <div class="form-group" style="width: 100%; height: 30px;line-height: 30px;">
                                            <label class="col-sm-2 control-label" style="text-align: left">屏幕宽度：</label>
                                            <div class="col-sm-9">
                                                ${pd.screen_wide}
                                            </div>
                                        </div>
                                        <div class="form-group" style="width: 100%; height: 30px;line-height: 30px;">
                                            <label class="col-sm-2 control-label" style="text-align: left">屏幕高度：</label>
                                            <div class="col-sm-9">
                                                ${pd.screen_high}
                                            </div>
                                        </div>
                                        <div class="form-group" style="width: 100%; height: 30px;line-height: 30px;">
                                            <label class="col-sm-2 control-label" style="text-align: left">可使用窗口宽度：</label>
                                            <div class="col-sm-9">
                                                ${pd.window_use_width}
                                            </div>
                                        </div>
                                        <div class="form-group" style="width: 100%; height: 30px;line-height: 30px;">
                                            <label class="col-sm-2 control-label" style="text-align: left">可使用窗口高度：</label>
                                            <div class="col-sm-9">
                                                ${pd.window_use_high}
                                            </div>
                                        </div>
                                        <div class="form-group" style="width: 100%; height: 30px;line-height: 30px;">
                                            <label class="col-sm-2 control-label" style="text-align: left">状态栏的高度：</label>
                                            <div class="col-sm-9">
                                                ${pd.status_bar_high}
                                            </div>
                                        </div>
                                        <div class="form-group" style="width: 100%; height: 30px;line-height: 30px;">
                                            <label class="col-sm-2 control-label" style="text-align: left">微信设置的语言：</label>
                                            <div class="col-sm-9">
                                                ${pd.wechar_language}
                                            </div>
                                        </div>
                                        <div class="form-group" style="width: 100%; height: 30px;line-height: 30px;">
                                            <label class="col-sm-2 control-label" style="text-align: left">微信版本号：</label>
                                            <div class="col-sm-9">
                                                ${pd.wechar_version}
                                            </div>
                                        </div>
                                        <div class="form-group" style="width: 100%; height: 30px;line-height: 30px;">
                                            <label class="col-sm-2 control-label" style="text-align: left">客户端平台：</label>
                                            <div class="col-sm-9">
                                                ${pd.client_platform}
                                            </div>
                                        </div>
                                        <div class="form-group" style="width: 100%; height: 30px;line-height: 30px;">
                                            <label class="col-sm-2 control-label" style="text-align: left">用户字体大小设置：</label>
                                            <div class="col-sm-9">
                                                ${pd.font_size}
                                            </div>
                                        </div>
                                        <div class="form-group" style="width: 100%; height: 30px;line-height: 30px;">
                                            <label class="col-sm-2 control-label" style="text-align: left">客户端基础库版本：</label>
                                            <div class="col-sm-9">
                                                ${pd.client_platform_version}
                                            </div>
                                        </div>
                                        <div class="form-group" style="width: 100%; height: 30px;line-height: 30px;">
                                            <label class="col-sm-2 control-label" style="text-align: left">性能等级：</label>
                                            <div class="col-sm-9">
                                                ${pd.performance_leval}
                                            </div>
                                        </div>
                                    </div>
                                    <div style="margin-top: 50px;">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 页面底部js¨ -->
    <%@ include file="../system/index/foot.jsp"%>
    <!-- 下拉框 -->
    <script src="static/ace/js/chosen.jquery.js"></script>
    <!-- 日期框 -->
    <script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
    <!--提示框-->
    <script type="text/javascript" src="static/js/jquery.tips.js"></script>
    <script type="text/javascript">
        //下载表格
        function toExcel(id){
            window.location.href='<%=basePath%>sponsor/excel.do?id='+id;
        }
        //生成详情二维码
        function detailSponsor(qr_code_url,game_id){
            top.jzts();
            var diag = new top.Dialog();
            diag.Drag=true;
            diag.URL = "<%=basePath%>sponsor/goDetailSponsor.do?qr_code_url="+qr_code_url +"&id="+game_id;
            diag.Width = 450;
            diag.Height = 355;
            diag.Modal = true;				//有无遮罩窗口
            diag.CancelEvent = function(){ //关闭事件
                if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
                    tosearch();
                }
                diag.close();
            };
            diag.show();
        }
    </script>
</body>
</html>