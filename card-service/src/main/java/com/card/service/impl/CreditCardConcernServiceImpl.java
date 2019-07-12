package com.card.service.impl;

import com.card.inteface.utils.PageBean;
import com.card.inteface.vo.CreditCardConcernVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.inteface.dao.CreditCardConcernDao;
import com.card.inteface.service.CreditCardConcernService;  
import com.card.inteface.entity.CreditCardConcern;

import java.util.List;

/**
 * @notes:信用卡关注Service实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
@Service
public class CreditCardConcernServiceImpl implements CreditCardConcernService {

	@Autowired
	private CreditCardConcernDao creditCardConcernDao;

	/**
	 * @notes 查询关注者头像
	 * @param creditCardId
	 * @return
	 */
	public List<String> findCreditCardConcernPortraitLimit(Integer creditCardId) throws Exception {
		return creditCardConcernDao.findCreditCardConcernPortraitLimit(creditCardId);
	}

	/**
	 * @notes 查询关注者统计
	 * @param creditCardId
	 * @return
	 */
	public Integer findCreditCardConcernCount(Integer creditCardId) throws Exception{
		return creditCardConcernDao.findCreditCardConcernCount(creditCardId);
	}

	/**
	 * @notse 查询关注列表
	 * @param pageBean
	 * @return
	 */
	public PageBean<CreditCardConcernVo> findCreditCardConcernPage(PageBean<CreditCardConcernVo> pageBean) throws Exception {
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		List<CreditCardConcernVo> list = creditCardConcernDao.findCreditCardConcernPage(pageBean.getBean().getCreditCardId());
		return new PageBean<CreditCardConcernVo>(list);
	}
}
