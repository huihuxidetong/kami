package com.card.service.impl;

import com.card.inteface.dao.UserDao;
import com.card.inteface.entity.User;
import com.card.inteface.utils.PageBean;
import com.card.inteface.vo.CreditCardInfoHotVo;
import com.card.inteface.vo.MyCreditCardInfoVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.inteface.dao.UserCreditCardDao;
import com.card.inteface.service.UserCreditCardService;  
import com.card.inteface.entity.UserCreditCard;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @notes:用户绑定信用卡Service实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
@Service
public class UserCreditCardServiceImpl implements UserCreditCardService {

	@Autowired
	private UserCreditCardDao userCreditCardDao;

	@Autowired
	private UserDao userDao;

	/**
	 * @ntoes 查询用户头像信息
	 * @param userId
	 * @return
	 */
	public Integer findUserCreditCardLCount(Integer userId) throws Exception {
		return userCreditCardDao.findUserCreditCardLCount(userId);
	}

	/**
	 * @note 查询用户绑定信用卡列表
	 * @param userId
	 * @return
	 */
	public List<MyCreditCardInfoVo> findMyCreditCardListPage(Integer userId) throws Exception {
		return userCreditCardDao.findMyCreditCardList(userId);
	}

	/**
	 * @notes 保存用户信用卡
	 * @param userCreditCard
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveUserCreditCard(UserCreditCard userCreditCard) throws Exception {
		//保存用户信用卡
		userCreditCardDao.saveUserCreditCard(userCreditCard);
		//更新用户积分
		User user = userDao.findUserByUserId(userCreditCard.getUserId());
		if(null != user){
			user.setUserIntegral(user.getUserIntegral()+10);
			userDao.updateUser(user);
		}
	}
}
