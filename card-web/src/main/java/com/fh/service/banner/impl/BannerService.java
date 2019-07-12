package com.fh.service.banner.impl;

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
import com.fh.service.banner.BannerManager;

/** 
 * 说明： 推荐管理
 * 创建人：FH Q313596790
 * 创建时间：2018-09-26
 * @version
 */
@Service("bannerService")
public class BannerService implements BannerManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		pd.put("banner_image",pd.get("sourceFilePath"));
		int banner_type = Integer.valueOf(pd.get("banner_type").toString());
		if(1 == banner_type){
			pd.put("banner_url",pd.get("banner_route"));
		}else{
			pd.put("banner_url",pd.get("banner_like"));
		}
		dao.save("BannerMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("BannerMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		pd.put("id",pd.get("id"));
		pd.put("banner_image",pd.get("sourceFilePath"));
		int banner_type = Integer.valueOf(pd.get("banner_type").toString());
		if(1 == banner_type){
			pd.put("banner_url",pd.get("banner_route"));
		}else{
			pd.put("banner_url",pd.get("banner_like"));
		}
		dao.update("BannerMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		List<PageData> pageDataList=(List<PageData>)dao.findForList("BannerMapper.datalistPage", page);
		for (PageData pageData:pageDataList){
			String imgUrl = pageData.getString("banner_image");
			if(StringUtils.isNotBlank(imgUrl)){
				imgUrl = propertiesUtil.getFileDownloadUrl() + imgUrl;
				pageData.put("banner_image",imgUrl);
			}else{
				pageData.put("banner_image","static/images/e.jpg");
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
		return (List<PageData>)dao.findForList("BannerMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		PageData pageData= (PageData)dao.findForObject("BannerMapper.findById", pd);
		if (null != pageData && pageData.size()>0){
			String banner_image=pageData.getString("banner_image");
			if (StringUtils.isNotBlank(banner_image)){
				pageData.put("downloand_source_file_url",propertiesUtil.getFileDownloadUrl()+pageData.get("banner_image"));
			}else{
				pageData.put("sourceFilePath",propertiesUtil.getDefault_img_url());
				pageData.put("downloand_source_file_url",propertiesUtil.getFileDownloadUrl()+ File.separator+propertiesUtil.getDefault_img_url());
			}
		}
		return  pageData;
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("BannerMapper.deleteAll", ArrayDATA_IDS);
	}

	/**
	 * 判断Banner的sort是否相同
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public Integer chang(String sort) throws Exception{
		return(Integer) dao.findForObject("BannerMapper.selectObject",sort);
	}
}

