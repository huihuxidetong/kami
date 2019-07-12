<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="common/comm_head.jsp"%>
<base href="<%=basePath%>">
<!-- 下拉框 -->
<link rel="stylesheet" href="static/ace/css/chosen.css" />
<!-- jsp文件头和头部 -->
<%@ include file="system/index/top.jsp"%>
<!-- 日期框 -->
<link rel="stylesheet" href="static/ace/css/datepicker.css" />
</head>
<body class="no-skin">
	<!-- basic scripts -->
	 <script src="static/js/myjs/head.js"></script> 
	<!-- 页面底部js¨ -->
	<%@ include file="system/index/foot.jsp"%>
	<!-- 删除时确认窗口 -->
	<script src="static/ace/js/bootbox.js"></script>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<script type="text/javascript">
		$(top.hangge());//关闭加载状态
		var backURL = '${pd.backURL}'; //跳转的路径	
		var urlId = '${pd.urlId}';
		var urlName = '${pd.urlName}';
		var urlParentId = '${pd.urlParentId}';
		$(function(){
			siMenu(urlId,urlParentId,urlName,"<%=basePath%>"+backURL);
		})
	</script>
</body>
</html>