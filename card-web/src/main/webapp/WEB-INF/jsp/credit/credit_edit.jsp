<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="../common/comm_head.jsp"%>
	<base href="<%=basePath%>">
	<!-- 下拉框 -->
	<link rel="stylesheet" href="static/ace/css/chosen.css"/>
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
<ul id="myTab" class="nav nav-tabs">
	<li class="active">
		<a href="#tjxyk" data-toggle="tab">添加信用卡</a>
	</li>
</ul>
<form action="credit/upload.do" id="fileForm" enctype="multipart/form-data" >
	<input type="file" name="file" id="file"  style="display:none;"/>
</form>
<form action="credit/${msg}.do" class="form-horizontal" name="Form" id="Form" method="post">
	<input type="hidden" name="bank_credit_card_id" id="bank_credit_card_id" value="${pd.bank_credit_card_id}"/>
	<input name="edithtml" id="edithtml" type="hidden"/>
	<input name="sourceFilePath" id="sourceFilePath" value="${pd.sourceFilePath}" type="hidden"/>
	<input type="hidden" id="bank_name" name="bank_name" value="${pd.bank_name}"/>
	<input type="hidden" name="card_label" id="card_label" value="${pd.card_label}">
	<input type="hidden" name="card_choose_label" id="card_choose_label" value="${pd.card_choose_label}">
	<input type="hidden" name="id" id="id" value="${pd.id}"/>
	<input type="hidden" name="card_hairpin_organi_ids" id="card_hairpin_organi_ids" value=""/>
	<input type="hidden" name="credit_characters_ids" id="credit_characters_ids" value=""/>
	<input type="hidden" name="cost_str" id="cost_str" value=""/>
	<input type="hidden" name="question_str" id="question_str" value=""/>
	<input type="hidden" name="opr_process_str" id="opr_process_str" value=""/>


	<ul id="myTab1" class="nav nav-tabs">
		<div style="text-align: center;">
			<a style="margin-bottom: -30px;" href="javascript:void(0)" class="btn btn-mini btn-primary" onclick="cancel();">取消</a>
			<a style="margin-bottom: -30px;" href="javascript:void(0)" class="btn btn-mini btn-success" onclick="save();">保存</a>
		</div>
		<li class="active">
			<a href="#jbxx" data-toggle="tab">基本信息</a>
		</li>
		<li><a href="#zxtq" data-toggle="tab">专享特权</a></li>
		<li><a href="#fysm" data-toggle="tab">费用</a><span style="margin-bottom: 500px;position:absolute;" id="creditCost_check"></span></li>
		<li><a href="#bklc" data-toggle="tab">办卡流程</a><span id="creditOprProcess_Check"></span></li>
		<li><a href="#cjwt" data-toggle="tab">常见问题</a><span style="margin-bottom: 500px;position:absolute;" id="creditQuestion_check"></span></li>
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
													<img src="" id="fileUrl" title="点击上传" style="width:280px;height:180px;cursor: pointer"/>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label " style="text-align: left">所属银行：</label>
												<div class="col-sm-9" >
													<select id="bank_id" onchange="getCreditCard()"  name="bank_id" data-skipstyle="data-skipstyle"  class="col-xs-10 col-sm-7" style="width: 360px">

													</select>
												</div>
											</div>
                                            <div class="form-group">
												<label class="col-sm-3 control-label" style="text-align: left">信用卡名称：</label>
												<div class="col-sm-9" >
													<select id="credit_card"  name="credit_card" data-skipstyle="data-skipstyle"  class="col-xs-10 col-sm-7" style="width: 360px">
														<option value='' selected='selected' >请选择</option>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label " style="text-align: left">特色：</label>
												<div class="col-sm-9">
													<input id="card_feature" name="card_feature" placeholder="请填写20个字以内的特色" maxlength="20"  STYLE="width: 360px" class="col-xs-10 col-sm-7" type="text" value="${pd.card_feature}">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label " style="text-align: left">等级：</label>
												<div class="col-sm-9">
													<select id="card_leval" name="card_leval"  class="col-xs-10 col-sm-7" style="width: 360px;text-align: left">
														<c:forEach items="${pd.CARD_LEVAL_MAP}" var="card_leval">
															<option value="${card_leval.key}" <c:if test="${pd.card_leval == card_leval.value}">selected="selected"</c:if> >${card_leval.value}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label " style="text-align: left">币种：</label>
												<div class="col-sm-9">
													<select id="card_currency" name="card_currency" class="col-xs-10 col-sm-7" style="width: 360px;text-align: left">
														<c:forEach items="${pd.CARD_CURRENCY}" var="card_currency">
															<option value="${card_currency.key}" selected="selected">${card_currency.value}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										<div class="form-group">
											<label class="col-sm-3 control-label " style="text-align: left">发卡组织:</label>
											<div class="col-sm-9">
												<select style="width:170px!important;" id="card_hairpin_organi" name="card_hairpin_organi" class="selectpicker show-tick chosen-select show-menu-arrow" multiple data-max-option="1" data-live-search="true">
													<c:forEach items="${pd.CARD_HAIRPIN_ORGANI}" var="card_hairpin_organi">
														<option value="${card_hairpin_organi.key}" <c:if test="${fn:contains(pd.card_hairpin_organi,card_hairpin_organi.key)}"> selected="selected" </c:if>>${card_hairpin_organi.value}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label " style="text-align: left">免息期限：</label>
											<div class="col-sm-9">
												<select id="free_interest_period"  name="free_interest_period" data-skipstyle="data-skipstyle"  class="col-xs-10 col-sm-7" style="width: 360px">
													<option value="" >请选择</option>
													<option value="50天" <c:if test="${pd.free_interest_period == '50天'}"> selected="selected" </c:if>>50天</option>
													<option value="56天" <c:if test="${pd.free_interest_period == '56天'}"> selected="selected" </c:if>>56天</option>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label " style="text-align: left">积分规则：</label>
											<div class="col-sm-9">
												<td style="padding-left:2px;">
													<input name="integral_rule" id="integral_rule" STYLE="width: 360px" maxlength="20" class="col-xs-10 col-sm-7" type="text" value="${pd.integral_rule}"/>
												</td>
											</div>
										</div>
											<div class="form-group">
												<label class="col-sm-3 control-label " style="text-align: left">申请条件：</label>
												<div class="col-sm-9" >
													<td style="padding-left:2px;">
														<input name="apply_condition" id="apply_condition" maxlength="30" STYLE="width: 360px" class="col-xs-10 col-sm-7" type="text" value="${pd.apply_condition}"/>
													</td>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label " style="text-align: left">信用卡链接：</label>
												<div class="col-sm-9" >
													<td style="padding-left:2px;">
														<input name="open_credit_card_url" id="open_credit_card_url" maxlength="100" STYLE="width: 360px" class="col-xs-10 col-sm-7" type="text" value="${pd.open_credit_card_url}"/>
													</td>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label " style="text-align: left">开卡/首刷礼：</label>
												<div class="col-sm-9">
													<td style="padding-left:2px;">
														<input name="first_brush_ceremony" id="first_brush_ceremony" maxlength="30" STYLE="width: 360px" class="col-xs-10 col-sm-7" type="text" value="${pd.first_brush_ceremony}"/>
													</td>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label " style="text-align: left">特性:</label>
												<div class="col-sm-9">
													<select style="width:170px!important;" id="credit_characters" name="credit_characters" class="selectpicker show-tick chosen-select show-menu-arrow" multiple data-max-option="1" data-live-search="true">
														<c:forEach items="${pd.CREDIT_CHARACTERS}" var="credit_characters">
															<option value="${credit_characters.key}" <c:if test="${fn:contains(pd.credit_characters,credit_characters.key)}"> selected="selected" </c:if>>${credit_characters.value}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label" style="text-align: left">标签:</label>
												<div class="col-sm-9" id="card_label_list" style="width: 360px;">
													<c:forEach items="${pd.card_label}"  var="card_label" varStatus="index">
														<label style="padding-left: 8px;padding-top:7px;">
															<input name="card_labels" class="ace ace-checkbox-2"  type="checkbox" value="${card_label}" <c:if test="${fn:contains(pd.card_choose_label,card_label)}"> checked="checked" </c:if> >	<span class="lbl">${card_label}</span>
														</label>
													</c:forEach>
													<input type="button" onclick="javascript:OpenDiv();" id="selflabel" class="btn btn-primary btn-mini" value="自定义标签"/>
												</div>
											</div>
											<div id="label" style="display: none; width: 280px; height: 180px;border: 1px solid grey; float: right;margin-top: -200px; margin-right: -180px;">
												<div class="form-group">
													<div style="white-space: nowrap; margin-left: 100px;font-size: 16px;">添加自定义标签&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<a style="text-decoration: none;" href="javascript:CloseDiv();"><span style="color: grey;">X</span></a>
													</div><br/>
													<div class="col-sm-9" style="margin-left: 50px;">
														<input name="custom_label" id="custom_label" maxlength="10"  STYLE="width: 200px;border-radius: 16px"  type="text" placeholder="输入标签名称">
													</div>
													<input type="button" style="margin-left: 100px; margin-top: 40px;" href="javascript:void(0)" onclick="addCustomLabel()" class="btn btn-primary btn-circle" value="添加标签"/>
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
		<div class="tab-pane fade" id="zxtq">
			<div class="main-container">
				<!-- /section:basics/sidebar -->
				<div class="main-content">
					<div class="main-content-inner">
						<div class="page-content">
							<div class="row">
								<div class="col-xs-12">
									<!-- 存放生成的hmlt开头-->
									<div class="col-md-5">
										<div class="form-group">
											<textarea  style="width: 360px;height: 200px" id="card_privilege" name="card_privilege" placeholder="请填写200个字以内的特权内容" rows="2"   maxlength="200" onkeyup="checkNum()">${pd.card_privilege}</textarea>
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
												<td style="vertical-align:top;">
													<a class="btn btn-mini btn-primary" onclick="addOpr();">添加</a>
												</td>
												<td style="vertical-align:top;">
													<a class="btn btn-mini btn-primary" id="opr_delete">删除</a>
												</td>
												<table id="simple-table3" class="table table-striped table-bordered table-hover" style="margin-top:5px;">
													<thead>
													<tr>
														<th class="center" style="width:35px;">
															<label class="pos-rel"><input type="checkbox" class="ace" /><span class="lbl"></span></label>
														</th>
														<th class="center">流程名称</th>
													</tr>
													</thead>
													<div id="divOpr">
														<tbody id="table-values">
															<c:forEach items="${pd.opr_processList}" var="opr_process">
																<tr>
																	<td class='center'>
																		<label class="pos-rel"><input type='checkbox' name='ids' value="${var.id}" class="ace checkbox" /><span class="lbl"></span></label>
																	</td>
																	<td style="text-align: center;">
																		<input type="text" id="opr_process" name="opr_process"  maxlength="5"  value="${opr_process}"  placeholder="请填写5个字以内的流程名称"/>
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
												<td style="vertical-align:top;">
													<a class="btn btn-mini btn-primary" onclick="addQue();">添加</a>
												</td>
												<div id="divQue">
													<table id="simple-table2" class="table table-striped table-bordered table-hover" style="margin-top:5px;">
														<tbody id="simple-table2-value">
														<c:forEach items="${pd.creditQues}" var="creditQues">
															<tr class="form-group www" >
																<td>
																	<label class="col-sm-3 control-label " style="text-align: left">问题：</label>
																	<div class="col-sm-6" style="position: relative">
																		<input id="question" name="question"  placeholder="请填写30个字以内的问题描述" STYLE="width: 300px" maxlength="30" class="col-xs-10 col-sm-7" type="text" value="${creditQues.question}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<button id="que" href="javascript:cloError();" style="position: absolute;top:0;right:45%" class="close" ><span>&times;</span></button>
																	</div>
																	<hr>
																	<label class="col-sm-3 control-label " style="text-align: left">回答：</label>
																	<div class="col-sm-9">
																		<textarea  style="width: 360px;height: 150px" id="answer" name="answer" minlength="100" placeholder="请填写100个字以内的回答内容" rows="2"  maxlength="200" onkeyup="checkNumQues()">${creditQues.answer}</textarea>
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
		</div>
		<div class="tab-pane fade" id="fysm">
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
											<div id="zhongxin" style="display: block;">
												<td style="vertical-align:top;">
													<a class="btn btn-mini btn-primary" onclick="addCost();">添加</a>
												</td>
												<td style="vertical-align: top;">
													<a class="btn btn-mini btn-primary" id="cost_delete">删除</a>
												</td>
												<table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">
													<thead>
													<tr>
														<th class="center" style="width:35px;">
															<label class="pos-rel"><input type="checkbox" class="ace" id="zcheckbox" /><span class="lbl"></span></label>
														</th>
														<th class="center">费用</th>
														<th class="center">费用说明</th>
														<th class="center">优惠措施</th>
													</tr>
													</thead>
													<div id="divCost">
														<tbody id="table-value">
														<c:forEach items="${pd.creditCost}" var="creditCost">
															<tr>
																<td class='center'>
																	<label class="pos-rel"><input type='checkbox' name='ids' value="${var.id}" class="ace checkbox" /><span class="lbl"></span></label>
																</td>
																<td style="text-align: center;">
																	<input style="text-align: center;" type="text" id="cost_type" name="cost_type" maxlength="5" value="${creditCost.cost_type}"   placeholder="请填写5个字以内的费用描述"/>
																</td>
																<td style="text-align: center;">
																	<input style="text-align: center" type="text" id="cost_explain" name="cost_explain"  maxlength="30" value="${creditCost.cost_explain}"  placeholder="请填写30个字以内的费用说明"/>
																</td>
																<td style="text-align: center;">
																	<input type="text" id="cost_measures" name="cost_measures"  maxlength="30" value="${creditCost.cost_measures}"   placeholder="请填写30个字以内的优惠措施"/>
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
    $(top.hangge());//关闭加载状态
    var downloand_source_file_url = "${pd.downloand_source_file_url}";
    $("#fileUrl").attr("src",downloand_source_file_url);

    $(function() {
        $.ajax({
            type: "POST",
            url: "<%=basePath%>/bank/allBank.do",
            dataType: "json",
            success:function (data) {
                $('#bank_id').append("<option value='' selected='selected' >" + '请选择' + "</option>");
                var bank_id = "${pd.bank_id}"
                for (var i = 0; i < data.varList.length; i++) {
                    if(bank_id == data.varList[i].id){
                        $('#bank_id').append("<option value='" + data.varList[i].id + "' selected='selected'  >" + data.varList[i].bank_name + "</option>");
                        getCreditCard();
                    }else {
                        $('#bank_id').append("<option value='" + data.varList[i].id + "' >" + data.varList[i].bank_name + "</option>");
                    }
                }
            },
            error:function () {
                alert("发生错误");
            }
        });

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
        $('#simple-table3 > thead > tr > th input[type=checkbox]').eq(0).on('click', function(){
            var th_checked = this.checked;//checkbox inside "TH" table header
            $(this).closest('table').find('tbody > tr').each(function(){
                var row = this;
                if(th_checked) $(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
                else $(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
            });
        });

    });
    function  getCreditCard() {
        var bank_id = $("#bank_id").val();
        $("#credit_card").empty();
        $.ajax({
            type: "post",
            url:  "<%=basePath%>/credit/allCreditCard?bank_ids="+bank_id,
            success: function (data) {
                $('#credit_card').append("<option value='' selected='selected' >" + '请选择' + "</option>");
                for (var i = 0; i < data.varList.length; i++) {
                    for (var i = 0; i < data.varList.length; i++) {
                        var bank_credit_card_id = "${pd.bank_credit_card_id}"
						if(bank_credit_card_id == data.varList[i].id){
                            $('#credit_card').append("<option value='" + data.varList[i].id + "'  selected='selected'>" + data.varList[i].card_name + "</option>");
                        }else{
                            $('#credit_card').append("<option value='" + data.varList[i].id + "' >" + data.varList[i].card_name + "</option>");
                        }
                    }
                }
            },
            error: function () {
                alert("加载失败");
            }
        });
    }
    function OpenDiv(){
        document.getElementById("label").style.display="block";
        document.getElementById("tag").style.display="block";
    }
    function CloseDiv(){
        document.getElementById("label").style.display="none";
        document.getElementById("tag").style.display="block";
    }
    function cloError() {
        document.getElementById("error").style.display="none";
        document.getElementById("tag").style.display="block";
    }
    //添加费用
    function addCost() {
		var html = "<tr>\n" +
			"<td class='center'>\n" +
			"\t<label class=\"pos-rel\"><input type='checkbox' name='ids' class=\"checkbox\"  id=\"ids\" value=\"\" class=\"ace\" /><span class=\"lbl\"></span></label>\n" +
			"</td>\n" +
			"<td style=\"text-align: center;\">\n" +
			"\t<input style=\"text-align: center;\" type=\"text\" id=\"cost_type\" name=\"cost_type\" maxlength='5' value=\"\"  placeholder=\"请填写5个字以内的费用描述\"/>\n" +
			"</td>\n" +
			"<td style=\"text-align: center;\">\n" +
			"\t<input style=\"text-align: center\" type=\"text\" id=\"cost_explain\" name=\"cost_explain\" maxlength='30'  value=\"\" placeholder=\"请填写30个字以内的费用说明\"/>\n" +
			"</td>\n" +
			"<td style=\"text-align: center;\">\n" +
			"\t<input type=\"text\" id=\"cost_measures\" name=\"cost_measures\" maxlength='30'  placeholder=\"请填写30个字以内的优惠措施\"/>\n" +
			"</td>\n" +
			"</tr>";
		 $("#table-value").append(html);
		document.getElementById("divCost").style.display = "none";

    }
    //费用删除
    $(function(){
        $("#cost_delete").click(function(){
            for(var i = $(".checkbox").length;i > 0; i--)
            {
                if($(".checkbox").eq(i-1).prop("checked") === true){
                    $(".checkbox").eq(i-1).parent().parent().parent().remove();
                }
            }
        })
    })
	//添加流程
    function addOpr() {
        if(validateLable()){
            var html ="<tr>\n" +
                "<td class='ceanter'>\n" +
                "<label class=\"pos-rel\"><input type='checkbox' name='fd' id=\"fd\" value=\"${var.id}\" class=\"checkbox\" /><span class=\"lbl\"></span></label>\n" +
                "</td>\n" +
                "<td style=\"text-align: center;\">\n" +
                "<input type=\"text\" id=\"opr_process\" name=\"opr_process\"  maxlength=\"5\" placeholder=\"这里输入流程名称\"/>\n" +
                "</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>";
            $("#table-values").append(html);
            document.getElementById("divOpr").style.display = "none";
        }
    }
    //流程删除
    $(function () {
		$("#opr_delete").click(function () {
			for (var i = $(".checkbox").length;i >0;i--){
			    if ($(".checkbox").eq(i-1).prop("checked") == true){
			        $(".checkbox").eq(i-1).parent().parent().parent().remove();
				}
			}
        });
    });
    function addQue() {
		var html =" \t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr class=\"form-group\">\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<label class=\"col-sm-3 control-label \" style=\"text-align: left\">问题：</label>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"col-sm-6\" style=\"position: relative\">\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<input id=\"question\" name=\"question\"  maxlength=\"30\" placeholder=\"请填写30个字以内的问题描述\" STYLE=\"width: 300px\" class=\"col-xs-10 col-sm-7\" type=\"text\" value=\"\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<button id=\"que\" href=\"javascript:cloError();\" style=\"position: absolute;top:0;right:45%\" class=\"close\" ><span>&times;</span></button>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<hr>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<label class=\"col-sm-3 control-label \" style=\"text-align: left\">回答：</label>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"col-sm-9\">\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<textarea  style=\"width: 360px;height: 150px\"  minlength=\"100\" id=\"answer\" name=\"answer\" placeholder=\"请填写100个字以内的回答内容\" rows=\"2\"  maxlength=\"200\" onkeyup=\"checkNum()\"></textarea>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span id=\"changeNum1\">0</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span>/</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span>200</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>";
		$("#simple-table2-value").append(html);
    }

    //取消
    function cancel(){
        if(null == currentPage || "" == currentPage){
            currentPage = 1;
        }
        if(null == showCount || "" == showCount){
            showCount = 10;
        }
        window.location.href= "<%=basePath%>credit/list.do?&currentPage=" + currentPage + "&showCount=" + showCount;
    }
    function save(){
        //流程
        var opr_process_str = "";
        $("input[name=opr_process]").each(function(){
            opr_process_str += $(this).val() +  ",";
        });
        if(null != opr_process_str && "" != opr_process_str){
            $("#opr_process_str").val(opr_process_str.substring(0,opr_process_str.length-1));
        }
        if(validateForm()) {
            //标签开始
            var card_label = '';
            var card_choose_label ='';

            $("input[name=card_labels]").each(function(){
                card_label += $(this).val() +  ",";
            });

            if(null != card_label && "" != card_label){
                card_label = card_label.substring(0,card_label.length-1);
			}

            $("input[name=card_labels]:checked").each(function(){
                card_choose_label += $(this).val() +  ",";
            });
            if(null != card_choose_label && "" != card_choose_label){
                card_choose_label = card_choose_label.substring(0,card_choose_label.length-1);
            }

            $("#card_label").val(card_label);
            $("#card_choose_label").val(card_choose_label);
            //标签结束
            var html = editor.txt.html();
            $("#edithtml").val(html.toString());
            var url = '<%=basePath%>'+$("#Form").attr("action")
			$("#card_hairpin_organi_ids").val($("#card_hairpin_organi").val());
            $("#credit_characters_ids").val($("#credit_characters").val());
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
                        window.location.href= '<%=basePath%>credit/list.do?currentPage=' + currentPage + "&showCount=" + showCount;
                    }
                },
                error : function() {
                    bootbox.alert("异常！");
                }
            });
        }
    }

    function loadCreditCardCost(){
        var tab=document.getElementById("table-value");
        var rows=tab.rows;
        var data = [];
        for(var i=0;i<rows.length;i++){ //遍历表格的行
            var rowstr = rows[i];
            rowstr = new Object()
            rowstr.cost_type = rows[i].cells[1].getElementsByTagName("input")[0].value;;
            rowstr.cost_explain = rows[i].cells[2].getElementsByTagName("input")[0].value;;
            rowstr.cost_measures = rows[i].cells[3].getElementsByTagName("input")[0].value;;
            data.push(rowstr);
        }
        var cost_str = JSON.stringify(data);
        $("#cost_str").val(cost_str);
    }

    function loadCreditCardQuestion(){
        var tab=document.getElementById("simple-table2-value");
        var rows=tab.rows;
        var data = [];
        for(var i=0;i<rows.length;i++){ //遍历表格的行
            var rowstr = rows[i];
            rowstr = new Object()
			rowstr.question = rows[i].cells[0].getElementsByTagName("input")[0].value;
			rowstr.answer = rows[i].cells[0].getElementsByTagName("textarea")[0].value;
			data.push(rowstr);
        }
        var question_str = JSON.stringify(data);
        $("#question_str").val(question_str);
    }
    function validateForm(){
        //加载费用
        loadCreditCardCost();
        //加载问题
        loadCreditCardQuestion();

        //常见问题校验
        // var queval = $("#question_str").val();
        // if(queval == "[]" || null == queval || "" == queval ){
         //    $("#creditQuestion_check").tips({
         //        side:3,
         //        msg:'请输入常见问题',
         //        bg:'red',
         //        time:2
         //    });
         //    $("#creditQuestion_check").focus();
         //    return false;
        // }
        var str = $("#bank_id").val();
        if(str==""){
            $("#bank_id").tips({
                side:3,
                msg:'请选择所属银行',
                bg:'red',
                time:2
            });
            $("#bank_id").focus();
            return false;
        }
        var str = $("#credit_card").val();
        if(str==""){
            $("#credit_card").tips({
                side:3,
                msg:'请选择信用卡名称',
                bg:'red',
                time:2
            });
            $("#credit_card").focus();
            return false;
        }
        var val = $("#card_feature").val();
        if(val==""){
            $("#card_feature").tips({
                side:3,
                msg:'请填写20个字以内的特色',
                bg:'red',
                time:2
            });
            $("#card_feature").focus();
            return false;
        }else{
            if(!validateLength(str,40)){
                $("#card_feature").tips({
                    side:3,
                    msg:'最多填写40个字符',
                    bg:'red',
                    time:2
                });
                $("#card_feature").focus();
                return false;
            }
        }

        //专享特权
        var val = $("#card_privilege").val();
        if(val==""){
            $("#card_privilege").tips({
                side:3,
                msg:'请填写200个字以内的特权内容',
                bg:'red',
                time:2
            });
            $("#card_privilege").focus();
            return false;
        }

        //费用校验
        var costval = $("#cost_str").val();
        if(costval == "[]" || null == costval || "" == costval ){
            $("#creditCost_check").tips({
                side:3,
                msg:'请输入费用',
                bg:'red',
                time:2
            });
            $("#creditCost_check").focus();
            return false;
        }

        //流程校验
        var oprprosssval = $("#opr_process_str").val();
        if(null == oprprosssval || "" == oprprosssval){
            $("#creditOprProcess_Check").tips({
                side:3,
                msg:'请输入流程名称',
                bg:'red',
                time:2
            });
            $("#creditOprProcess_Check").focus();
            return false;
        }
        //办卡流程
        var val = $("#opr_process").val();
        if(val==""){
            $("#opr_process").tips({
                side:3,
                msg:'请填写5个字以内的流程名称',
                bg:'red',
                time:2
            });
            $("#opr_process").focus();
            return false;
        }else{
            if(!validateLength(str,10)){
                $("#opr_process").tips({
                    side:3,
                    msg:'最多填写10个字符',
                    bg:'red',
                    time:2
                });
                $("#opr_process").focus();
                return false;
            }
        }
        //问题描述
        // var val = $("#question").val();
        // if(val==""){
        //     $("#question").tips({
        //         side:3,
        //         msg:'请填写30个字以内的问题描述',
        //         bg:'red',
        //         time:2
        //     });
        //     $("#question").focus();
        //     return false;
        // }else{
        //     if(!validateLength(str,60)){
        //         $("#question").tips({
        //             side:3,
        //             msg:'最多填写60个字符',
        //             bg:'red',
        //             time:2
        //         });
        //         $("#question").focus();
        //         return false;
        //     }
        // }

        //问题回答
        // var val = $("#answer").val();
        // if(val==""){
        //     $("#answer").tips({
        //         side:3,
        //         msg:'请填写100个字以内的问题回答',
        //         bg:'red',
        //         time:2
        //     });
        //     $("#answer").focus();
        //     return false;
        // }else{
        //     if(!validateLength(str,200)){
        //         $("#answer").tips({
        //             side:3,
        //             msg:'最多填写200个字符',
        //             bg:'red',
        //             time:2
        //         });
        //         $("#answer").focus();
        //         return false;
        //     }
        // }
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

   function checkNumQues() {
	   var str=$("#answer").val();
	   var len=0;
	   for (var i = 0; i < str.length; i++) {
	       len++;
	   }
	   $("changeNum1").html(len);
   }

    function addCustomLabel() {
        var custom_lable = $("#custom_label").val();
        if(validateLable(custom_lable)) {
            var html = '<label style="padding-left: 8px;padding-top:7px;" ><input name="card_labels" class="ace ace-checkbox-2"  type="checkbox" value="'+custom_lable+'"  ><span class="lbl">'+custom_lable+'</span> </label>';
           /* $("#card_label_list").append(html);*/
			$("#selflabel").before(html);
            document.getElementById("label").style.display = "none";
        }
    }

    function validateLable(custom_lable){
        var success = true;
        $("input[name=card_labels]").each(function(){
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
    //问题删除
    $("#simple-table2").delegate("button","click",function () {
        if($(".form-group").length>1){
            $(this).parent().parent().parent().remove();
            for (var i = $(".form-group").length; i > 0; i--)
            {
                $(".form-group").eq(i-1).find("label")[0].innerHTML="问题"+i+"";
                $(".form-group").eq(i-1).find("label")[1].innerHTML="回答"+i+""
            }
        }
    });
</script>
</body>