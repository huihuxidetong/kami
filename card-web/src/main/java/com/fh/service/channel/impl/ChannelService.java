package com.fh.service.channel.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import com.fh.service.qr.QrCodeService;
import com.fh.util.PropertiesUtil;
import com.fh.util.StringUtil;
import com.fh.util.UuidUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;
import com.fh.service.channel.ChannelManager;

/** 
 * 说明： 渠道管理
 * 创建人：FH Q313596790
 * 创建时间：2018-09-29
 * @version
 */
@Service("channelService")
public class ChannelService implements ChannelManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Autowired
	private QrCodeService qrCodeService;
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		pd.put("code",pd.get("code"));
		pd.put("extension_url",pd.get("extension_url"));
		pd.put("cretae_user_id",0);
		pd.put("create_time",new Date());
		pd.put("update_user_id",0);
		pd.put("update_time",new Date());
		dao.save("ChannelMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("ChannelMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		pd.put("code",pd.get("code"));
		pd.put("extension_url",pd.get("extension_url"));
		pd.put("update_user_id",0);
		pd.put("update_time",new Date());
		dao.update("ChannelMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		List<PageData> pageDataList = (List<PageData>)dao.findForList("ChannelMapper.datalistPage", page);
		if(null != pageDataList){
			for(PageData pageData : pageDataList){
				String code = pageData.getString("code");
				PageData pageData1 = (PageData) dao.findForObject("ChannelMapper.findChannelData", code);
				pageData.put("data",pageData1);
			}
		}
		return pageDataList;
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ChannelMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		PageData pageData= (PageData)dao.findForObject("ChannelMapper.findById", pd);
		if (null != pageData){
			//pageData.put("channel_code",pageData.get("code"));
			pageData.put("download_qr_code_url",propertiesUtil.getFileDownloadUrl() +pageData.get("qr_code_url"));
			String code = pageData.getString("code");
			if(StringUtils.isNotBlank(code)) {
				PageData pageData1 = (PageData) dao.findForObject("ChannelMapper.findChannelData", code);
				pageData.put("data",pageData1);
			}
		}
		return pageData;
	}

	/**
	 * @notes 获取小程序二维码
	 * @param pageData
	 * @return
	 */
	public PageData qrQrCodeUrl(PageData pageData) throws Exception {
		PageData pageData1 = new PageData();
		String uuid = UuidUtil.get32UUID();
		//String embed_code = "";
		String params = "shareType=2&channelCode=" +uuid;
		String extension_url = "pages/index/index";
		String url = qrCodeService.getQrCodeUrl(extension_url+"?"+params,propertiesUtil.getAppid(),propertiesUtil.getSecret(),propertiesUtil.getGrant_type());
		pageData1.put("code",uuid);
		pageData1.put("extension_url",extension_url + "?"+params);
		pageData1.put("qr_code_url",propertiesUtil.getFileDownloadUrl() + url);
		//pageData1.put("embed_code","");
		return pageData1;
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("ChannelMapper.deleteAll", ArrayDATA_IDS);
	}

	/**
	 * 查询该渠道名称是否存在
	 * @param channel_name
	 * @return
	 * @throws Exception
	 */
	public Integer chang(String channel_name) throws Exception{
		return (Integer) dao.findForObject("ChannelMapper.selectObject",channel_name);
	}
}

