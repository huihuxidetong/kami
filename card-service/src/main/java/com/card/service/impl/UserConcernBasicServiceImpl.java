package com.card.service.impl;

import com.card.inteface.dao.CreditCardConcernDao;
import com.card.inteface.entity.CreditCardConcern;
import com.card.inteface.utils.PageBean;
import com.card.inteface.vo.ActivityIndexVo;
import com.card.inteface.vo.CreditCardInfoHotVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.inteface.dao.UserConcernBasicDao;
import com.card.inteface.service.UserConcernBasicService;  
import com.card.inteface.entity.UserConcernBasic;

import java.util.Date;
import java.util.List;

/**
 * @notes:用户关注基本信息Service实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
@Service
public class UserConcernBasicServiceImpl implements UserConcernBasicService {

	@Autowired
	private UserConcernBasicDao userConcernBasicDao;


	/**
	 * @notes 查询关注的信用卡列表分页
	 * @param pageBean
	 * @return
	 */
	public PageBean<CreditCardInfoHotVo> findUserConcernBasic4CreditCardPage(PageBean<UserConcernBasic> pageBean) throws Exception {
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		List<CreditCardInfoHotVo> list = userConcernBasicDao.findUserConcernBasic4CreditCardPage(pageBean.getBean());
		return new PageBean<CreditCardInfoHotVo>(list);
	}

	/**
	 * @notes 查询关注的信用卡列表分页
	 * @param pageBean
	 * @return
	 */
	public PageBean<ActivityIndexVo> findUserConcernBasic4ActivityPage(PageBean<UserConcernBasic> pageBean) throws Exception {
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		List<ActivityIndexVo> list = userConcernBasicDao.findUserConcernBasic4ActivityPage(pageBean.getBean());
		return new PageBean<ActivityIndexVo>(list);
	}

	/**
	 * @notes 保存用户关注信息
	 * @param userConcernBasic
	 */
	public void saveUserConcernBasic(UserConcernBasic userConcernBasic) throws Exception {
		UserConcernBasic userConcernBasic1 = new UserConcernBasic();
		userConcernBasic1.setConcernId(userConcernBasic.getConcernId());
		userConcernBasic1.setUserId(userConcernBasic.getUserId());
		userConcernBasic1.setConcernType(userConcernBasic.getConcernType());
		userConcernBasic1 = userConcernBasicDao.findUserConcernBasicByEntity(userConcernBasic1);
		if(null == userConcernBasic1) {
			userConcernBasicDao.saveUserConcernBasic(userConcernBasic);
		}else{
			userConcernBasic1.setStatus(1);
			userConcernBasicDao.updateUserConcernBasic(userConcernBasic1);
		}
	}

	/**
	 * @notes 用户取消关注信息
	 * @param userConcernBasic
	 */
	public void updateUserConcernBasic(UserConcernBasic userConcernBasic) throws Exception{
		userConcernBasic.setStatus(0);
		userConcernBasicDao.updateUserConcernBasic(userConcernBasic);
	}

	/**
	 * @notes 查询关注信息
	 * @param userConcernBasic
	 * @return
	 */
	public UserConcernBasic findUserConcernBasicByEntity(UserConcernBasic userConcernBasic) throws Exception {
		return userConcernBasicDao.findUserConcernBasicByEntity(userConcernBasic);
	}
}
