<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<form action="channel/${msg}" class="form-horizontal"  name="Form" id="Form" method="post">
    <input type="hidden" id="id" name="id" value="${pd.id}"/>
    <input type="hidden" id="qr_code_url" name="qr_code_url" value="${pd.qr_code_url}"/>
    <input type="hidden" id="code" name="code" value="${pd.code}"/>
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#jbxx" data-toggle="tab">渠道详细</a>
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
                                        <div style="width: 1200px;height: 340px; border: 1px solid lightgrey;">
                                            <div class="form-group" style="margin-right: 600px;">
                                                <label class="col-sm-3 control-label " style="text-align: left">渠道名称：</label>
                                                <div class="col-sm-9" style="margin-top: 8px;" >
                                                    ${pd.channel_name}
                                                </div>
                                            </div>
                                            <div class="form-group" style="margin-right: 600px;">
                                                <label class="col-sm-3 control-label "  style="text-align: left">联系人：</label>
                                                <div class="col-sm-9" style="margin-top: 8px;">
                                                    ${pd.contacts_name}
                                                </div>
                                            </div>
                                            <div class="form-group" style="margin-right: 600px;">
                                                <label class="col-sm-3 control-label " style="text-align: left">联系电话：</label>
                                                <div class="col-sm-9" style="margin-top: 8px;">
                                                    ${pd.contacts_mobile}
                                                </div>
                                            </div>
                                            <div class="form-group" style="margin-right: 600px;">
                                                <label class="col-sm-3 control-label " style="text-align: left">渠道推广码：</label>
                                                <div class="col-sm-9" style="margin-top: 8px;">
                                                    ${pd.extension_url}
                                                </div>
                                            </div>
                                            <div class="form-group" style="float: right; margin-top: -180px; margin-right: 200px;">
                                                <label class="col-sm-3 control-label " style="white-space: nowrap;text-align: left">小程序码：</label>
                                                <div class="col-sm-9" style="margin-top: 8px;">
                                                    <img id="img_qr_code_url" onclick="PictureBigClick('${pd.qr_code_url}')" src="${pd.qr_code_url}"  style="width: 150px;height: 150px;margin-left: -3px;">
                                                </div>
                                            </div>
                                        </div>
                                        <div>
                                            <span style="color: black">具体数据</span>
                                            <div>
                                                <table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">
                                                    <tr style="white-space: nowrap;">
                                                        <th class="center">渠道名称</th>
                                                        <th class="center">浏览量</th>
                                                        <th class="center">用户数</th>
                                                        <th class="center">信用卡申请</th>
                                                        <th class="center">信用卡绑定</th>
                                                    </tr>
                                                    <tr style="white-space: nowrap;">
                                                        <td class='center'>${pd.channel_name}</td>
                                                        <td class='center'>${pd.data.pv}</td>
                                                        <td class='center'>${pd.data.uv}</td>
                                                        <td class='center'>${pd.data.auv}</td>
                                                        <td class='center'>${pd.data.bauv}</td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<!-- 页面底部js¨ -->
<%@ include file="../system/index/foot.jsp"%>
<!-- 下拉框 -->
<script src="static/ace/js/chosen.jquery.js"></script>
<!-- 日期框 -->
<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
<!--提示框-->
<script type="text/javascript" src="static/js/jquery.tips.js"></script>
<script type="text/javascript">
    $(top.hangge());
    //保存
    function save(){
        if($("#name").val()==""){
            $("#name").tips({
                side:3,
                msg:'请输入渠道名称',
                bg:'#AE81FF',
                time:2
            });
            $("#name").focus();
            return false;
        }
        $("#Form").submit();
        $("#zhongxin").hide();
        $("#zhongxin2").show();
    }

    $(function() {
        //日期框
        $('.date-picker').datepicker({autoclose: true,todayHighlight: true});
        $("#img_qr_code_url").attr("src",'${pd.qr_code_url}');
    });

</script>
</body>
</html>