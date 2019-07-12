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
    <link rel="stylesheet" href="static/ace/css/datepicker.css"/>
    <script type="text/javascript" src="static/wangEditor/wangEditor.js"></script>
</head>
<body style="background-color: #fff">
<div style="margin-top: 20px;width: 100%;"></div>
<form action="games/upload.do" id="fileForm" enctype="multipart/form-data" >
    <input type="file" name="file" id="file"  style="display:none;"/>
</form>
<form action="activity/${msg}.do" class="form-horizontal" name="Form" id="Form" method="post">
    <input name="edithtml" id="edithtml" type="hidden" value="${pd.edithtml}"/>
    <input name="sourceFilePath" id="sourceFilePath" value="${pd.sourceFilePath}" type="hidden"/>
    <input type="hidden" name="bank_ids" id="bank_ids" value="">
    <ul id="myTab" class="nav nav-tabs">
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
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label " style="text-align: left">活动银行卡：</label>
                                            <div class="col-sm-9 control-label"style="text-align: left;">
                                                   ${pd.bank_name}&nbsp;&nbsp;${pd.card_name}
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label " style="text-align: left">活动名称：</label>
                                            <div class="col-sm-9 control-label " style="text-align: left;">
                                                ${pd.activity_name}
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label " style="text-align: left">活动时间：</label>
                                            <div class="col-sm-9 control-label " style="text-align: left;" >
                                                ${pd.activity_time_start},${pd.activity_time_end}
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label " style="text-align: left">活动地点：</label>
                                            <div class="col-sm-9 control-label " style="text-align: left;" >
                                                ${pd.activity_position}
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label " style="text-align: left">活动须知：</label>
                                            <div class="col-sm-9 control-label " style="text-align: left;">
                                                ${pd.describe}
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
                                        <div class="form-group">
                                            <c:forEach items="${pd.imageList}" var="img">
                                                <div class="col-sm-2">
                                                    <a href="${img.downloand_source_file_url}" target="_blank"> <img src="${img.downloand_source_file_url}" style="width: 80px;height:80px;"/></a>
                                                </div>
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
    var editor;
    $(top.hangge());//关闭加载状态

    $(function() {
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
        var html = editor.txt.html();
        $("#edithtml").val(html.toString());

    });
    $(function () {
        $.ajax({
            type: "POST",
            url: "<%=basePath%>/bank/allBank.do",
            dataType: "json",
            success:function (data) {
                var bank = "${pd.bank_id}";
                for (var i = 0; i < data.varList.length; i++) {
                    if(null != bank && bank.indexOf(data.varList[i].id) != -1){
                        $('#bank').append("<option value='" + data.varList[i].id + "' selected='selected'  >" + data.varList[i].bank_name + "</option>");
                    }else {
                        $('#bank').append("<option value='" + data.varList[i].id + "'>" + data.varList[i].bank_name + "</option>");
                    }
                }
                $('#bank').selectpicker('refresh');
            },
            error:function () {
                alert("发生错误");
            }
        });

        var bank_ids = "${pd.bank_id}";
        if(null != bank_ids){
            $("#credit_card").empty();
            $.ajax({
                type: "post",
                url:  "<%=basePath%>/credit/allCreditCard?bank_ids="+bank_ids,
                success: function (data) {
                    var bank_credit_card_id = "${pd.bank_credit_card_id}";
                    for (var i = 0; i < data.varList.length; i++) {
                        if(bank_credit_card_id.indexOf(data.varList[i].id) != -1){
                            $('#credit_card').append("<option value='" + data.varList[i].id + "'  selected='selected'>" + data.varList[i].card_name + "</option>");
                        }else{
                            $('#credit_card').append("<option value='" + data.varList[i].id + "'>" + data.varList[i].card_name + "</option>");
                        }
                    }
                    $('#credit_card').selectpicker('refresh');
                },
                error: function () {
                    alert("加载失败");
                }
            });
        }

    })


    function  getCreditCard() {
        var bank_ids = $("#bank").val();
        $("#credit_card").empty();
        $.ajax({
            type: "post",
            url:  "<%=basePath%>/credit/allCreditCard?bank_ids="+bank_ids,
            success: function (data) {
                var bank_credit_card_id = "${pd.bank_credit_card_id}";
                for (var i = 0; i < data.varList.length; i++) {
                    if(bank_credit_card_id.indexOf(data.varList[i].id) != -1){
                        $('#credit_card').append("<option value='" + data.varList[i].id + "'  selected='selected'>" + data.varList[i].card_name + "</option>");
                    }else{
                        $('#credit_card').append("<option value='" + data.varList[i].id + "'>" + data.varList[i].card_name + "</option>");
                    }
                }
                $('#credit_card').selectpicker('refresh');
            },
            error: function () {
                alert("加载失败");
            }
        });
    }
</script>
</body>