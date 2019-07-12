package com.fh.service.bank.impl;

import java.io.File;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import com.fh.util.PropertiesUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;
import com.fh.service.bank.BankManager;
/** 
 * 说明： 信用卡银行
 * 创建人：FH Q313596790
 * 创建时间：2018-09-27
 * @version
 */
@Service("bankService")
public class BankService implements BankManager{
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Autowired
	private PropertiesUtil propertiesUtil;
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		pd.put("bank_logo",pd.get("sourceFilePath"));
		dao.save("BankMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("BankMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		pd.put("bank_logo",pd.get("sourceFilePath"));
		dao.update("BankMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		List<PageData> pageDataList = (List<PageData>)dao.findForList("BankMapper.datalistPage", page);
		if(null != pageDataList && pageDataList.size()>0){
			for(PageData pageData : pageDataList){
				pageData.put("downSourceFilePath",propertiesUtil.getFileDownloadUrl() + pageData.get("bank_logo"));
			}
		}
		return  pageDataList;
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("BankMapper.listAll", pd);
	}


	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		PageData pageData= (PageData)dao.findForObject("BankMapper.findById", pd);
		if (null != pageData){
			List<PageData> pageDataCreditName = (List<PageData>) dao.findForList("CreditBankMapper.findByCreditName",pageData);
			pageData.put("pageDataCreditName",pageDataCreditName);
			String bank_logo = pageData.getString("bank_logo");
			if(StringUtils.isNotBlank(bank_logo)){
				pageData.put("downloand_source_file_url", propertiesUtil.getFileDownloadUrl() + pageData.get("bank_logo"));
			}else{
				pageData.put("sourceFilePath",propertiesUtil.getDefault_img_url());
				pageData.put("downloand_source_file_url", propertiesUtil.getFileDownloadUrl() + File.separator + propertiesUtil.getDefault_img_url());
			}
		}
		return pageData;
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("BankMapper.deleteAll", ArrayDATA_IDS);
	}

	/**查找当前银行下的信用卡
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> creditList(Page page)throws Exception{
		List<PageData> pageDataList = (List<PageData>)dao.findForList("BankMapper.creditByBankId", page);
		return pageDataList;
	}

	/**新增信用卡
	 * @param pd
	 * @throws Exception
	 */
	public void addBankCreditCard(PageData pd)throws Exception{
		dao.save("CreditBankMapper.save",pd);
	}

	/**
	 * @mnots 更新信用卡名称
	 * @param pageData
	 */
	public void updateBankCreditCard(PageData pageData) throws Exception {
		dao.update("CreditBankMapper.updateBankCreditCard",pageData);
	}

}

