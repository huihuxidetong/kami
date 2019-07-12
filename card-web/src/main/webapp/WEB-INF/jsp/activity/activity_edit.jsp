<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
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
	<link rel="stylesheet" href="dist/css/bootstrap-select.css">
	<style type="text/css">
		.btn-primary{
			margin-right: 5px;
			margin-bottom: 5px;
		}
	</style>
</head>
<body style="background-color: #fff">
<div style="margin-top: 20px;width: 100%;"></div>
<form action="activity/upload.do" id="fileForm" enctype="multipart/form-data" >
	<input type="file" name="file" id="file"  style="display:none;"/>
</form>
<form action="activity/${msg}.do" class="form-horizontal" name="Form" id="Form" method="post">
	<input type="hidden" name="activity_id" id="activity_id" value="${pd.activity_id}"/>
	<input name="edithtml" id="edithtml" type="hidden" value="${pd.edithtml}"/>
	<input name="bank_name" id="bank_name" type="hidden" value="${pd.bank_name}"/>
	<input name="card_name" id="card_name" type="hidden" value="${pd.card_name}"/>
	<input name="sourceFilePath" id="sourceFilePath" value="${pd.sourceFilePath}" type="hidden"/>
	<input type="hidden" name="activity_custom_lable" id="activity_custom_lable" value="${pd.activity_custom_lable}">
	<input type="hidden" name="activity_choose_lable" id="activity_choose_lable" value="${pd.activity_choose_lable}">
	<input type="hidden" name="urlList" id="urlList" value="${pd.urlList}">
	<input type="hidden" name="bank_ids" id="bank_ids" value="">
	<input type="hidden" name="credit_card_ids" id="credit_card_ids" value="">
	<ul id="myTab" class="nav nav-tabs">
		<div style="text-align: center;">
			<a style="margin-bottom: -10px;" href="javascript:void(0)" class="btn btn-mini btn-primary" onclick="cancel();">取消</a>
			<a style="margin-bottom: -10px;" href="javascript:void(0)" class="btn btn-mini btn-success" onclick="save();">保存</a>
		</div>
		<li class="active">
			<a href="#jbxx" data-toggle="tab">活动信息</a>
		</li>
		<li><a href="#zrtj" data-toggle="tab">活动描述</a></li>
		<li><a href="#jjcl" data-toggle="tab">活动图片</a></li>
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
										<div class="form-group" >
											<label class="col-sm-3 control-label " style="text-align: left">活动银行卡：</label>
											<div class="col-md-9">
												<select id="bank" <c:if test="${pd.status != 1 || pd.is_unlimitedt_bank_credit_card == 1}"> disabled="disabled"</c:if> name="bank" onchange="getCreditCard()" class="selectpicker show-tick chosen-select show-menu-arrow" multiple data-max-option="1" data-live-search="true"></select>
												<select id="credit_card" <c:if test="${pd.status != 1 || pd.is_unlimitedt_bank_credit_card == 1}"> disabled="disabled" </c:if>  name="credit_card" class="selectpicker show-tick chosen-select show-menu-arrow" multiple data-max-option="1" data-live-search="true"></select>
												<label class="pos-rel"><input name="is_unlimitedt_bank_credit_card" type="checkbox" <c:if test="${pd.status != 1}"> disabled="disabled"</c:if> <c:if test="${pd.is_unlimitedt_bank_credit_card == 1}"> checked="checked"</c:if> class="ace" onclick="checkboxOnclick(this)" id="is_unlimitedt_bank_credit_card" value="${pd.is_unlimitedt_bank_credit_card}" /><span class="lbl"></span>不限银行和信用卡</label>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label " style="text-align: left">活动名称：</label>
											<div class="col-sm-9" >
												<input id="activity_name" name="activity_name" maxlength="20"  STYLE="width: 360px" class="col-xs-10 col-sm-7" type="text" value="${pd.activity_name}" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label " style="text-align: left">活动时间：</label>
											<div class="col-sm-9">
												<td style="padding-left:2px;">
													<input name="activity_time_start" id="activity_time_start"  value="${pd.activity_time_start}" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" style="width:180px;height: 30px"/>
												</td>
												<td style="padding-left:2px;">
													<input name="activity_time_end" id="activity_time_end"  value="${pd.activity_time_end}" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:activity_time_start.value,readOnly:true})" style="width:180px;height: 30px"/>
												</td>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label " style="text-align: left">活动地点：</label>
											<div class="col-sm-9">
												<input id="activity_position" name="activity_position" maxlength="10" placeholder="请填写10个字以内的活动地点" STYLE="width: 360px" class="col-xs-10 col-sm-7" type="text" value="${pd.activity_position}"/>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label " style="text-align: left">活动类型：</label>
											<div class="col-sm-9" >
												<select id="activity_type"  name="activity_type" data-skipstyle="data-skipstyle"  class="col-xs-10 col-sm-7" style="width: 360px">
													<option value="" >请选择</option>
													<c:forEach items="${pd.ACTIVITY_TYPE}" var="activity_type">
														<option value="${activity_type.key}" <c:if test="${pd.activity_type == activity_type.key}"> selected="selected" </c:if>>${activity_type.value}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label " style="text-align: left">是否需要报名：</label>
											<div class="col-sm-9">
												<input type="radio" id="is_has_sign_up"  <c:if test="${pd.status != 1}"> disabled="disabled"</c:if>  name="is_has_sign_up"  value="1" <c:if test="${pd.is_has_sign_up == 1}"> checked="checked" </c:if> />是
												<input type="radio" name="is_has_sign_up"  <c:if test="${pd.status != 1}"> disabled="disabled"</c:if>  value="0" <c:if test="${pd.is_has_sign_up == 0}"> checked="checked" </c:if>/>否
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label " style="text-align: left">活动须知:<span id="describe_tips"></span></label>
											<div class="col-sm-9" >
												<textarea  style="width:360px;height: 200px" id="describe" name="describe" rows="2"  maxlength="200" placeholder="最多可输入200字" onkeyup="checkNum()">${pd.describe}</textarea>
												<span id="changeNum">0</span>
												<span>/</span>
												<span>200</span>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label " style="text-align: left">标签：</label>
											<div class="col-sm-9" >
												<div class="col-sm-9" id="card_lable_list" style="width: 360px;">
													<c:forEach items="${pd.activity_custom_lable}"  var="activity_custom_lable" varStatus="index">
														<label style="padding-left: 8px;padding-top:7px;" >
														<input name="card_lables" class="ace ace-checkbox-2"  type="checkbox" value="${activity_custom_lable}" <c:if test="${fn:contains(pd.activity_choose_lable,activity_custom_lable)}"> checked="checked" </c:if> ><span class="lbl">${activity_custom_lable}</span>
														</label>
													</c:forEach>
													<input type="button" onclick="javascript:OpenDiv();" id="selflabel" class="btn btn-primary btn-mini" value="自定义标签"/>
												</div>
											</div>
										</div>
										<div id="label" style="display: none; width: 280px; height: 180px;border: 1px solid grey; float: right;margin-top: -200px; margin-right: -180px;">
											<div class="form-group">
												<div style="white-space: nowrap; margin-left: 100px;font-size: 16px;">添加自定义标签&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<a style="text-decoration: none;" href="javascript:CloseDiv();"><span style="color: grey;">X</span></a>
												</div><br/>
												<div class="col-sm-9" style="margin-left: 50px;">
													<input name="custom_label" id="custom_label"  STYLE="width: 200px;border-radius: 16px"  maxlength="10" type="text" placeholder="输入标签名称">
												</div>
												<input type="button" style="margin-left: 100px; margin-top: 40px;" href="javascript:void(0)" onclick="addCustomLabel()" class="btn btn-primary btn-circle .btn-xs" value="添加标签"/>
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
		<div class="tab-pane fade" id="zrtj">
			<div class="main-container">
				<!-- /section:basics/sidebar -->
				<div class="main-content">
					<div class="main-content-inner">
						<div class="page-content">
							<div class="row">
								<div class="col-xs-12">
									<!-- 存放生成的hmlt开头  -->
									<div class="col-md-5">
										<div class="form-group">
											<div id="editor" name="editor">
												${pd.notic}
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
		<div class="tab-pane fade" id="jjcl">
			<div class="main-container">
				<!-- /section:basics/sidebar -->
				<div class="main-content">
					<div class="main-content-inner">
						<div class="page-content">
							<div class="row">
								<div class="col-xs-12">
									<!-- 存放生成的hmlt开头  -->
									<div class="col-md-6">
										<span id="image_url_tips"></span>
										<div class="tab-right" id="rrr">
											<c:forEach items="${pd.imageList}" var="img">
												<div class="col-sm-2" style="position: relative;">
													<input name="source_url" value="${img.image_url}" type="hidden"/>
													<a href="${img.downloand_source_file_url}" target="_blank"> <img src="${img.downloand_source_file_url}" style="width: 80px;height:80px;"/></a>
													<a style="position: absolute;top:0;right:25px" class="close" style="position: absolute;top:0;right:35px" href="javascript:void(0);" onclick="delImg(this)"> <button  type="button"><span>&times;</span></button></a>
												</div>
											</c:forEach>
										</div>
										<div class="tab-right">
											<a href="javascript:void(0);" class="btn btn-mini btn-success" onclick="addImg(this)">添加</a>
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
<script src="dist/js/bootstrap-select.js"></script>
<script type="text/javascript">
    var showCount = "${pd.showCount}";
    var currentPage ="${pd.currentPage}";
    var editor;
    $(top.hangge());//关闭加载状态*/
    function uploadFile(obj){
        $('input[type="file"]').click();
        $('#fileForm').ajaxForm({
            dataType: 'json',
            type:'post',
            success: function(data){
                $(document).on('change','#file',function(){
                    $('#fileForm').submit();
                })
                var str =
                    '<input name="source_url" value="'+data.sourceFilePath+'" type="hidden"/>' +
                    '<a href="'+data.downloandSourceFileUrl+'" target="_blank"> <img src="'+data.downloandSourceFileUrl+'" style="width: 80px;height:80px;"/></a>' +
                    '<a style="position: absolute;top:0;right:25px" href="javascript:void(0);" onclick="delImg(this)"><button  class="close" type="button"><span>&times;</span></button></a>' ;
                $(obj).parent("div").html(str);
            }
        })
    }
    var downloand_source_file_url = "${pd.downloand_source_file_url}";
    $("#fileUrl").attr("src",downloand_source_file_url);
    $(function() {
        $("#bank").selectpicker({width:180});
        $("#credit_card").selectpicker({width:180});


        $.ajax({
            type: "POST",
            url: "<%=basePath%>/bank/allBank.do",
            dataType: "json",
            success:function (data) {
                var bank = "${pd.bank_id}";
                var is_unlimitedt_bank_credit_card = $("#is_unlimitedt_bank_credit_card").val();
                if( 1 == is_unlimitedt_bank_credit_card){
                    bank = "";
                }
                for (var i = 0; i < data.varList.length; i++) {
                    if(null != bank && bank.indexOf(data.varList[i].id) != -1){
                        $('#bank').append("<option value='" + data.varList[i].id + "' selected='selected'  >" + data.varList[i].bank_name + "</option>");
                    }else {
                        $('#bank').append("<option value='" + data.varList[i].id + "' >" + data.varList[i].bank_name + "</option>");
                    }
                }
                $('#bank').selectpicker('refresh');
            },
            error:function () {
                alert("发生错误");
            }
        });
        var bank_ids = "${pd.bank_id}";
		if(bank_ids != null || "" != bank_ids){
            $("#credit_card").empty();
            $.ajax({
                type: "post",
                url:  "<%=basePath%>/credit/allCreditCard?bank_ids="+bank_ids,
                success: function (data) {
                    var bank_credit_card_id = "${pd.bank_credit_card_id}";
                    var is_unlimitedt_bank_credit_card = $("#is_unlimitedt_bank_credit_card").val();
                    if( 1 == is_unlimitedt_bank_credit_card){
                        bank_credit_card_id = "";
                    }
                    for (var i = 0; i < data.varList.length; i++) {
                        if(bank_credit_card_id.indexOf(data.varList[i].id) != -1){
                            $('#credit_card').append("<option value='" + data.varList[i].id + "'  selected='selected'>" + data.varList[i].card_name + "</option>");
                        }else{
                            $('#credit_card').append("<option value='" + data.varList[i].id + "' >" + data.varList[i].card_name + "</option>");
                        }
                    }
                    $('#credit_card').selectpicker('refresh');
                },
                error: function () {
                    alert("加载失败");
                }
            });
        }

        var E = window.wangEditor;
        //这里的id为<div id="editor"中的id.
        editor = new E('#editor');
        // 配置服务器端地址,也就是controller的请求路径，不带项目路径，前面没有/
        editor.customConfig.uploadImgServer = 'activity/upload.do';
        //配置属性名称，绑定请求的图片数据
        //controller会用到，可以随便设置，但是一定要与controller一致
        editor.customConfig.uploadFileName = 'file';
        //创建编辑器
        editor.create();
        //设置默认日期开始
        checkNum();
        $(function(){
            var activity_time_start = '${pd.activity_time_start}';
            var activity_time_end = '${pd.activity_time_end}';
            if(null == activity_time_start || "" == activity_time_start || null == activity_time_end || "" == activity_time_end) {
                //设置默认日期开始
                setdate();
            }
        });

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



    //取消
    function cancel(){
        if(null == currentPage || "" == currentPage){
            currentPage = 1;
        }
        if(null == showCount || "" == showCount){
            showCount = 10;
        }
        window.location.href= "<%=basePath%>activity/list.do?currentPage=" + currentPage + "&showCount=" + showCount;
    }
    function save(){
        if(validateForm()) {
            var activity_custom_lable = '';
            var activity_choose_lable ='';
           $("input[name=card_lables]").each(function(){
                activity_custom_lable += $(this).val() +  ",";
            });
            if(null != activity_custom_lable && "" != activity_custom_lable){
                activity_custom_lable = activity_custom_lable.substring(0,activity_custom_lable.length-1);
            }

            $("input[name=card_lables]:checked").each(function(){
                activity_choose_lable += $(this).val() +  ",";
            });
            if(null != activity_choose_lable && "" != activity_choose_lable){
                activity_choose_lable = activity_choose_lable.substring(0,activity_choose_lable.length-1);
            }
            $("#activity_custom_lable").val(activity_custom_lable);
            $("#activity_choose_lable").val(activity_choose_lable);
            var html = editor.txt.html();
            $("#edithtml").val(html.toString());

            var urlList = '';
            $("input[name=source_url]").each(function(){
                if(null != $(this).val() && "" != $(this).val()) {
                    urlList += $(this).val() + ",";
                }
            });
            if(null != urlList && "" != urlList){
                urlList = urlList.substring(0,urlList.length-1);
			}
            if(null == urlList || "" == urlList){
                $("#image_url_tips").tips({
                    side:3,
                    msg:'请至少上传一张图片',
                    bg:'#AE81FF',
                    time:2
                });
                $("#image_url_tips").focus();
            	return;
			}
			$("#urlList").val(urlList);
            var url = '<%=basePath%>'+$("#Form").attr("action")
            $("#bank_ids").val($("#bank").val());
            $("#credit_card_ids").val($("#credit_card").val());
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
                        window.location.href= '<%=basePath%>activity/list.do?currentPage=' + currentPage + "&showCount=" + showCount;
                    }
                },
                error : function() {
                    bootbox.alert("异常！");
                }
            });
        }
    }

    function validateForm(){
        var str = $("#activity_name").val();
        if(str==""){
            $("#activity_name").tips({
                side:3,
                msg:'请输入活动名称',
                bg:'#AE81FF',
                time:2
            });
            $("#activity_name").focus();
            return false;
        }else{
            if(!validateLength(str,40)){
                $("#activity_name").tips({
                    side:3,
                    msg:'最多可输⼊20字',
                    bg:'red',
                    time:2
                });
                $("#activity_name").focus();
                return false;
            }
        }

        var val = $("#activity_time_start").val();
        if (val == "") {
            $("#activity_time_start").tips({
                side: 3,
                msg: '请选择活动开始时间',
                bg: '#AE81FF',
                time: 2
            });
            $("#activity_time_start").focus();
            return false;
        }
        var val = $("#activity_time_end").val();
        if (val == "") {
            $("#activity_time_end").tips({
                side: 3,
                msg: '请选择活动结束时间',
                bg: '#AE81FF',
                time: 2
            });
            $("#activity_time_end").focus();
            return false;
        }
        var str = $("#activity_position").val();
        if(str==""){
            $("#activity_position").tips({
                side:3,
                msg:'请输入活动地点',
                bg:'red',
                time:2
            });
            $("#activity_position").focus();
            return false;
        }else{
            if(!validateLength(str,20)){
                $("#activity_position").tips({
                    side:3,
                    msg:'请填写10个字以内的活动地点',
                    bg:'red',
                    time:2
                });
                $("#activity_position").focus();
                return false;
            }
        }
        var val = $("#activity_type").val();
        if (val == "") {
            $("#activity_type").tips({
                side: 3,
                msg: '请选择活动类型',
                bg: '#AE81FF',
                time: 2
            });
            $("#activity_type").focus();
            return false;
        }

        var val=$('input:radio[name="is_has_sign_up"]:checked').val();
        if(val==null){
            $("#is_has_sign_up").tips({
                side:3,
                msg:'请选择是否需要报名',
                bg:'#AE81FF',
                time:2
            });
            $("#is_has_sign_up").focus();
            return false;
        }


        var val=$('#describe').val();
        if(val==null || "" == val){
            $("#describe_tips").tips({
                side:3,
                msg:'请填写200个字以内的活动须知',
                bg:'#AE81FF',
                time:2
            });
            $("#describe_tips").focus();
            return false;
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
    //是否为正整数
    function isPositiveInteger(val){
        var re = /^[0-9]+$/ ;
        if(re.test(val)){
            if(val>0){
                return true;
            }
        }
        return false
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
        var activity_time_start = date.getFullYear() + "-" + date.getMonth() + "-" + (date.getDate() );
        var activity_time_end = date.getFullYear() + "-" + date.getMonth() + "-" + (date.getDate() +1 );

        $("#activity_time_start").val(activity_time_start);
        $("#activity_time_end").val(activity_time_end);

    }

    function checkNum() {
        var str = $("#describe").val();
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

    function OpenDiv(){
        document.getElementById("label").style.display="block";
        document.getElementById("tag").style.display="block";
    }
    function CloseDiv(){
        document.getElementById("label").style.display="none";
        document.getElementById("tag").style.display="block";
    }
    function addCustomLabel() {
        var custom_lable = $("#custom_label").val();
        if(validateLable(custom_lable)) {
            var html = '<label style="padding-left: 8px;padding-top:7px;" ><input name="card_lables" class="ace ace-checkbox-2"  type="checkbox" value="'+custom_lable+'"  ><span class="lbl">'+custom_lable+'</span> </label>';
            $("#selflabel").before(html);
            document.getElementById("label").style.display = "none";
        }
    }
    function validateLable(custom_lable){
        var success = true;
        $("input[name=card_lables]").each(function(){
            if($(this).val() == custom_lable ){
                $("#custom_label").tips({
                    side:3,
                    msg:'标签已存在',
                    bg:'#AE81FF',
                    time:2
                });
                $("#custom_label").focus();
                success = false;
            }
        });
        return success;
    }


    function addImg(obj){
        var imglenth = $("div[id=rrr] img")
        if(imglenth.length>=9){
            $("#image_url_tips").tips({
                side:3,
                msg:'最多上传9张图片',
                bg:'#AE81FF',
                time:2
            });
            $("#image_url_tips").focus();
            return;
		}else {
            var str = '<div class="col-sm-2" >' +
                '<img src="static/images/photo.png" id="fileUrl" onclick="uploadFile(this)" title="点击上传" style="cursor: pointer;width: 80px;height:80px;"" />' +
                '<a style="position: absolute;top:0;right:25px" href="javascript:void(0);" onclick="delImg(this)"> <button  class="close" type="button"><span>&times;</span></button></a>' +
                '</div>';
            $('#rrr').append(str);
            $('#zhongxin').show();
        }

    }
    function delImg(obj){
        bootbox.confirm("确定要删除该图片，删除后将无法恢复！", function(result) {
            if (result) {
                $(obj).parent("div").remove();
            }
        });
    }

    function getAllBank() {
        $.ajax({
            type: "POST",
            url: "<%=basePath%>/bank/allBank.do",
            dataType: "json",
            success:function (data) {
                for (var i = 0; i < data.varList.length; i++) {
					$('#bank').append("<option value='" + data.varList[i].id + "' >" + data.varList[i].bank_name + "</option>");
                }
                $('#bank').selectpicker('refresh');
            },
            error:function () {
                alert("发生错误");
            }
        });
    }

    function  getCreditCard() {
        var bank_ids = $("#bank").val();
        $("#credit_card").empty();
        $.ajax({
            type: "post",
            url:  "<%=basePath%>/credit/allCreditCard?bank_ids="+bank_ids,
            success: function (data) {
                for (var i = 0; i < data.varList.length; i++) {
					$('#credit_card').append("<option value='" + data.varList[i].id + "' >" + data.varList[i].card_name + "</option>");
                }
                $('#credit_card').selectpicker('refresh');
            },
            error: function () {
                alert("加载失败");
            }
        });
    }

    function checkboxOnclick(checkbox) {
        $("#bank").empty();
        $("#credit_card").empty();
        getAllBank();
        getCreditCard()
        if (checkbox.checked == true) {
            $("#is_unlimitedt_bank_credit_card").val("1");
            $("#bank").attr("disabled", "disabled");
            $("#credit_card").attr("disabled", "disabled");
        } else {
            $("#is_unlimitedt_bank_credit_card").val("0");
            $("#bank").removeAttr("disabled");
            $("#credit_card").removeAttr("disabled");
        }
    }
</script>
</body>