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
<form action="bank/${msg}" class="form-horizontal"  name="Form" id="Form" method="post">
    <input type="hidden" id="bank_id" name="bank_id" value="${pd.bank_id}"/>
    <input type="hidden" id="id" name="id" value="${pd.id}"/>
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#jbxx" data-toggle="tab">银行详情</a>
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
                                                <label class="col-sm-3 control-label " style="text-align: left">银行logo：</label>
                                                <div class="col-sm-9" style="margin-top: 8px;">
                                                    <img src="${pd.downloand_source_file_url}" style="width: 150px;height: 100px;">
                                                </div>
                                            </div>
                                            <div class="form-group" style="margin-right: 600px;">
                                                <label class="col-sm-3 control-label "  style="text-align: left">银行名称：</label>
                                                <div class="col-sm-9" style="margin-top: 8px;">
                                                    ${pd.bank_name}
                                                </div>
                                            </div>
                                            <div class="form-group" style="margin-right: 600px;">
                                                <label class="col-sm-3 control-label " style="text-align: left">联系人姓名：</label>
                                                <div class="col-sm-9" style="margin-top: 8px;">
                                                    ${pd.bank_contacts_name}
                                                </div>
                                            </div>
                                            <div class="form-group" style="margin-right: 600px;">
                                                <label class="col-sm-3 control-label " style="text-align: left">联系人电话：</label>
                                                <div class="col-sm-9" style="margin-top: 8px;">
                                                    ${pd.bank_contacts_tel}
                                                </div>
                                            </div>
                                            <div class="form-group" style="margin-right: 600px;">
                                                <label class="col-sm-3 control-label " style="text-align: left">银行简介：</label>
                                                <div class="col-sm-9" style="margin-top: 8px;">
                                                    ${pd.bank_contacts_notic}
                                                </div>
                                            </div>
                                        </div>
                                        <div>
                                            <div>
                                                <table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">
                                                    <tr style="white-space: nowrap;">
                                                        <th class="center">ID</th>
                                                        <th class="center">信用卡名称</th>
                                                    </tr>
                                                    <c:forEach items="${pd.pageDataCreditName}" var="pageDataCredit">
                                                        <tr>
                                                            <td class='center'>${pageDataCredit.id}</td>
                                                            <td class="center">${pageDataCredit.card_name}</td>
                                                        </tr>
                                                    </c:forEach>
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
    //查看大图片
    function  PictureBigClick(img_qr_code_url){
        top.jzts();
        var diag = new top.Dialog();
        diag.Drag=true;
        diag.URL = "<%=basePath%>channel/goBigPicture.do?img_qr_code_url="+img_qr_code_url;
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
    $(function() {
        //日期框
        $('.date-picker').datepicker({autoclose: true,todayHighlight: true});
        $("#img_qr_code_url").attr("src",'${pd.qr_code_url}');
    });

</script>
</body>
</html>