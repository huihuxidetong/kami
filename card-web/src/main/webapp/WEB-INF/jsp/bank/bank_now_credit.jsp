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
    <!-- 日期框 -->
    <link rel="stylesheet" href="static/ace/css/datepicker.css" />
    <style type="text/css">
        a:link{
            text-decoration: none;
        }
    </style>
</head>
<body style="background-color: #fff">
<div style="width:100%;height:20px;"></div>
<form action="bank/goNowCredit.do" class="form-horizontal"  name="Form" id="Form" method="post">
    <input id="bank_id" name="bank_id" value="${pd.bank_id}" type="hidden">
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#jbxx" data-toggle="tab">信用卡</a>
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
                                        <td>
                                            <div class="nav-search">
                                                关键字:
                                                <span class="input-icon">
											<input type="text"  class="nav-search-input" id="nav-search-input" autocomplete="off" name="keywords" value="${pd.keywords }" placeholder="这里输入关键词"/>
											<i class="ace-icon fa fa-search nav-search-icon"></i>
										</span>
                                        <td style="vertical-align:top;">
                                            &nbsp;
                                            &nbsp;
                                        </td>
                                        <c:if test="${QX.cha ==1}">
                                            <td style="vertical-align:top;"><a class="btn btn-mini btn-primary" onclick="tosearch();">查询</a></td>
                                        </c:if>
                                        <td style="vertical-align:top;">
                                            &nbsp;
                                            &nbsp;
                                        </td>
                                        <c:if test="${QX.cha ==1}">
                                            <td style="vertical-align:top;"><a class="btn btn-mini btn-primary"  onclick="javascript:addDiv();">添加</a></td>
                                        </c:if>
                                    </div>
                                    </td>
                                    <div class="page-header position-relative" style="width: 100%">
                                        <table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">
                                            <thead>
                                            <tr>
                                                <th class="center">ID</th>
                                                <th class="center">信用卡名称</th>
                                                <th class="center">操作</th>
                                            </tr>
                                            </thead>
                                            <c:forEach items="${varList}" var="var" varStatus="vs">
                                                <tr style="white-space: nowrap;text-align: center;">
                                                    <td class="center">${var.id}</td>
                                                    <td class="center">${var.card_name}</td>
                                                    <td class="center">
                                                        <c:if test="${QX.edit != 1 && QX.del != 1 }">
                                                        <span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="ace-icon fa fa-lock" title="无权限"></i></span>
                                                        </c:if>
                                                        <div class="hidden-sm hidden-xs btn-group">
                                                            <c:if test="${QX.edit == 1 }">
                                                                <a class="btn btn-mini btn-success" onclick="javascript:editDiv('${var.card_name}',${var.id});">编辑</a>
                                                            </c:if>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </div>
                                    <div class="page-header position-relative">
                                        <table style="width:100%;">
                                            <tr>
                                                <td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="div1" style="display: none; width: 280px; height: 180px;border: 1px solid grey;">
                <div class="form-group">
                    <div style="white-space: nowrap; margin-left: 100px;font-size: 16px;"><span id="card_title">添加信用卡</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="javascript:CloseDiv();"><span style="color: grey;">X</span></a></div><br/>
                    <div class="col-sm-9" style="margin-left: 50px;">
                        <input id="card_name" name="card_name"  STYLE="width: 200px;"  type="text" placeholder="输入信用卡名称">
                    </div>
                    <input type="hidden" id="credit_card_id">
                    <input type="button" style="margin-left: 100px; margin-top: 40px;" onclick="javascript:addCredit(${pd.bank_id});" id="confirmButton" class="btn btn-primary btn-circle .btn-xs" value="添加"/>
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
<!--提示框-->
<script type="text/javascript" src="static/js/jquery.tips.js"></script>
<script type="text/javascript">
    var showCount = "${pd.showCount}";
    var currentPage ="${pd.currentPage}";
    $(top.hangge());//关闭加载状态
    function tosearch() {
        top.jzts();
        $("#Form").submit();
    }
    function addCredit(bank_id) {
        if(validateForm()) {
            var credit_card_id = $("#credit_card_id").val();
            $.ajax({
                type: "post",
                url: "<%=basePath%>/bank/addBankCreditCard?bank_id=" + bank_id + "&credit_card_id=" + credit_card_id,
                data: $('#Form').serialize(),
                success: function (result) {
                    if (result.resultCode == 200) {
                        if (null == currentPage || "" == currentPage) {
                            currentPage == 1;
                        }
                        if (null == showCount || "" == showCount) {
                            showCount = 10;
                        }
                        window.location.href = "<%=basePath%>/bank/goNowCredit.do?bank_id=" + $("#bank_id").val();
                    }
                },
                error: function () {
                    alert("异常");
                }
            });
        }
    }
    function editDiv(card_name,credit_card_id) {
        $("#credit_card_id").val(credit_card_id);
        document.getElementById("div1").style.display="block";
       $("#card_title").html("编辑信用卡");
       $("#card_name").val(card_name);
       $("#confirmButton").val("保存");
    }
    function addDiv() {
        $("#credit_card_id").val("")
        $("#card_title").html("添加信用卡");
        $("#card_name").val("");
        $("#confirmButton").val("添加");
        document.getElementById("div1").style.display="block";
    }
    function CloseDiv(){
        document.getElementById("div1").style.display="none";
        document.getElementById("tag").style.display="block";
    }

    //银行校验
    function validateForm() {
        var str = $("#card_name").val();
        if (str == "") {
            $("#card_name").tips({
                side: 3,
                msg: '信用卡名称是必填项',
                bg: '#AE81FF',
                time: 2
            });
            $("#bank_name").focus();
            return false;
        } else {
            if (!validateLength(str, 40)) {
                $("#card_name").tips({
                    side: 3,
                    msg: '请填写10个字以内的信用卡名称',
                    bg: '#AE81FF',
                    time: 2
                });
                $("#card_name ").focus();
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