package com.fh.controller.activity;
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
import com.fh.service.activity.ActivityManager;
/** 
 * 说明：活动管理
 * 创建人：FH Q313596790
 * 创建时间：2018-09-26
 */
@Controller
@RequestMapping(value="/activity")
public class ActivityController extends BaseController {
	
	String menuUrl = "activity/list.do"; //菜单地址(权限用)
	@Resource(name="activityService")
	private ActivityManager activityService;
	@Autowired
	private ActivityManager activityManager;
	@Autowired
	private PropertiesUtil propertiesUtil;
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public Object save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增activity");
		Map<String, Object> map = new HashMap<String, Object>();
		PageData pd = new PageData();
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		try {
			pd = this.getPageData();
			pd.put("create_user_id",getUserInfo().getUSER_ID());
			pd.put("create_time",new Date());
			pd.put("update_user_id",getUserInfo().getUSER_ID());
			pd.put("update_time",new Date());
			activityService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除Activity");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		activityService.delete(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"编辑activity");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			pd.put("update_user",getUserInfo().getUSER_ID());
			pd.put("update_time",new Date());
			activityService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表Activity");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData> varList = activityService.list(page);	//列出Activity列表
		mv.setViewName("activity/activity_list");
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
		pd.put("downloand_source_file_url", propertiesUtil.getFileDownloadUrl() + File.separator + propertiesUtil.getDefault_img_url());
		List<PageData> activityList = activityManager.listAll(new PageData());
		pd.put("activityList",activityList);
		pd.put("biaozhunCredit",activityList);
		pd.put("status",1);
		pd.put("is_unlimitedt_bank_credit_card",0);
		//活动类型
		pd.put("ACTIVITY_TYPE", Constants.ACTIVITY_TYPE);
		//活动的自定义标签
		pd.put("activity_custom_lable",Constants.ACTIVITY_CUSTOM_LABLE);
		mv.setViewName("activity/activity_edit");
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
		pd = activityService.findById(pd);	//根据ID读取
		List<PageData> activityList = activityManager.listAll(new PageData());
		pd.put("activityList",activityList);
		pd.put("biaozhunCredit",activityList);
		//活动类型
		pd.put("ACTIVITY_TYPE", Constants.ACTIVITY_TYPE);
		//活动的自定义标签
		mv.setViewName("activity/activity_edit");
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
	  	pd = activityService.findById(pd);	//根据ID读取
		mv.setViewName("activity/activity_detail");
		mv.addObject("msg", "edit");
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Activity");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			activityService.deleteAll(ArrayDATA_IDS);
			pd.put("msg", "ok");
		}else{
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}
	/**更新状态
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/updateActivity")
	@ResponseBody
	public Object updateActivity() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"更新状态ApplyCooperate");
		Map<String, Object> map = new HashMap<String, Object>();
		PageData pd = new PageData();
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		try {
			pd = this.getPageData();
			activityService.updateActivity(pd);
			map.put("msg", "success");
			map.put("resultCode", "200");
		}catch (Exception e){
			map.put("msg", "faile");
			map.put("resultCode", "500");
		}
		return AppUtil.returnObject(pd, map);
	}

	/**活动名单列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/joinActivitylist")
	public ModelAndView joinActivitylist(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Activity");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData> varList = activityService.activityList(page);
		Integer totalJoinUserNum = activityService.findTotalJoinUserNum(pd);
		pd.put("totalJoinUserNum",totalJoinUserNum);
		mv.setViewName("activity/activity_userjoin_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	 /**导出到excel
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出Activity到excel");
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
		dataMap.put("titles", titles);
		List<PageData> varOList = activityService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).get("ID").toString());	//1
			vpd.put("var2", varOList.get(i).get("BANK_ID").toString());	//2
			vpd.put("var3", varOList.get(i).get("BANK_CREDIT_CARD_ID").toString());	//3
			vpd.put("var4", varOList.get(i).getString("ACTIVITY_NAME"));	    //4
			vpd.put("var5", varOList.get(i).getString("ACTIVITY_TIME_START"));	    //5
			vpd.put("var6", varOList.get(i).getString("ACTIVITY_TIME_END"));	    //6
			vpd.put("var7", varOList.get(i).getString("ACTIVITY_POSITION"));	    //7
			vpd.put("var8", varOList.get(i).get("ACTIVITY_TYPE").toString());	//8
			vpd.put("var9", varOList.get(i).get("IS_HAS_SIGN_UP").toString());	//9
			vpd.put("var10", varOList.get(i).getString("DESCRIBE"));	    //10
			vpd.put("var11", varOList.get(i).getString("ACTIVITY_CUSTOM_LABLE"));	    //11
			vpd.put("var12", varOList.get(i).getString("NOTIC"));	    //12
			vpd.put("var13", varOList.get(i).get("CREATE_USER_ID").toString());	//13
			vpd.put("var14", varOList.get(i).getString("CREATE_TIME"));	    //14
			vpd.put("var15", varOList.get(i).get("UPDATE_USER_ID").toString());	//15
			vpd.put("var16", varOList.get(i).getString("UPDATE_TIME"));	    //16
			vpd.put("var17", varOList.get(i).get("ACTIVITY_LOOK_NUMBER").toString());	//17
			vpd.put("var18", varOList.get(i).getString("STATUS"));	    //18
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
}
