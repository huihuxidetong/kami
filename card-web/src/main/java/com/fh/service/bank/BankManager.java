package com.fh.service.bank;

import java.util.List;
import com.fh.entity.Page;
import com.fh.util.PageData;

/** 
 * 说明： 信用卡银行接口
 * 创建人：FH Q313596790
 * 创建时间：2018-09-27
 * @version
 */
public interface BankManager{

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

	/**去信用卡当前列表
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> creditList(Page page)throws Exception;

	/**新增信用卡
	 * @param pd
	 * @throws Exception
	 */
	public void addBankCreditCard(PageData pd)throws Exception;

	/**
	 * @mnots 更新信用卡名称
	 * @param pageData
	 */
	public void updateBankCreditCard(PageData pageData) throws Exception;
}

