package com.fh.controller.channel;

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
import javax.servlet.http.HttpServletResponse;

import com.fh.util.*;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.channel.ChannelManager;

/** 
 * 说明：渠道管理
 * 创建人：FH Q313596790
 * 创建时间：2018-09-29
 */
@Controller
@RequestMapping(value="/channel")
public class ChannelController extends BaseController {
	
	String menuUrl = "channel/list.do"; //菜单地址(权限用)
	@Resource(name="channelService")
	private ChannelManager channelService;

	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public Object save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增channel");
		Map<String, Object> map = new HashMap<String, Object>();
		PageData pd = new PageData();
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		try {
			pd = this.getPageData();
			channelService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除Channel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		channelService.delete(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"新增channel");
		Map<String, Object> map = new HashMap<String, Object>();
		PageData pd = new PageData();
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		try {
			pd = this.getPageData();
			channelService.edit(pd);
			map.put("msg", "success");
			map.put("resultCode", "200");
		}catch (Exception e){
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
		List<PageData>	varList = channelService.list(page);	//列出Channel列表
		mv.setViewName("channel/channel_list");
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
		mv.setViewName("channel/channel_edit");
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
		pd = channelService.findById(pd);	//根据ID读取
		mv.setViewName("channel/channel_edit");
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
		pd = channelService.findById(pd);	//根据ID读取
		mv.setViewName("channel/channel_detail");
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Channel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			channelService.deleteAll(ArrayDATA_IDS);
			pd.put("msg", "ok");
		}else{
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}

	/**
	 * 查询渠道名称是否存在
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/change",method = RequestMethod.POST)
	@ResponseBody
	public Boolean chang(HttpServletRequest request) throws Exception{
		String channel_name = request.getParameter("channel_name");
		Integer count = channelService.chang(channel_name);
		if(count>0){
			return true;
		}else {
			return false;
		}
	}
	
	 /**导出到excel
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出Channel到excel");
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
		dataMap.put("titles", titles);
		List<PageData> varOList = channelService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).get("ID").toString());	//1
			vpd.put("var2", varOList.get(i).getString("CHANNEL_NAME"));	    //2
			vpd.put("var3", varOList.get(i).getString("CONTACTS_NAME"));	    //3
			vpd.put("var4", varOList.get(i).getString("CONTACTS_MOBILE"));	    //4
			vpd.put("var5", varOList.get(i).getString("EXTENSION_URL"));	    //5
			vpd.put("var6", varOList.get(i).getString("QR_CODE_URL"));	    //6
			vpd.put("var7", varOList.get(i).get("CREATE_USER_ID").toString());	//7
			vpd.put("var8", varOList.get(i).getString("CREATE_TIME"));	    //8
			vpd.put("var9", varOList.get(i).get("UPDATE_USER_ID").toString());	//9
			vpd.put("var10", varOList.get(i).getString("UPDATE_TIME"));	    //10
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		return mv;
	}

	/** 获取小程序二维码
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/getQrCodeUrl")
	@ResponseBody
	public Object getQrCodeUrl() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Games");
		Map<String, Object> map = new HashMap<String, Object>();
		PageData pd = new PageData();
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		try {
			PageData pageData = channelService.qrQrCodeUrl(pd);
			map.put("pd",pageData);
			map.put("msg", "success");
			map.put("resultCode", "200");
		}catch (Exception e){
			map.put("msg", "faile");
			map.put("resultCode", "500");
		}
		return AppUtil.returnObject(pd, map);
	}
	/** 下载小程序二维码
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/downloadQrCode")
	@ResponseBody
	public void downloadQrCode(final HttpServletRequest request, final HttpServletResponse response) throws Exception{
		PageData pd = new PageData();
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		FileDownload.downLoadFile(pd.getString("qr_code_url"),request,response);
	}

	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
