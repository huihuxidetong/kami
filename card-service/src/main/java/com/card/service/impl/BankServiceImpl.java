package com.card.service.impl;

import com.card.inteface.vo.BankVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.inteface.dao.BankDao;
import com.card.inteface.entity.Bank;  
import com.card.inteface.service.BankService;

import java.util.List;

/**
 * @notes:银行Service实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
@Service
public class BankServiceImpl implements BankService {

	@Autowired
	private BankDao bankDao;

	/**
	 * @notes 查询所有银行信息
	 * @return
	 */
	public List<BankVo> findBankVoList() throws Exception {
		return bankDao.findBankVoList();
	}

}
