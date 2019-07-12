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
<form action="/channel/${msg}.do" class="form-horizontal"  name="Form" id="Form" method="post">
	<input type="hidden" id="id" name="id" value="${pd.id}"/>
	<input type="hidden" id="qr_code_url" name="qr_code_url" value="${pd.qr_code_url}"/>
	<input type="hidden" id="code" name="code" value="${pd.code}"/>
	<ul id="myTab" class="nav nav-tabs">
		<li class="active">
			<a href="#jbxx" data-toggle="tab">添加渠道</a>
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
										<div class="form-group">
											<label class="col-sm-3 control-label "  style="text-align: left">渠道名称：</label>
											<div class="col-sm-9" >
												<input id="channel_name" name="channel_name" onblur="validateName()"  STYLE="width: 300px" class="span8" type="text" value="${pd.channel_name}">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label "  style="text-align: left">联系人名称：</label>
											<div class="col-sm-9" >
												<input id="contacts_name" name="contacts_name"  STYLE="width: 300px" class="col-xs-10 col-sm-7"  type="text" value="${pd.contacts_name}">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label "  style="text-align: left">联系人电话：</label>
											<div class="col-sm-9" >
												<input id="contacts_mobile" name="contacts_mobile"  STYLE="width: 300px" class="col-xs-10 col-sm-7" type="text" value="${pd.contacts_mobile}">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label"  style="text-align: left">推广码：</label>
											<div class="col-sm-9">
												<a style="float:left;margin-left: 10px;" href="javascript:void(0)" class="btn btn-mini btn-primary" onclick="createQrCode();">生成渠道码</a>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label "  style="text-align: left">渠道链接：</label>
											<div class="col-sm-9">
												<input id="extension_url" name="extension_url"  readonly="readonly" STYLE="width: 300px" class="col-xs-10 col-sm-7" type="text" value="${pd.extension_url}">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label "  style="text-align: left">小程序码：</label>
											<div class="col-sm-9" style="width:350px;">
												<img id="img_qr_code_url" src=""/>
											</div>
										</div>
										<div class="form-group" style="margin-left: 200px">
											<div class="col-sm-9">
												<a style="float:left;margin-left: 10px;" href="javascript:void(0)" id="save" class="btn btn-mini btn-success" onclick="save();">保存</a>
												<a style="float:left;margin-left: 10px;" href="javascript:void(0)" class="btn btn-mini btn-danger" onclick="cancel()">取消</a>
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
<!-- 删除时确认窗口 -->
<script src="static/ace/js/bootbox.js"></script>
<script src="static/ace/js/chosen.jquery.js"></script>
<!-- 日期框 -->
<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
<!--提示框-->
<script type="text/javascript" src="static/js/jquery.tips.js"></script>
<script type="text/javascript">
    var showCount = "${pd.showCount}";
    var currentPage ="${pd.currentPage}";
    $(top.hangge());
    //保存
    function save(){
        if( validateForm()){
            var url = '<%=basePath%>'+$("#Form").attr("action")
            $.ajax({
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的类型
                url:url,//url
                data:$('#Form').serialize(),
                success: function (result) {
                    if (result.resultCode == 200) {
                        if(null == currentPage || "" == currentPage){
                            currentPage = 1;
                        }
                        if(null == showCount || "" == showCount){
                            showCount = 10;
                        }
                        window.location.href= "<%=basePath%>channel/list.do?currentPage=" + currentPage + "&showCount=" + showCount;
                    }
                },
                error : function() {
                    bootbox.alert("异常！");
                }
            });
        }
    }
    //判断渠道名称不能重复
    function validateName() {
        $("#extension_url").val("");
        $("#img_qr_code_url").attr("src","");
        $("#img_qr_code_url").attr("style","")
        var channel_name = $("#channel_name").val();
        $.ajax({
            type: "post",
            url:"<%=basePath%>channel/change",
            data:{"channel_name":channel_name},
            dataType: "json",
            success:function (data) {
                if (data) {
                    $("#channel_name").tips({
                        side:3,
                        msg:'渠道名称已存在',
                        bg:'#AE81FF',
                        time:2
                    });
                    $("#channel_name").focus();
                    $("#save").attr('disabled',true);
                    return false;
                } else {
                    $("#save").attr('disabled',false);
                    return true;
                }
            }
        })
    }
    $(function() {
        //日期框
        $('.date-picker').datepicker({autoclose: true,todayHighlight: true});
        var str = '${pd.qr_code_url}';
        if(null != str && ""!= str ){
            $("#img_qr_code_url").attr("style","width: 160px;height: 160px;margin-left: -10px;")
            $("#img_qr_code_url").attr("src",'${pd.qr_code_url}');
        }
    });
    //取消
    function cancel() {
        if(null == currentPage || "" == currentPage){
            currentPage = 1;
        }
        if(null == showCount || "" == showCount){
            showCount = 10;
        }
        window.location.href= "<%=basePath%>channel/list.do?currentPage=" + currentPage + "&showCount=" + showCount;
    }
    function createQrCode(){
        var channel_name = $("#channel_name").val();
        if(channel_name==""){
            $("#channel_name").tips({
                side:3,
                msg:'请输入渠道名称',
                bg:'#AE81FF',
                time:2
            });
            $("#channel_name").focus();
            return false;
        }
        var channel_name = $("#channel_name").val();
        $.ajax({
            type: "post",
            url:"<%=basePath%>channel/change",
            data:{"channel_name":channel_name},
            dataType: "json",
            success:function (data) {
                if (data) {
                    $("#channel_name").tips({
                        side:3,
                        msg:'渠道名称已存在',
                        bg:'#AE81FF',
                        time:2
                    });
                    $("#channel_name").focus();
                    return false;
                } else {
                    $.ajax({
                        type: "POST",//方法类型
                        dataType: "json",//预期服务器返回的数据类型
                        url:"<%=basePath%>channel/getQrCodeUrl.do",//url
                        data:{"channel_name":channel_name},
                        success: function (result) {
                            if(result.resultCode == 200){
                                $("#img_qr_code_url").attr("style","width: 160px;height: 160px;margin-left: -10px;")
                                $("#extension_url").val(result.pd.extension_url);
                                $("#img_qr_code_url").attr("src",result.pd.qr_code_url);
                                $("#qr_code_url").val(result.pd.qr_code_url);
                                //$("#embed_code").val(result.pd.extension_url);
                                $("#code").val(result.pd.code);
                            }
                        },
                        error : function() {
                            bootbox.alert("异常！");
                        }
                    });
                }
            }
        })
    }
    //渠道校验
    function validateForm() {
        var str = $("#channel_name").val();
        if (str == "") {
            $("#channel_name").tips({
                side: 3,
                msg: '请输入渠道名称',
                bg: '#AE81FF',
                time: 2
            });
            $("#channel_name").focus();
            return false;
        } else {
            if (!validateLength(str, 40)) {
                $("#channel_name").tips({
                    side: 3,
                    msg: '最多可输?20字',
                    bg: '#AE81FF',
                    time: 2
                });
                $("#channel_name ").focus();
                return false;
            }
        }
        var str = $("#contacts_name").val();
        if (str == "") {
            $("#contacts_name").tips({
                side: 3,
                msg: '请输入联系人名称',
                bg: '#AE81FF',
                time: 2
            });
            $("#contacts_name").focus();
            return false;
        } else {
            if (!validateLength(str, 40)) {
                $("#contacts_name").tips({
                    side: 3,
                    msg: '最多可输?20字',
                    bg: '#AE81FF',
                    time: 2
                });
                $("#contacts_name ").focus();
                return false;
            }
        }
        var str = $("#contacts_mobile").val();
        if (str == "") {
            $("#contacts_mobile").tips({
                side: 3,
                msg: '请输入联系人电话',
                bg: '#AE81FF',
                time: 2
            });
            $("#contacts_mobile").focus();
            return false;
        } else {
            if (!validateLength(str, 40)) {
                $("#contacts_mobile").tips({
                    side: 3,
                    msg: '最多可输?20字',
                    bg: '#AE81FF',
                    time: 2
                });
                $("#contacts_mobile ").focus();
                return false;
            }
        }
        return true;
    }
    //验证字符
    function validateLength(str,commonLength){
        var len = 0;
        for (var i=0; i<str.length; i++) {
            var c = str.charCodeAt(i);
            //单字节加1
            if ((c >= 0x0001 && c <= 0x007e) || (0xff60 <= c && c <= 0xff9f)) {
                len++;
            }
            else {
                len += 2;
            }
        }
        if(commonLength >= len){
            return true;
        }
        return false;
    }
</script>
</body>
</html>