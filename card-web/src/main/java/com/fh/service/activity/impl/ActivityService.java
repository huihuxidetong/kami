package com.fh.service.activity.impl;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import com.fh.util.PropertiesUtil;
import com.fh.vo.Notic;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;
import com.fh.service.activity.ActivityManager;

/** 
 * 说明： 活动管理
 * 创建人：FH Q313596790
 * 创建时间：2018-09-26
 * @version
 */
@Service("activityService")
public class ActivityService implements ActivityManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		pd.put("bank_id",pd.get("bank_ids"));
		pd.put("bank_credit_card_id",pd.get("credit_card_ids"));
		pd.put("notic",pd.get("edithtml"));
		pd.put("status",1);

		String is_unlimitedt_bank_credit_card = String.valueOf(pd.get("is_unlimitedt_bank_credit_card"));
		is_unlimitedt_bank_credit_card = (StringUtils.isBlank(is_unlimitedt_bank_credit_card) || null == is_unlimitedt_bank_credit_card || "null".equalsIgnoreCase(is_unlimitedt_bank_credit_card)) ? "0" : is_unlimitedt_bank_credit_card;
		pd.put("is_unlimitedt_bank_credit_card",is_unlimitedt_bank_credit_card);
		if(1 == Integer.valueOf(is_unlimitedt_bank_credit_card)){
			String bank_id = (String) dao.findForObject("BankMapper.listAllIds",null);
			if(StringUtils.isNotBlank(bank_id) && "" != bank_id) {
				String bank_credit_card_id = (String) dao.findForObject("CreditMapper.findAllCreditCardIdsByBankId", bank_id);
				pd.put("bank_id",bank_id);
				pd.put("bank_credit_card_id",bank_credit_card_id);
			}
		}

		dao.save("ActivityMapper.save", pd);
		//保存文件信息
		String urlList = pd.getString("urlList");
		if(StringUtils.isNotBlank(urlList)){
			//插入新数据
			PageData pageData = new PageData();
			for(String img : urlList.split(",")){
				pageData.put("activity_id",pd.get("id"));
				pageData.put("image_url",img);
				pageData.put("status",1);
				pageData.put("create_time",new Date());
				dao.save("ActivityMapper.saveActivityFile",pageData);
			}
		}
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("ActivityMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		pd.put("id",pd.get("activity_id"));
		pd.put("bank_id",pd.get("bank_ids"));
		pd.put("bank_credit_card_id",pd.get("credit_card_ids"));
		pd.put("notic",pd.get("edithtml"));
		String is_unlimitedt_bank_credit_card = String.valueOf(pd.get("is_unlimitedt_bank_credit_card"));
		is_unlimitedt_bank_credit_card = (StringUtils.isBlank(is_unlimitedt_bank_credit_card) || null == is_unlimitedt_bank_credit_card || "null".equalsIgnoreCase(is_unlimitedt_bank_credit_card)) ? "0" : is_unlimitedt_bank_credit_card;
		pd.put("is_unlimitedt_bank_credit_card",is_unlimitedt_bank_credit_card);
		if(1 == Integer.valueOf(is_unlimitedt_bank_credit_card)){
			String bank_id = (String) dao.findForObject("BankMapper.listAllIds",null);
			if(StringUtils.isNotBlank(bank_id) && "" != bank_id) {
				String bank_credit_card_id = (String) dao.findForObject("CreditMapper.findAllCreditCardIdsByBankId", bank_id);
				pd.put("bank_id",bank_id);
				pd.put("bank_credit_card_id",bank_credit_card_id);
			}
		}
		dao.update("ActivityMapper.edit", pd);
		//保存文件信息
		String urlList = pd.getString("urlList");
		if(StringUtils.isNotBlank(urlList)){
			//更新元文件为不可用
			dao.update("ActivityMapper.updateActivityFile",Integer.valueOf(String.valueOf(pd.get("activity_id"))));
			//插入新数据
			PageData pageData = new PageData();
			for(String img : urlList.split(",")){
				pageData.put("activity_id",pd.get("activity_id"));
				pageData.put("image_url",img);
				pageData.put("status",1);
				pageData.put("create_time",new Date());
				dao.save("ActivityMapper.saveActivityFile",pageData);
			}
		}
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		List<PageData> pageDataList=(List<PageData>)dao.findForList("ActivityMapper.datalistPage",page);
		if (null != pageDataList && pageDataList.size()>0){
			for (PageData pageData:pageDataList){
				String activity_time_start=String.valueOf(pageData.get("activity_time_start"));
				pageData.put("activity_time_start",activity_time_start.substring(0,11));
				String activity_time_end=String.valueOf(pageData.get("activity_time_end"));
				pageData.put("activity_time_end",activity_time_end.substring(0,11));
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
		return (List<PageData>)dao.findForList("ActivityMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		PageData pageData= (PageData)dao.findForObject("ActivityMapper.findById", pd);
		if (null != pageData){
			/*List<PageData> joinActivityNum = (List<PageData>) dao.findForList("ActivityApplyMapper.avtivityListPage",pageData);
			pageData.put("joinActivityNum",joinActivityNum);*/
			Notic notic = (Notic) dao.findForObject("ActivityMapper.findNotic",pd);
			if(null != notic) {
				pageData.put("notic", notic.getNotic());
			}
			PageData pageData1 = new PageData();
			pageData1.put("activity_id",pd.get("id"));
			List<PageData> imageUrlList = (List<PageData>) dao.findForList("ActivityMapper.findActivityFile",pageData1);
			if(null != imageUrlList && imageUrlList.size()>0){
				for(PageData pageData2 : imageUrlList){
					String image_url=pageData2.getString("image_url");
					if (StringUtils.isNotBlank(image_url)){
						pageData2.put("downloand_source_file_url",propertiesUtil.getFileDownloadUrl()+pageData2.get("image_url"));
					}else {
						pageData2.put("sourceFilePath",propertiesUtil.getDefault_img_url());
						pageData2.put("downloand_source_file_url", propertiesUtil.getFileDownloadUrl()+ propertiesUtil.getDefault_img_url());
					}
				}
				pageData.put("imageList",imageUrlList);
			}
			String activity_custom_lable = pageData.getString("activity_custom_lable");
			if(StringUtils.isNotBlank(activity_custom_lable)){
				pageData.put("activity_custom_lable", Arrays.asList(activity_custom_lable.split(",")));
			}
			String activity_time_start=String.valueOf(pageData.get("activity_time_start"));
			pageData.put("activity_time_start",activity_time_start.substring(0,11));
			String activity_time_end=String.valueOf(pageData.get("activity_time_end"));
			pageData.put("activity_time_end",activity_time_end.substring(0,11));
		}
		return  pageData;
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("ActivityMapper.deleteAll", ArrayDATA_IDS);
	}

	/**
	 * @notes 更新状态为已经下线
	 * @param pageData
	 */
	public void updateActivity(PageData pageData) throws Exception{
		dao.update("ActivityMapper.updateActivity",pageData);
	}

	/**
	 * 活动名单
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> activityList(Page page)throws Exception{
		List<PageData> pageDataList= (List<PageData>)dao.findForList("ActivityApplyMapper.avtivityListPage", page);
		/*//截取时间字符串
		if (null != pageDataList && pageDataList.size()>0){
			for (PageData pageData:pageDataList){
				String create_time=String.valueOf(pageData.get("create_time"));
				pageData.put("create_time",create_time.substring(0,11));
			}
		}*/
		return pageDataList;
	}

	/**
	 * @notes 查询
	 * @param pageData
	 * @return
	 */
	public Integer findTotalJoinUserNum(PageData pageData) throws Exception {
		return (Integer) dao.findForObject("ActivityApplyMapper.findTotalJoinUserNum",pageData);
	}
	
}

