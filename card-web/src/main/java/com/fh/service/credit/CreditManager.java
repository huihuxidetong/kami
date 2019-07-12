package com.fh.service.credit;

import java.util.List;
import com.fh.entity.Page;
import com.fh.util.PageData;

/** 
 * 说明： 信用卡管理接口
 * 创建人：FH Q313596790
 * 创建时间：2018-09-26
 * @version
 */
public interface CreditManager{

	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception;
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception;
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception;
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page)throws Exception;
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> listAll(PageData pd)throws Exception;
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception;

	/**
	 * 更新信用卡的状态
	 * @param pageData
	 * @throws Exception
	 */
	public void updateCreditState(PageData pageData)throws Exception;

	/**
	 * @notes 根据银行查询Id查询所有的信用卡
	 * @param pageData
	 * @return
	 */
	public List<PageData> findAllCreditCardByBankId(PageData pageData) throws Exception;
	
}

