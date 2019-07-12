package com.card.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.inteface.entity.CreditCardQuestion;  
import com.card.inteface.dao.CreditCardQuestionDao;
import com.card.inteface.service.CreditCardQuestionService;

import java.util.List;

/**
 * @notes:信用卡问题Service实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
@Service
public class CreditCardQuestionServiceImpl implements CreditCardQuestionService {

	@Autowired
	private CreditCardQuestionDao creditCardQuestionDao;

	/**
	 * @通过信用卡id查询常见问题
	 * @param creditCardId
	 * @return
	 */
	public List<CreditCardQuestion> findcreditCardQuestionByCreditCardId(Integer creditCardId) throws Exception {
		return creditCardQuestionDao.findcreditCardQuestionByCreditCardId(creditCardId);
	}
}
