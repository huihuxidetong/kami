package com.fh.service.usercard.impl;

import java.io.File;
import java.util.List;
import javax.annotation.Resource;

import com.fh.util.PropertiesUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;
import com.fh.service.usercard.UserCardManager;

/** 
 * 说明： 用户管理
 * 创建人：FH Q313596790
 * 创建时间：2018-09-29
 * @version
 */
@Service("usercardService")
public class UserCardService implements UserCardManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Autowired
	private PropertiesUtil propertiesUtil;
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("UserCardMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("UserCardMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("UserCardMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		List<PageData> pageDataList= (List<PageData>)dao.findForList("UserCardMapper.datalistPage", page);
		if(null != pageDataList && pageDataList.size()>0){
			for(PageData pageData : pageDataList){
				String create_time=String.valueOf(pageData.get("create_time"));
				pageData.put("create_time",create_time.substring(0,16));
				pageData.put("downSourceFilePath",pageData.get("user_portrait"));
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
		List<PageData> pageDataList= (List<PageData>)dao.findForList("UserCardMapper.listAll", pd);
		if (null != pageDataList && pageDataList.size()>0){
			for(PageData pageData : pageDataList){
				String wh=String.valueOf(pageData.get("mobile_dpr_wide")+String.valueOf(pageData.get("mobile_dpr_high")));
				pageData.put("mobile_dpr_wide"+"*"+"mobile_dpr_high",wh);
			}

		}
		return pageDataList;
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		PageData pageData= (PageData)dao.findForObject("UserCardMapper.findById", pd);
		if (null != pageData){
			String user_portrait = pageData.getString("user_portrait");
			if(StringUtils.isNotBlank(user_portrait)){
				pageData.put("downloand_source_file_url", pageData.get("user_portrait"));
			}else{
				pageData.put("sourceFilePath",propertiesUtil.getDefault_img_url());
				pageData.put("downloand_source_file_url", File.separator + propertiesUtil.getDefault_img_url());
			}
		}
		return pageData;
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("UserCardMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

