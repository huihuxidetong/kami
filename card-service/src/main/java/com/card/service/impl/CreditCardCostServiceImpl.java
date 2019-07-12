package com.card.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.inteface.entity.CreditCardCost;  
import com.card.inteface.dao.CreditCardCostDao;
import com.card.inteface.service.CreditCardCostService;

import java.util.List;

/**
 * @notes:信用卡费用Service实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
@Service
public class CreditCardCostServiceImpl implements CreditCardCostService {

	@Autowired
	private CreditCardCostDao creditCardCostDao;

	/**
	 * @notes 通过信用卡id查询费用信息
	 * @param creditCardId
	 * @return
	 */
	public List<CreditCardCost> findCreditCardCostByCreditCardId(Integer creditCardId) throws Exception {
		return creditCardCostDao.findCreditCardCostByCreditCardId(creditCardId);
	}
}
