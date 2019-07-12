package com.fh.controller.credit;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.fh.util.*;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.credit.CreditManager;

/** 
 * 说明：信用卡管理
 * 创建人：FH Q313596790
 * 创建时间：2018-09-26
 */
@Controller
@RequestMapping(value="/credit")
public class CreditController extends BaseController {
	String menuUrl = "credit/list.do"; //菜单地址(权限用)
	@Resource(name="creditService")
	private CreditManager creditService;
	@Autowired
	private PropertiesUtil propertiesUtil;
	@Autowired
	private CreditManager creditManager;
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public Object save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Credit");
		Map<String, Object> map = new HashMap<String, Object>();
		PageData pd = new PageData();
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		try {
			pd = this.getPageData();
			pd.put("credit_user_id",getUserInfo().getUSER_ID());
			pd.put("create_time",new Date());
			pd.put("update_user_id",getUserInfo().getUSER_ID());
			pd.put("update_time",new Date());
			creditService.save(pd);
			map.put("msg", "success");
			map.put("resultCode", "200");
		}catch (Exception e){
			map.put("msg", "faile");
			map.put("resultCode", "500");
		}
		return AppUtil.returnObject(pd, map);
	}

	/**删除
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除Credit");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		creditService.delete(pd);
		out.write("success");
		out.close();
	}

	/**编辑
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	@ResponseBody
	public Object edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"编辑Credit");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			pd.put("update_user",getUserInfo().getUSER_ID());
			pd.put("update_time",new Date());
			creditService.edit(pd);
			map.put("msg","success");
			map.put("resultCode","200");
		}catch ( Exception e){
			map.put("msg", "faile");
			map.put("resultCode", "500");
		}
		return AppUtil.returnObject(pd, map);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Credit");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = creditService.list(page);	//列出Credit列表
		mv.setViewName("credit/credit_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**去新增页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		List<PageData> bankList = creditManager.listAll(new PageData());
		pd.put("bankList",bankList);
		pd.put("creditCardList",bankList);
		pd.put("sourceFilePath",propertiesUtil.getDefault_img_url());
		pd.put("downloand_source_file_url", propertiesUtil.getFileDownloadUrl() + propertiesUtil.getDefault_img_url());
		//等级
		pd.put("CARD_LEVAL_MAP", Constants.CARD_LEVAL);
		//发卡组织
		pd.put("CARD_HAIRPIN_ORGANI",Constants.CARD_HAIRPIN_ORGANI);
		//特性
		pd.put("CREDIT_CHARACTERS",Constants.CREDIT_CHARACTERS);
		//币种
		pd.put("CARD_CURRENCY",Constants.CARD_CURRENCY);
		//卡的标签
		pd.put("card_label",Constants.CARD_LABEL);
		mv.setViewName("credit/credit_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		return mv;
	}
	 /**去修改页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = creditService.findById(pd);	//根据ID读取
		//等级
		pd.put("CARD_LEVAL_MAP", Constants.CARD_LEVAL);
		//发卡组织
		pd.put("CARD_HAIRPIN_ORGANI",Constants.CARD_HAIRPIN_ORGANI);
		//币种
		pd.put("CARD_CURRENCY",Constants.CARD_CURRENCY);
		//特性
		pd.put("CREDIT_CHARACTERS",Constants.CREDIT_CHARACTERS);
		List<PageData> bankList = creditManager.listAll(new PageData());
		pd.put("bankList",bankList);
		pd.put("creditCardList",bankList);
		mv.setViewName("credit/credit_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}

	/**去详情页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goDetail")
	public ModelAndView goDetail()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		//等级
		pd.put("CARD_LEVAL", Constants.CARD_LEVAL);
		//发卡组织
		pd.put("CARD_HAIRPIN_ORGANI",Constants.CARD_HAIRPIN_ORGANI);
		//特性
		pd.put("CREDIT_CHARACTERS",Constants.CREDIT_CHARACTERS);
		//标签
		pd.put("CARD_LABEL",Constants.CARD_LABEL);
		pd = creditService.findById(pd);	//根据ID读取
		mv.setViewName("credit/credit_detail");
		mv.addObject("msg", "detail");
		mv.addObject("pd", pd);
		return mv;
	}

	/**批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Credit");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			creditService.deleteAll(ArrayDATA_IDS);
			pd.put("msg", "ok");
		}else{
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}
	
	 /**导出到excel
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出Credit到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("备注1");	//1
		titles.add("备注2");	//2
		titles.add("备注3");	//3
		titles.add("备注4");	//4
		titles.add("备注5");	//5
		titles.add("备注6");	//6
		titles.add("备注7");	//7
		titles.add("备注8");	//8
		titles.add("备注9");	//9
		titles.add("备注10");	//10
		titles.add("备注11");	//11
		titles.add("备注12");	//12
		titles.add("备注13");	//13
		titles.add("备注14");	//14
		titles.add("备注15");	//15
		titles.add("备注16");	//16
		titles.add("备注17");	//17
		titles.add("备注18");	//18
		titles.add("备注19");	//19
		titles.add("备注20");	//20
		titles.add("备注21");	//21
		titles.add("备注22");	//22
		dataMap.put("titles", titles);
		List<PageData> varOList = creditService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).get("ID").toString());	//1
			vpd.put("var2", varOList.get(i).getString("CARD_LOGO"));	    //2
			vpd.put("var3", varOList.get(i).get("BANK_ID").toString());	//3
			vpd.put("var4", varOList.get(i).get("BANK_CREDIT_CARD_ID").toString());	//4
			vpd.put("var5", varOList.get(i).getString("CARD_FEATURE"));	    //5
			vpd.put("var6", varOList.get(i).getString("CARD_LEVAL"));	    //6
			vpd.put("var7", varOList.get(i).getString("CARD_CURRENCY"));	    //7
			vpd.put("var8", varOList.get(i).getString("CARD_HAIRPIN_ORGANI"));	    //8
			vpd.put("var9", varOList.get(i).getString("FREE_INTEREST_PERIOD"));	    //9
			vpd.put("var10", varOList.get(i).getString("FREE_INTEREST_RULE"));	    //10
			vpd.put("var11", varOList.get(i).getString("APPLY_CONDITION"));	    //11
			vpd.put("var12", varOList.get(i).getString("FIRST_BRUSH_CEREMONY"));	    //12
			vpd.put("var13", varOList.get(i).getString("CARD_LABEL"));	    //13
			vpd.put("var14", varOList.get(i).getString("CARD_PRIVILEGE"));	    //14
			vpd.put("var15", varOList.get(i).getString("OPR_PROCESS"));	    //15
			vpd.put("var16", varOList.get(i).get("CREATE_USER_ID").toString());	//16
			vpd.put("var17", varOList.get(i).getString("CREATE_TIME"));	    //17
			vpd.put("var18", varOList.get(i).get("UPDATE_USER_ID").toString());	//18
			vpd.put("var19", varOList.get(i).getString("UPDATE_TIME"));	    //19
			vpd.put("var20", varOList.get(i).get("STATUS").toString());	//20
			vpd.put("var21", varOList.get(i).get("CARD_LOOK_NUMBER").toString());	//21
			vpd.put("var22", varOList.get(i).get("IS_HOT").toString());	//22
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		return mv;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}

	/**
	 * 上传文件
	 * @param request
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="upload")
	@ResponseBody
	public Object upload(HttpServletRequest request, @RequestParam("file") CommonsMultipartFile file) throws Exception{
		PageData pd = new PageData();
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		String sourceFilePath = sourceFilePath(request,file);
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("sourceFilePath",sourceFilePath);
		paramMap.put("downloandSourceFileUrl",propertiesUtil.getFileDownloadUrl() + sourceFilePath);
		paramMap.put("errno", 0);
		return AppUtil.returnObject(pd, paramMap);
	}

	public String sourceFilePath(HttpServletRequest request,CommonsMultipartFile file) throws IOException {
		String path = request.getSession().getServletContext().getRealPath("/") + "uploadFiles/file/" + file;
		HttpClientBuilder httpBuilder = HttpClientBuilder.create();
		CloseableHttpClient httpclient = httpBuilder.build();
		String filePath = "";
		File f = null;
		try {
			path= path + file.getOriginalFilename();
			f=new File(path);
			file.transferTo(f);
			HttpPost httppost = new HttpPost(propertiesUtil.getFileUploadUrl());
			FileBody bin = new FileBody(f);
			HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("file", bin).build();
			httppost.setEntity(reqEntity);
			CloseableHttpResponse response = httpclient.execute(httppost);
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				resEntity.getContent();
				String body = EntityUtils.toString(resEntity);
				if(StringUtils.isNotBlank(body)){
					JSONObject jsonObject = JSONObject.fromObject(body);
					if("0".equalsIgnoreCase(String.valueOf(jsonObject.get("code")))){
						filePath = String.valueOf(jsonObject.get("data"));
					}
				}
			}
			EntityUtils.consume(resEntity);
		} finally {
			if(f.exists()){
				f.delete();
			}
			httpclient.close();
		}
		return filePath;
	}

	//更新信用卡的状态（上架和下架）
	@RequestMapping(value="/updateCredit")
	@ResponseBody
	public Object updateCredit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"更新信用卡的状态updateCredit");
		Map<String, Object> map = new HashMap<String, Object>();
		PageData pd = new PageData();
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		try {
			pd = this.getPageData();
			creditService.updateCreditState(pd);
			map.put("msg", "success"+pd);
			map.put("resultCode", "200");
		}catch (Exception e){
			map.put("msg", "faile");
			map.put("resultCode", "500");
		}
		return AppUtil.returnObject(pd, map);
	}

	/**查询所有信用卡
	 * @throws Exception
	 */
	@RequestMapping(value="/allCreditCard")
	@ResponseBody
	public Object allCreditCard() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"查询所有Credit");
		Map<String, Object> map = new HashMap<String, Object>();
		PageData pd = new PageData();
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		try {
			pd = this.getPageData();
			List<PageData>	varList = creditManager.findAllCreditCardByBankId(pd);
			map.put("varList",varList);
			map.put("msg", "success");
			map.put("resultCode", "200");
		}catch (Exception e){
			map.put("msg", "faile");
			map.put("resultCode", "500");
		}
		return AppUtil.returnObject(pd, map);
	}

}
