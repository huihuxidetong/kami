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
	<script type="text/javascript" src="<%=basePath%>/static/My97DatePicker/WdatePicker.js"></script>
	<!-- 日期框 -->
	<link rel="stylesheet" href="static/ace/css/datepicker.css" />
	<script type="text/javascript" src="static/wangEditor/wangEditor.js"></script>
</head>
<body class="no-skin">
<div style="width:100%;height:20px;"></div>
<form action="bank/upload.do" id="fileForm" enctype="multipart/form-data">
	<input type="file" name="file" id="file" style="display: none"/>
</form>
<form action="bank/${msg}" class="form-horizontal"  name="Form" id="Form" method="post">
	<input name="edithtml" id="edithtml" type="hidden"/>
	<input name="sourceFilePath" id="sourceFilePath" value="${pd.sourceFilePath}" type="hidden"/>
	<input name="id" id="id" type="hidden" value="${pd.id}">
	<ul id="myTab" class="nav nav-tabs">
		<li class="active">
			<a href="#jbxx" data-toggle="tab">添加银行</a>
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
											<label class="col-sm-3 control-label "  style="text-align: left">银行logo<span style="color: red">*</span>：</label>
											<div class="col-sm-9" >
													<img src="" id="fileUrl" title="点击上传" style="width:100px;height:100px;cursor: pointer"/>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label "  style="text-align: left">银行名称<span style="color: red">*</span>：</label>
											<div class="col-sm-9">
												<input id="bank_name" name="bank_name"  STYLE="width: 300px" maxlength="10" class="col-xs-10 col-sm-7"  type="text" value="${pd.bank_name}">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label "  style="text-align: left">联系人姓名：</label>
											<div class="col-sm-9" >
												<input id="bank_contacts_name" name="bank_contacts_name" maxlength="10" STYLE="width: 300px" class="col-xs-10 col-sm-7" type="text" value="${pd.bank_contacts_name}">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label "  style="text-align: left">联系人电话：</label>
											<div class="col-sm-9" >
												<input id="bank_contacts_tel" name="bank_contacts_tel"maxlength="11"   STYLE="width: 300px" class="col-xs-10 col-sm-7" type="text" value="${pd.bank_contacts_tel}">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label "  style="text-align: left">银行简介：</label>
											<div class="col-sm-9" style="width:350px;">
												<textarea  style="width: 350px;height: 250px" id="bank_contacts_notic" name="bank_contacts_notic"  placeholder="最多可输⼊200字" rows="2"  maxlength="200" onkeyup="checkNumBank()">${pd.bank_contacts_notic}</textarea>
												<span id="changeNumBank">0</span>
												<span>/</span>
												<span>200</span>
											</div>
										</div>
										<div class="form-group" style="margin-left: 200px">
											<div class="col-sm-9" >
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
<script src="static/ace/js/chosen.jquery.js"></script>
<!-- 日期框 -->
<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
<!--提示框-->
<script type="text/javascript" src="static/js/jquery.form.js"></script>
<script type="text/javascript" src="static/js/jquery.tips.js"></script>
<script type="text/javascript">
    var showCount = "${pd.showCount}";
    var currentPage ="${pd.currentPage}";
    var editor;
    $(top.hangge());
    var downloand_source_file_url = "${pd.downloand_source_file_url}";
    $("#fileUrl").attr("src",downloand_source_file_url);
    $(function() {
        var E = window.wangEditor;
        //这里的id为<div id="editor"中的id.
        editor = new E('#editor');
        // 配置服务器端地址,也就是controller的请求路径，不带项目路径，前面没有/
        editor.customConfig.uploadImgServer = 'bank/upload.do';
        //配置属性名称，绑定请求的图片数据
        //controller会用到，可以随便设置，但是一定要与controller一致
        editor.customConfig.uploadFileName = 'file';
        //创建编辑器
        editor.create();
        //设置默认日期开始
        setdate();
        //设置默认日期开始
        $("#fileUrl").click(function () {
            $('input[type="file"]').click();
            $('#fileForm').ajaxForm({
                dataType: 'json',
                type: 'post',
                success: function (data) {
                    $("#fileUrl").attr("src", data.downloandSourceFileUrl);
                    $("#sourceFilePath").val(data.sourceFilePath);
                }
            })
        })
        $(document).on('change', '#file', function () {
            $('#fileForm').submit();
        });

    });

    //保存
    function save(){
        if(validateForm()) {
            var url = '<%=basePath%>'+$("#Form").attr("action")
            $.ajax({
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
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
                        window.location.href= "<%=basePath%>bank/list.do?currentPage=" + currentPage + "&showCount=" + showCount;
                    }
                },
                error : function() {
                    bootbox.alert("异常！");
                }
            });
       }
    }
    //取消
    function cancel() {
        if(null == currentPage || "" == currentPage){
            currentPage = 1;
        }
        if(null == showCount || "" == showCount){
            showCount = 10;
        }
        window.location.href= "<%=basePath%>bank/list.do?currentPage=" + currentPage + "&showCount=" + showCount;
    }
    //日期框添加默认值
    function  setdate(){
        //设置结束日期为当前日期
        var date = new Date();
        var seperator1 = "-";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if(month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if(strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var end = date.getFullYear() + seperator1 + month + seperator1 + strDate +" " + date.getHours() + seperator2 + date.getMinutes() +
            seperator2 + date.getSeconds();
        $("#EndTime").val(end);

        //设置开始日期为当前日期的前一个月
        date.setMonth(date.getMonth()+1);
      /*  var ending_time = date.getFullYear() + "-" + date.getMonth() + "-" + (date.getDate() +1 )+
            " " + date.getHours() + seperator2 + date.getMinutes();
        $("#ending_time").val(ending_time);*/
    }

    function checkNumBank() {
        var str = $("#bank_contacts_notic").val();
        var len = 0;
        for (var i=0; i<str.length; i++) {
            // var c = str.charCodeAt(i);
            //单字节加1
            // if ((c >= 0x0001 && c <= 0x007e) || (0xff60 <= c && c <= 0xff9f)) {
            len++;
            // /
        }
        $("#changeNumBank").html(len);
    }

    //银行校验
    function validateForm() {
       /* var str = $("#bank_logo").val();
        if (str == "") {
            $("#bank_logo").tips({
                side: 3,
                msg: '上传一张图片',
                bg: '#AE81FF',
                time: 2
            });
            $("#bank_logo").focus();
            return false;
        } else {
            if (!validateLength(str, 40)) {
                $("#bank_logo").tips({
                    side: 3,
                    msg: '请上传银行logo图片',
                    bg: '#AE81FF',
                    time: 2
                });
                $("#bank_logo ").focus();
                return false;
            }
        }*/
        var str = $("#bank_name").val();
        if (str == "") {
            $("#bank_name").tips({
                side: 3,
                msg: '银行名称是必填项',
                bg: '#AE81FF',
                time: 2
            });
            $("#bank_name").focus();
            return false;
        } else {
            if (!validateLength(str, 20)) {
                $("#bank_name").tips({
                    side: 3,
                    msg: '请填写10个字以内的银行名称',
                    bg: '#AE81FF',
                    time: 2
                });
                $("#bank_name ").focus();
                return false;
            }
        }
        var str = $("#bank_contacts_name").val();
        if (str == "") {
            $("#bank_contacts_name").tips({
                side: 3,
                msg: '请填写10个字以内的联系人姓名',
                bg: '#AE81FF',
                time: 2
            });
            $("#bank_contacts_name").focus();
            return false;
        } else {
            if (!validateLength(str, 20)) {
                $("#bank_contacts_name").tips({
                    side: 3,
                    msg: '请填写10个字以内的联系人姓名',
                    bg: '#AE81FF',
                    time: 2
                });
                $("#bank_contacts_name ").focus();
                return false;
            }
        }
        var str = $("#bank_contacts_tel").val();
        if (str == "") {
            $("#bank_contacts_tel").tips({
                side: 3,
                msg: '填写11位正确手机号码',
                bg: '#AE81FF',
                time: 2
            });
            $("#bank_contacts_tel").focus();
            return false;
        } else {
            if (!validateLength(str, 40)) {
                $("#bank_contacts_tel").tips({
                    side: 3,
                    msg: '请填写11位正确手机号码',
                    bg: '#AE81FF',
                    time: 2
                });
                $("#bank_contacts_tel ").focus();
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