package com.card.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.inteface.entity.CreditCardApplyData;  
import com.card.inteface.dao.CreditCardApplyDataDao;  
import com.card.inteface.service.CreditCardApplyDataService;

import java.util.Date;

/**
 * @notes:信用卡数据Service实现类
 *
 * @author zzh
 *
 * 2018-10-12 11:20:54		Email:azhangzhihengi@163.com
 */
@Service
public class CreditCardApplyDataServiceImpl implements CreditCardApplyDataService {

	@Autowired
	private CreditCardApplyDataDao creditCardApplyDataDao;

	/**
	 * @ntoes 信用卡申请
	 * @param creditCardApplyData
	 */
	public void addCreditCardApplyData(CreditCardApplyData creditCardApplyData) throws Exception {
		CreditCardApplyData creditCardApplyData1 = creditCardApplyDataDao.findCreditCardApplyData(creditCardApplyData);
		if(null == creditCardApplyData1) {
			creditCardApplyData.setCreateTime(new Date());
			creditCardApplyDataDao.addCreditCardApplyData(creditCardApplyData);
		}
	}
}
