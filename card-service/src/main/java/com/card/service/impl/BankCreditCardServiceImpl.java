package com.card.service.impl;

import com.card.inteface.vo.CreditCardInfoQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.inteface.service.BankCreditCardService;  
import com.card.inteface.dao.BankCreditCardDao;
import com.card.inteface.entity.BankCreditCard;

import java.util.List;

/**
 * @notes:银行关联信用卡Service实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
@Service
public class BankCreditCardServiceImpl implements BankCreditCardService {

	@Autowired
	private BankCreditCardDao bankCreditCardDao;

	/**
	 * @ntoes 搜索信用卡
	 * @param bankCreditCard
	 * @return
	 */
	public List<CreditCardInfoQueryVo> queryCreditCardList(BankCreditCard bankCreditCard) throws Exception {
		return bankCreditCardDao.queryCreditCardList(bankCreditCard);
	}

}
