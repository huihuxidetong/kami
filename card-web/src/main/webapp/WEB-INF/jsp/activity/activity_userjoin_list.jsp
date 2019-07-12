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
<form action="activity/joinActivitylist.do" class="form-horizontal"  name="Form" id="Form" method="post">
    <input type="hidden" name="id" id="id" value="${pd.id}" />
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#jbxx" data-toggle="tab">活动名单</a>
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
											<input type="text"  class="nav-search-input" id="nav-search-input" autocomplete="off" name="keywords" value="${pd.keywords}" placeholder="这里输入关键词"/>
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
                                    </div>
                                    </td>
                                    <div class="page-header position-relative" style="width: 100%">
                                        <span>已有&nbsp;${pd.totalJoinUserNum}&nbsp;人参与活动</span>
                                        <table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">
                                            <thead>
                                            <tr>
                                                <th class="center">参与人</th>
                                                <th class="center">联系电话</th>
                                                <th class="center">信用卡号</th>
                                                <th class="center">参与时间</th>
                                            </tr>
                                            </thead>
                                            <c:forEach items="${varList}" var="var" varStatus="vs">
                                                <tr>
                                                    <td class="center">${var.join_user_name}</td>
                                                    <td class="center">${var.join_user_mobile}</td>
                                                    <td class="center">${var.join_user_credit_card}</td>
                                                    <td class="center">${var.create_time}</td>
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
    $(top.hangge());//关闭加载状态
    function tosearch() {
        top.jzts();
        $("#Form").submit();
    }
    function add() {
        document.getElementById("div1").style.display="block";
        document.getElementById("tag").style.display="block";
    }
    function CloseDiv(){
        document.getElementById("div1").style.display="none";
        document.getElementById("tag").style.display="block";
    }
    //删除
    function del(id){
        bootbox.confirm("确定要删除吗?", function(result) {
            if(result) {
                top.jzts();
                var url = "<%=basePath%>bank/delete.do?id="+id+"&tm="+new Date().getTime();
                $.get(url,function(data){
                    tosearch();
                });
            }
        });
    }
</script>
</body>