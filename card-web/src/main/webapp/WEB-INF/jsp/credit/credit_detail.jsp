<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<form action="credit/upload.do" id="fileForm" enctype="multipart/form-data" >
    <input type="file" name="file" id="file"  style="display:none;"/>
</form>
<form action="credit/goDetail.do" class="form-horizontal" name="Form" id="Form" method="post">
    <input name="edithtml" id="edithtml" type="hidden"/>
    <input name="sourceFilePath" id="sourceFilePath" value="${pd.sourceFilePath}" type="hidden"/>
    <input type="hidden" name="card_label" id="card_label" value="${pd.card_label}">
    <ul id="myTab" class="nav nav-tabs">
        <div style="text-align: center;">
            <a style="margin-bottom: -30px;" href="javascript:void(0)" class="btn btn-mini btn-primary" onclick="cancel();">取消</a>
            <a style="margin-bottom: -30px;" href="javascript:void(0)" class="btn btn-mini btn-success" onclick="save();">保存</a>
        </div>
        <li class="active">
            <a href="#jbxx" data-toggle="tab">基本信息</a>
        </li>
        <li><a href="#zrtj" data-toggle="tab">专享特权</a></li>
        <li><a href="#jjcl" data-toggle="tab">费用</a></li>
        <li><a href="#bklc" data-toggle="tab">办卡流程</a></li>
        <li><a href="#cjwt" data-toggle="tab">常见问题</a></li>
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
                                            <label class="col-sm-3 control-label" style="text-align: left">信用卡照片：</label>
                                            <div class="col-sm-9">
                                                <img src="${pd.downloand_source_file_url}" style="width: 150px;height: 100px;">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label " style="text-align: left">所属银行：</label>
                                            <div class="col-sm-9">
                                               ${pd.bank_name}
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label " style="text-align: left">信用卡名称：</label>
                                            <div class="col-sm-9" >
                                                ${pd.card_name}
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label " style="text-align: left">特色：</label>
                                            <div class="col-sm-9">
                                               ${pd.card_feature}
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label " style="text-align: left">等级：</label>
                                            <div class="col-sm-9">
                                                ${pd.card_leval}
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label " style="text-align: left">币种：</label>
                                            <div class="col-sm-9">
                                                ${pd.card_currency}
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label " style="text-align: left">发卡组织:</label>
                                            <div class="col-sm-9">
                                                ${pd.card_hairpin_organi}
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label " style="text-align: left">免息期限：</label>
                                            <div class="col-sm-9" >
                                               ${pd.free_interest_period}
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label " style="text-align: left">积分规则：</label>
                                            <div class="col-sm-9">
                                               ${pd.integral_rule}
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label " style="text-align: left">申请条件：</label>
                                            <div class="col-sm-9">
                                                ${pd.apply_condition}
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label " style="text-align: left">标签:</label>
                                            <div class="col-sm-9">
                                                <c:forEach items="${pd.card_label}"  var="card_label" varStatus="index">
                                                    <label style="padding-left: 8px;padding-top:7px;">
                                                        <input name="card_labels" class="ace ace-checkbox-2"  type="checkbox" value="${card_label}" <c:if test="${fn:contains(pd.card_choose_label,card_label)}"> checked="checked" </c:if> >	<span class="lbl">${card_label}</span>
                                                    </label>
                                                </c:forEach>
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
                                            <textarea  style="width: 500px;height: 300px" id="card_privilege" name="card_privilege" placeholder="最多可输⼊200字" rows="2"  maxlength="200" onkeyup="checkNum()">${pd.card_privilege}</textarea>
                                            <span id="changeNum">0</span>
                                            <span>/</span>
                                            <span>200</span>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="tab-pane fade" id="bklc">
            <div class="main-container">
                <!-- /section:basics/sidebar -->
                <div class="main-content">
                    <div class="main-content-inner">
                        <div class="page-content">
                            <div class="row">
                                <div class="col-xs-12">
                                    <!-- 存放生成的hmlt开头  -->
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <div id="zhongxin1" style="display: block;">
                                                <thead>
                                                <tr>
                                                    <th class="center">流程名称</th>
                                                </tr>
                                                </thead>
                                                <div id="divOpr">
                                                    <tbody id="table-values">
                                                    <c:forEach items="${pd.opr_processList}" var="opr_process">
                                                        <tr>
                                                            <td style="text-align: center;">
                                                                <input type="text" id="opr_process" name="opr_process"  maxlength="10"  value="${opr_process}"  placeholder="请填写5个字以内的流程名称"/>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
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
        <div class="tab-pane fade" id="cjwt">
            <div class="main-container">
                <!-- /section:basics/sidebar -->
                <div class="main-content">
                    <div class="main-content-inner">
                        <div class="page-content">
                            <div class="row">
                                <div class="col-xs-12">
                                    <!-- 存放生成的hmlt开头  -->
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <div id="zhongxin2" style="display: block;">
                                                <table id="simple-table2" class="table table-striped table-bordered table-hover" style="margin-top:5px;">
                                                    <tbody id="simple-table2-value">
                                                    <c:forEach items="${pd.creditQues}" var="creditQues">
                                                        <tr class="form-group www">
                                                            <td>
                                                                <label class="col-sm-3 control-label " style="text-align: left">问题：</label>
                                                                <div class="col-sm-6" style="position: relative">
                                                                    <input id="question" name="question" STYLE="width: 300px" maxlength="30" class="col-xs-10 col-sm-7" type="text" value="${creditQues.question}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                    <button id="que" href="javascript:cloError();" style="position: absolute;top:0;right:45%" class="close" ></button>
                                                                </div>
                                                                <hr>
                                                                <label class="col-sm-3 control-label " style="text-align: left">回答：</label>
                                                                <div class="col-sm-9">
                                                                    <textarea  style="width: 360px;height: 150px" id="answer" name="answer" minlength="100" rows="2"  maxlength="200" onkeyup="checkNum()">${creditQues.answer}</textarea>
                                                                    <span id="changeNum1">0</span>
                                                                    <span>/</span>
                                                                    <span>200</span>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
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
                                        <div class="form-group">
                                            <table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">
                                                <thead>
                                                <tr>
                                                    <th class="center">费用</th>
                                                    <th class="center">费用说明</th>
                                                    <th class="center">费用措施</th>
                                                </tr>
                                                </thead>
                                                <div id="divCost">
                                                    <tbody id="table-value">
                                                    <c:forEach items="${pd.creditCost}" var="creditCost">
                                                        <tr>
                                                            <td style="text-align: center;">
                                                                <input style="text-align: center;" type="text" id="cost_type" name="cost_type" maxlength="10" value="${creditCost.cost_type}"/>
                                                            </td>
                                                            <td style="text-align: center;">
                                                                <input style="text-align: center" type="text" id="cost_explain" name="cost_explain"  maxlength="60" value="${creditCost.cost_explain}"/>
                                                            </td>
                                                            <td style="text-align: center;">
                                                                <input style="text-align: center" type="text" id="cost_measures" name="cost_measures"  maxlength="60" value="${creditCost.cost_measures}"/>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </div>
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
    var ending_type = "${pd.ending_type}";
    if(ending_type == "1"){
        $("#max_team_member_cnt_div").css("display","none");
        $("#ending_time_div").css("display","block");
    }else if(ending_type == "2"){
        $("#max_team_member_cnt_div").css("display","block");
        $("#ending_time_div").css("display","none");
    }
    $(top.hangge());//关闭加载状态
    var downloand_source_file_url = "${pd.downloand_source_file_url}";
    $("#fileUrl").attr("src",downloand_source_file_url);

    $(function() {
        var E = window.wangEditor;
        //这里的id为<div id="editor"中的id.
        editor = new E('#editor');
        // 配置服务器端地址,也就是controller的请求路径，不带项目路径，前面没有/
        editor.customConfig.uploadImgServer = 'credit/upload.do';
        //配置属性名称，绑定请求的图片数据
        //controller会用到，可以随便设置，但是一定要与controller一致
        editor.customConfig.uploadFileName = 'file';
        //创建编辑器
        editor.create();
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

        $("#is_set_buy_link").change(function() {
            if($("input[type='checkbox']").is(':checked')){
                $("#is_set_buy_link_true").css("display","block");
            }else{
                $("#is_set_buy_link_true").css("display","none");
            }
        });
        var mini_program_id = '${pd.mini_program_id}';
        if(null !=mini_program_id && "" !=  mini_program_id){
            $("#is_set_buy_link").attr("checked","checked");
            $("#is_set_buy_link_true").css("display","block");
        }
    });


    //点击button按钮预览二维码
    function  previewGames(id){
        top.jzts();
        var diag = new top.Dialog();
        diag.Drag=true;
        diag.URL = "<%=basePath%>games/goPreview.do?id="+id;
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
    function  endingTypeChange() {
        var val=$("#ending_type").val();
        if(val == 1){
            $("#max_team_member_cnt_div").css("display","none");
            $("#ending_time_div").css("display","block");
        }else{
            $("#max_team_member_cnt_div").css("display","block");
            $("#ending_time_div").css("display","none");
        }
    }
    //取消
    function cancel(){
        if(null == currentPage || "" == currentPage){
            currentPage = 1;
        }
        if(null == showCount || "" == showCount){
            showCount = 10;
        }
        window.location.href= "<%=basePath%>credit/list.do?create_type=2&currentPage=" + currentPage + "&showCount=" + showCount;
    }
    function save(){
        if(validateForm()) {
            var html = editor.txt.html();
            $("#edithtml").val(html.toString());
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
                        window.location.href= '<%=basePath%>games/list.do?create_type=2currentPage=' + currentPage + "&showCount=" + showCount;
                    }
                },
                error : function() {
                    bootbox.alert("异常！");
                }
            });
        }
    }

    function validateForm(){
        var str = $("#title").val();
        if(str==""){
            $("#title").tips({
                side:3,
                msg:'请输入奖品名称',
                bg:'red',
                time:2
            });
            $("#title").focus();
            return false;
        }else{
            if(!validateLength(str,40)){
                $("#title").tips({
                    side:3,
                    msg:'最多可输⼊20字',
                    bg:'red',
                    time:2
                });
                $("#title").focus();
                return false;
            }
        }
        var val = $("#quantity").val();
        if(val==""){
            $("#quantity").tips({
                side:3,
                msg:'请输入奖品数量',
                bg:'red',
                time:2
            });
            $("#quantity").focus();
            return false;
        }else{
            if(!isPositiveInteger(val) || !validateIntBigOrSm(val,1,20000)){
                $("#quantity").tips({
                    side:3,
                    msg:'请填写1-20000之间的整数',
                    bg:'#AE81FF',
                    time:2
                });
                $("#quantity").focus();
                return false;
            }
        }
        if($("input[type='checkbox']").is(':checked')) {
            var val = $("#mini_program_id").val();
            if (val == "") {
                $("#mini_program_id").tips({
                    side: 3,
                    msg: '请输入商城小程序APPID',
                    bg: '#AE81FF',
                    time: 2
                });
                $("#mini_program_id").focus();
                return false;
            }
            var val = $("#mini_program_url").val();
            if (val == "") {
                $("#mini_program_url").tips({
                    side: 3,
                    msg: '请输入路径',
                    bg: '#AE81FF',
                    time: 2
                });
                $("#mini_program_url").focus();
                return false;
            }
            var val = $("#explains").val();
            if (val != "") {
                if (!validateLength(val, 10)) {
                    $("#explains").tips({
                        side: 3,
                        msg: '最多可数5个字',
                        bg: '#AE81FF',
                        time: 2
                    });
                    $("#explains").focus();
                    return false;
                }
            }
        }
        var val = $("#sponsor_id").val();
        if(val==""){
            $("#sponsor_id").tips({
                side:3,
                msg:'请选择赞助商名称',
                bg:'#AE81FF',
                time:2
            });
            $("#sponsor_id").focus();
            return false;
        }

        var val = $("#ending_type").val();
        if(val==""){
            $("#ending_type").tips({
                side:3,
                msg:'请选择抽奖方式',
                bg:'#AE81FF',
                time:2
            });
            $("#ending_type").focus();
            return false;
        }
        if(val != "" && null != val){
            if(val ==1){
                var val = $("#ending_time").val();
                if(val==""){
                    $("#ending_time").tips({
                        side:3,
                        msg:'请选择开奖时间',
                        bg:'#AE81FF',
                        time:2
                    });
                    $("#ending_time").focus();
                    return false;
                }
            }else{
                var val = $("#max_team_member_cnt").val();
                if(val==""){
                    $("#max_team_member_cnt").tips({
                        side:3,
                        msg:'请输入最大人数',
                        bg:'#AE81FF',
                        time:2
                    });
                    $("#max_team_member_cnt").focus();
                    return false;
                }else{
                    if(!isPositiveInteger(val)){
                        $("#max_team_member_cnt").tips({
                            side:3,
                            msg:'请输入正整数',
                            bg:'#AE81FF',
                            time:2
                        });
                        $("#max_team_member_cnt").focus();
                        return false;
                    }
                }
            }
            var val = $("#description").val();
            if(val!=""){
                if(!validateLength(val,400)){
                    $("#quantity").tips({
                        side:3,
                        msg:'最多可数200个字',
                        bg:'#AE81FF',
                        time:2
                    });
                    $("#description").focus();
                    return false;
                }
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
    function previewCode(id){}
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

    function sponsorChange(obj){
        var txt=obj.options[obj.options.selectedIndex].text;
        $("#sponsor_name").val(txt);
    }

    //添加赞助商
    function addSponsor() {
        window.location.href="<%=basePath%>sponsor/goAdd.do";
    }
    function checkNum() {
        var str = $("#card_privilege").val();
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
</script>
</body>