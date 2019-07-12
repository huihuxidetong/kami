package com.fh.controller.bank;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.bank.BankManager;
/** 
 * 说明：信用卡银行
 * 创建人：FH Q313596790
 * 创建时间：2018-09-27
 */
@Controller
@RequestMapping(value="/bank")
public class BankController extends BaseController {
	
	String menuUrl = "bank/list.do"; //菜单地址(权限用)
	@Resource(name="bankService")
	private BankManager bankService;
	@Autowired
	private PropertiesUtil propertiesUtil;
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public Object save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Bank");
		Map<String, Object> map = new HashMap<String, Object>();
		PageData pd = new PageData();
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		try {
			pd = this.getPageData();
			pd.put("create_user_id",getUserInfo().getUSER_ID());
			pd.put("create_time",new Date());
			pd.put("update_user_id",getUserInfo().getUSER_ID());
			pd.put("update_time",new Date());
			bankService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除Bank");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		bankService.delete(pd);
		out.write("success");
		out.close();
	}

	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	@ResponseBody
	public Object edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"编辑Bank");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			pd.put("update_user_id",getUserInfo().getUSER_ID());
			pd.put("update_time",new Date());
			bankService.edit(pd);
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
	@ResponseBody
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Bank");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = bankService.list(page);	//列出Bank列表
		mv.setViewName("bank/bank_list");
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
		pd.put("sourceFilePath",propertiesUtil.getDefault_img_url());
		pd.put("downloand_source_file_url", propertiesUtil.getFileDownloadUrl() + propertiesUtil.getDefault_img_url());
		mv.setViewName("bank/bank_edit");
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
		pd = bankService.findById(pd);	//根据ID读取
		mv.setViewName("bank/bank_edit");
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
		pd = bankService.findById(pd);//根据ID读取
		mv.setViewName("bank/bank_detail");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}

	/**去信用卡当前页面
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/goNowCredit")
	public ModelAndView goNowCredit(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Channel");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData> varList = bankService.creditList(page);
		mv.setViewName("bank/bank_now_credit");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}

	/**批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Bank");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			bankService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出Bank到excel");
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
		dataMap.put("titles", titles);
		List<PageData> varOList = bankService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).get("ID").toString());	//1
			vpd.put("var2", varOList.get(i).getString("BANK_NAME"));	    //2
			vpd.put("var3", varOList.get(i).getString("BANK_LOGO"));	    //3
			vpd.put("var4", varOList.get(i).getString("BANK_CONTACTS_NAME"));	    //4
			vpd.put("var5", varOList.get(i).getString("BANK_CONTACTS_TEL"));	    //5
			vpd.put("var6", varOList.get(i).getString("BANK_CONTACTS_NOTIC"));	    //6
			vpd.put("var7", varOList.get(i).get("CREATE_USER_ID").toString());	//7
			vpd.put("var8", varOList.get(i).getString("CREATE_TIME"));	    //8
			vpd.put("var9", varOList.get(i).get("UPDATE_USER_ID").toString());	//9
			vpd.put("var10", varOList.get(i).getString("UPDATE_TIME"));	    //10
			vpd.put("var11", varOList.get(i).get("STATUS").toString());	//11
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		return mv;
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
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}

	/**查询所有银行
	 * @throws Exception
	 */
	@RequestMapping(value="/allBank")
	@ResponseBody
	public Object allBank() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"查询所有Bank");
		Map<String, Object> map = new HashMap<String, Object>();
		PageData pd = new PageData();
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		try {
			pd = this.getPageData();
			List<PageData>	varList = bankService.listAll(pd);
			map.put("varList",varList);
			map.put("msg", "success");
			map.put("resultCode", "200");
		}catch (Exception e){
			map.put("msg", "faile");
			map.put("resultCode", "500");
		}
		return AppUtil.returnObject(pd, map);
	}

	/**
	 * 添加银行下的信用卡
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addBankCreditCard")
	@ResponseBody
	public Object addBankCreditCard() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"添加银行下的信用卡");
		Map<String, Object> map = new HashMap<String, Object>();
		PageData pd = new PageData();
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		try {
			pd = this.getPageData();
			pd.put("create_user_id",getUserInfo().getUSER_ID());
			pd.put("create_time",new Date());
			pd.put("update_user_id",getUserInfo().getUSER_ID());
			pd.put("update_time",new Date());
			String cardId = String.valueOf(pd.get("credit_card_id"));
			if(StringUtils.isNotBlank(cardId)){
				bankService.updateBankCreditCard(pd);
			}else {
				bankService.addBankCreditCard(pd);
			}
			map.put("msg", "success");
			map.put("resultCode", "200");
		}catch (Exception e){
			map.put("msg", "faile");
			map.put("resultCode", "500");
		}
		return AppUtil.returnObject(pd, map);
	}
}
