﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
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
</head>
<body class="no-skin">

	<!-- /section:basics/navbar.layout -->
	<div class="main-container" id="main-container">
		<!-- /section:basics/sidebar -->
		<div class="main-content">
			<div class="main-content-inner">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
						<!-- 检索  -->
						<form action="credit/list.do" method="post" name="Form" id="Form">
						<table style="margin-top:5px;">
							<tr>
								<td>
									<div class="nav-search">
										<span class="input-icon">
											<input type="text" placeholder="这里输入关键词" class="nav-search-input" id="nav-search-input" autocomplete="off" name="keywords" value="${pd.keywords }" placeholder="这里输入关键词"/>
											<i class="ace-icon fa fa-search nav-search-icon"></i>
										</span>
									</div>
								</td>
								<td style="vertical-align:top;">
									&nbsp;
									&nbsp;
								</td>
								<c:if test="${QX.cha == 1 }">
									<td style="vertical-align:top;">
										<a class="btn btn-mini btn-primary" onclick="tosearch();">查询</a>
									</td>
								</c:if>
								<td style="vertical-align:top;">
									&nbsp;
									&nbsp;
								</td>
								<c:if test="${QX.add == 1}">
									<td style="vertical-align:top;">
										<a class="btn btn-mini btn-primary" onclick="add();">添加</a>
									</td>
								</c:if>
							</tr>
						</table>
						<!-- 检索  -->
						<table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">	
							<thead>
								<tr>
									<th class="center" style="width:50px;">ID</th>
									<th class="center">所属银行</th>
									<th class="center">信用卡名称</th>
									<th class="center">等级</th>
									<th class="center">币种</th>
									<th class="center">发卡组织</th>
									<th class="center">状态</th>
									<th class="center">操作</th>
								</tr>
							</thead>
							<tbody>
							<!-- 开始循环 -->	
							<c:choose>
								<c:when test="${not empty varList}">
									<c:if test="${QX.cha == 1 }">
									<c:forEach items="${varList}" var="var" varStatus="vs">
										<tr>
											<td class='center' style="width: 30px;">${vs.index+1}</td>
											<td class='center'>${var.bank_name}</td>
											<td class='center'>${var.card_name}</td>
											<td class='center'>${var.cardLeval}</td>
											<td class='center'>${var.card_currency}</td>
											<td class='center'>${var.card_hairpin_organi}</td>
											<td class='center'>
												<c:choose>
													<c:when test="${var.status == 1}">
														上架
													</c:when>
													<c:when test="${var.status == 2}">
														下架
													</c:when>
												</c:choose>
											</td>
											<td class="center">
												<c:if test="${QX.edit != 1 && QX.del != 1 }">
													<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="ace-icon fa fa-lock" title="无权限"></i></span>
												</c:if>
												<div class="hidden-sm hidden-xs btn-group">
													<c:choose>
														<c:when test="${var.status ==1}">
															<c:if test="${QX.cha == 1 }">
																<a class="btn btn-mini btn-success" onclick="detail(${var.id});">详情</a>
															</c:if>
															<c:if test="${QX.cha == 1 }">
																<a class="btn btn-mini btn-success" onclick="downline(${var.id})">下架</a>
															</c:if>
														</c:when>
														<c:when test="${var.status ==2}">
															<c:if test="${QX.cha == 1 }">
																<a class="btn btn-mini btn-success" onclick="checkPass(${var.id})">上架</a>
															</c:if>
															<c:if test="${QX.cha == 1 }">
																<a class="btn btn-mini btn-success" onclick="detail(${var.id});">详情</a>
															</c:if>
															<c:if test="${QX.edit == 1 }">
																<a class="btn btn-mini btn-success" onclick="edit(${var.id})">编辑</a>
															</c:if>
														</c:when>
													</c:choose>
												</div>
												<div class="hidden-md hidden-lg">
													<div class="inline pos-rel">
														<button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
															<i class="ace-icon fa fa-cog icon-only bigger-110"></i>
														</button>
													</div>
												</div>
											</td>
										</tr>
									</c:forEach>
									</c:if>
									<c:if test="${QX.cha == 0 }">
										<tr>
											<td colspan="100" class="center">您无权查看</td>
										</tr>
									</c:if>
								</c:when>
								<c:otherwise>
									<tr class="main_info">
										<td colspan="100" class="center" >没有相关数据</td>
									</tr>
								</c:otherwise>
							</c:choose>
							</tbody>
						</table>
						<div class="page-header position-relative">
						<table style="width:100%;">
							<tr>
								<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
							</tr>
						</table>
						</div>
						</form>
					
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
		</div>
		<!-- /.main-content -->

		<!-- 返回顶部 -->
		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>

	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
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
		//检索
		function tosearch(){
			top.jzts();
			$("#Form").submit();
		}
		$(function() {
			//日期框
			$('.date-picker').datepicker({
				autoclose: true,
				todayHighlight: true
			});
			//下拉框
			if(!ace.vars['touch']) {
				$('.chosen-select').chosen({allow_single_deselect:true}); 
				$(window)
				.off('resize.chosen')
				.on('resize.chosen', function() {
					$('.chosen-select').each(function() {
						 var $this = $(this);
						 $this.next().css({'width': $this.parent().width()});
					});
				}).trigger('resize.chosen');
				$(document).on('settings.ace.chosen', function(e, event_name, event_val) {
					if(event_name != 'sidebar_collapsed') return;
					$('.chosen-select').each(function() {
						 var $this = $(this);
						 $this.next().css({'width': $this.parent().width()});
					});
				});
				$('#chosen-multiple-style .btn').on('click', function(e){
					var target = $(this).find('input[type=radio]');
					var which = parseInt(target.val());
					if(which == 2) $('#form-field-select-4').addClass('tag-input-style');
					 else $('#form-field-select-4').removeClass('tag-input-style');
				});
			}
			//复选框全选控制
			var active_class = 'active';
			$('#simple-table > thead > tr > th input[type=checkbox]').eq(0).on('click', function(){
				var th_checked = this.checked;//checkbox inside "TH" table header
				$(this).closest('table').find('tbody > tr').each(function(){
					var row = this;
					if(th_checked) $(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
					else $(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
				});
			});
		});
		
		//批量操作
		function makeAll(msg){
			bootbox.confirm(msg, function(result) {
				if(result) {
					var str = '';
					for(var i=0;i < document.getElementsByName('ids').length;i++){
					  if(document.getElementsByName('ids')[i].checked){
					  	if(str=='') str += document.getElementsByName('ids')[i].value;
					  	else str += ',' + document.getElementsByName('ids')[i].value;
					  }
					}
					if(str==''){
						bootbox.dialog({
							message: "<span class='bigger-110'>您没有选择任何内容!</span>",
							buttons: 			
							{ "button":{ "label":"确定", "className":"btn-sm btn-success"}}
						});
						$("#zcheckbox").tips({
							side:1,
				            msg:'点这里全选',
				            bg:'#AE81FF',
				            time:8
				        });
						return;
					}else{
						if(msg == '确定要删除选中的数据吗?'){
							top.jzts();
							$.ajax({
								type: "POST",
								url: '<%=basePath%>credit/deleteAll.do?tm='+new Date().getTime(),
						    	data: {DATA_IDS:str},
								dataType:'json',
								//beforeSend: validateData,
								cache: false,
								success: function(data){
									 $.each(data.list, function(i, list){
											tosearch();
									 });
								}
							});
						}
					}
				}
			});
		};
        //新增
        function add() {
            window.location.href="<%=basePath%>credit/goAdd.do";
        }
        //编辑
        function edit(id){
            window.location.href="<%=basePath%>credit/goEdit.do?id="+id;
        }
        //详情
		function detail(id) {
            window.location.href="<%=basePath%>credit/goDetail.do?id="+id;
        }
        //上架
        function checkPass(id) {
            bootbox.confirm("确定要上架吗?",function (result) {
                if (result){
                    $.ajax({
                        type: "POST",
                        url: "<%=basePath%>credit/updateCredit.do",
                        data: {
                            id: id,
                            status: "1"
                        },
                        dataType: "json",
                        success:function (data) {
                            if(data.resultCode == 200){
                                window.location.href="<%=basePath%>credit/list.do";
                            }
                        },
                        error:function () {
                            alert("发生错误");
                        }
                    });
                }
            })
        }
        //下架
        function downline(id) {
            bootbox.confirm("确定要下架吗?",function (result){
                if (result){
                    $.ajax({
                        type: "POST",
                        url: "<%=basePath%>credit/updateCredit.do",
                        data: {
                            id: id,
                            status: "2"
                        },
                        dataType: "json",
                        success:function (data) {
                            if(data.resultCode == 200){
                                window.location.href="<%=basePath%>credit/list.do";
                            }
                        },
                        error:function () {
                            alert("发生错误");
                        }
                    });
                }
            })
        }
	</script>
</body>
</html>