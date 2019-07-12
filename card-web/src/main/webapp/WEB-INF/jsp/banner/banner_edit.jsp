<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
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
	<script type="text/javascript" src="<%=basePath%>/static/My97DatePicker/WdatePicker.js"></script>
	<!-- 日期框 -->
	<link rel="stylesheet" href="static/ace/css/datepicker.css" />
	<script type="text/javascript" src="static/wangEditor/wangEditor.js"></script>
</head>
<body style="background-color: #fff">
<div style="margin-top: 20px;width: 100%;"></div>
<form action="banner/upload.do" id="fileForm" enctype="multipart/form-data">
	<input type="file" name="file" id="file" style="display: none"/>
</form>

<form action="banner/${msg}.do" class="form-horizontal" name="Form" id="Form" method="post">
	<input name="id" id="id" value="${pd.id}" type="hidden">
	<input name="sourceFilePath" id="sourceFilePath" value="${pd.sourceFilePath}" type="hidden"/>
	<ul id="myTab" class="nav nav-tabs">
		<li class="active">
			<a href="#jbxx" data-toggle="tab">添加Banner</a>
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
											<label class="col-sm-3 control-label" style="text-align: left">排序：</label>
											<div class="col-sm-9">
												<input name="sort" id="sort" onblur="validateSort()" STYLE="width: 360px" minlength="100" onkeyup="value=value.replace( /[^\d]/g,'')"  class="col-xs-10 col-sm-7" type="text" value="${pd.sort}"/>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label" style="text-align: left">Banner名称：</label>
											<div class="col-sm-9">
												<input name="banner_name" id="banner_name" STYLE="width: 360px" minlength="20" class="col-xs-10 col-sm-7" type="text" value="${pd.banner_name}"/>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label" style="text-align: left">Banner图片：</label>
											<div class="col-sm-9">
												<img src="" id="fileUrl" title="点击上传" style="width:100px;height:100px;cursor: pointer"/>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label " style="text-align: left">类型：</label>
											<div class="col-sm-9">
												<input type="radio" id="banner_type" name="banner_type"  value="1" <c:if test="${pd.banner_type == 1   || null == pd.banner_type}"> checked="checked" </c:if> onclick=""/>路径
												<input type="radio" name="banner_type" value="2" <c:if test="${pd.banner_type == 2 }"> checked="checked" </c:if>/>链接
											</div>
										</div>
										<div class="form-group" id="banner_route_div" style="display: none">
											<label class="col-sm-3 control-label" style="text-align: left">Banner路径：</label>
											<div class="col-sm-9">
												<input name="banner_route" id="banner_route" STYLE="width: 360px " class="col-xs-10 col-sm-7" minlength="200" type="text" value="<c:if test="${pd.banner_type == 1}">${pd.banner_url}</c:if>"/>
											</div>
										</div>
										<div class="form-group" id="banner_like_div" style="display: none">
											<label class="col-sm-3 control-label" style="text-align: left">Banner链接：</label>
											<div class="col-sm-9">
												<input name="banner_like" id="banner_like" STYLE="width: 360px" class="col-xs-10 col-sm-7" minlength="200" type="text" value="<c:if test="${pd.banner_type == 2}">${pd.banner_url}</c:if>"/>
											</div>
										</div>
										<div style="text-align: center;">
											<a style="margin-bottom: -10px;" href="javascript:void(0)" class="btn btn-mini btn-primary" onclick="cancel();">取消</a>
											<a style="margin-bottom: -10px;" href="javascript:void(0)" class="btn btn-mini btn-success" onclick="save();">保存</a>
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
<!-- /.main-container -->

<!-- basic scripts -->
<%@ include file="../system/index/foot.jsp"%>
<!-- 删除时确认窗口 -->
<script src="static/ace/js/bootbox.js"></script>
<!-- ace scripts -->
<script src="static/ace/js/ace/ace.js"></script>
<!-- 下拉框 -->
<script src="static/ace/js/chosen.jquery.js"></script>
<!-- 日期框 -->
<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="static/js/jquery.tips.js"></script>
<script type="text/javascript" src="static/js/jquery.form.js"></script>
<script type="text/javascript" src="static/js/jquery.cookie.js"></script>
<script type="text/javascript">
    var showCount = "${pd.showCount}";
    var currentPage ="${pd.currentPage}";
    var editor;
    $(top.hangge());//关闭加载状态
    var downloand_source_file_url = "${pd.downloand_source_file_url}";
    $("#fileUrl").attr("src",downloand_source_file_url);
	var banner_type = "${pd.banner_type}";
	if (1== banner_type || null == banner_type || "" == banner_type){
		$("#banner_route_div").css("display","block");
        $("#banner_like_div").css("display","none");
	}else if(2== banner_type){
        $("#banner_rout_dive").css("display","none");
        $("#banner_like_div").css("display","block");
	}

    $(function() {
        //设置默认日期开始
        setdate();
        //设置默认日期开始
        checkNum();
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
    //校验sort不能重复
	function validateSort() {
		var sort=$("#sort").val();
		$.ajax({
			url:"<%=basePath%>banner/change",
			type:"post",
			data:{"sort":sort},
			success:function (data) {
				if (data) {
				    $("#sort").tips({
						side:3,
						msg:"不能输入相同的值",
                        bg:"#AE81FF",
                        time:2,
					});
				    $("#sort").focus();
				    return false;
				}else {
				    return true;
				}
            }
		});
    }
    //取消
    function cancel(){
        if(null == currentPage || "" == currentPage){
            currentPage = 1;
        }
        if(null == showCount || "" == showCount){
            showCount = 10;
        }
        window.location.href= "<%=basePath%>banner/list.do?&currentPage=" + currentPage + "&showCount=" + showCount;
    }
    //保存
    function save(){
        if(validateForm()) {
            var url = '<%=basePath%>'+$("#Form").attr("action")
            $.ajax({
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: url,//url
                data:$('#Form').serialize(),
                success: function (result) {
                    if (result.resultCode == 200) {
                        if(null == currentPage || "" == currentPage){
                            currentPage = 1;
                        }
                        if(null == showCount || "" == showCount){
                            showCount = 10;
                        }
                        window.location.href= '<%=basePath%>banner/list.do?currentPage=' + currentPage + "&showCount=" + showCount;
                    }
                },
                error : function() {
                    bootbox.alert("异常！");
                }
            });
        }
    }

    //Banner校验
    function validateForm() {
        var str = $("#sort").val();
        if (str == "") {
            $("#sort").tips({
                side: 3,
                msg: '请输入顺序',
                bg: '#AE81FF',
                time: 2
            });
            $("#sort").focus();
            return false;
        } else {
            if (!validateLength(str, 40)) {
                $("#sort").tips({
                    side: 3,
                    msg: '请输入阿拉伯数字',
                    bg: '#AE81FF',
                    time: 2
                });
                $("#sort ").focus();
                return false;
            }
        }

        var str = $("#banner_name").val();
        if (str == "") {
            $("#banner_name").tips({
                side: 3,
                msg: '请输入Banner名称',
                bg: '#AE81FF',
                time: 2
            });
            $("#banner_name").focus();
            return false;
        } else {
            if (!validateLength(str, 40)) {
                $("#banner_name").tips({
                    side: 3,
                    msg: '最多输入20字',
                    bg: '#AE81FF',
                    time: 2
                });
                $("#banner_name ").focus();
                return false;
            }
        }
        var str=$('input:radio[name="banner_type"]:checked').val();
        if(1 == str){
            var banner_route = $("#banner_route").val().replace(/(^\s*)|(\s*$)/g, "");
            if (banner_route == "") {
                $("#banner_route").tips({
                    side: 3,
                    msg: '请输入Banner路径',
                    bg: '#AE81FF',
                    time: 2
                });
                $("#banner_route").focus();
                return false;
            } else {
                if (!validateLength(str, 100)) {
                    $("#banner_url").tips({
                        side: 3,
                        msg: '最多输入100字',
                        bg: '#AE81FF',
                        time: 2
                    });
                    $("#banner_route ").focus();
                    return false;
                }
            }
		}else{
            var banner_like = $("#banner_like").val().replace(/(^\s*)|(\s*$)/g, "");
            if (banner_like == "") {
                $("#banner_like").tips({
                    side: 3,
                    msg: '请输入Banner链接',
                    bg: '#AE81FF',
                    time: 2
                });
                $("#banner_like").focus();
                return false;
            } else {
                if (!validateLength(str, 100)) {
                    $("#banner_url").tips({
                        side: 3,
                        msg: '最多输入100字',
                        bg: '#AE81FF',
                        time: 2
                    });
                    $("#banner_like ").focus();
                    return false;
                }
            }
		}
        return true;
    }

    //验证字符
    function validateLength(str,commonLength){
        if (str == null) return;
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
    //是否为正整数
    function isPositiveInteger(val){
        var re = /^[0-9]+$/ ;
        if(re.test(val)){
            if(val>0){
                return true;
            }
        }
        return false;
    }
    function validateIntBigOrSm(val,s,b) {
        if(parseInt(val)>=parseInt(s) && parseInt(b)>=parseInt(val)){
            return true;
        }
        return false;
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
        var ending_time = date.getFullYear() + "-" + date.getMonth() + "-" + (date.getDate() +1 )+
            " " + date.getHours() + seperator2 + date.getMinutes();

        $("#ending_time").val(ending_time);

    }


    function checkNum() {
        var str = $("#description").val();
        if (str == null) return;
        var len = 0;
        for (var i=0; i<str.length; i++) {
            // var c = str.charCodeAt(i);
            //单字节加1
            // if ((c >= 0x0001 && c <= 0x007e) || (0xff60 <= c && c <= 0xff9f)) {
            len++;
            // /
        }
        $("#changeNum").html(len);
    }

    $("input[name=banner_type]").click(function(){
        var banner_type_value = $(this).val();
        if (1 == banner_type_value || null == banner_type_value){
            $("#banner_route_div").css("display","block");
            $("#banner_like_div").css("display","none");
        }else{
            $("#banner_route_div").css("display","none");
            $("#banner_like_div").css("display","block");
        }
    });
</script>
</body>
