package com.card.service.impl;

import com.card.inteface.utils.PageBean;
import com.card.inteface.vo.*;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.inteface.entity.Activity;  
import com.card.inteface.dao.ActivityDao;
import com.card.inteface.service.ActivityService;

import java.util.List;

/**
 * @notes:活动Service实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityDao activityDao;

	/**
	 * @ntoes 查询首页活动信息信息
	 * @return
	 */
	public List<ActivityVo> findIndexActivityVo() throws Exception {
		return activityDao.findIndexActivityVo();
	}

	/**
	 * @notes 查询信用卡相关活动
	 * @param creditCardId
	 * @return
	 */
	public List<CreditDetailActivityVo> findActivityVoListByCreditCardId(Integer creditCardId) throws Exception {
		return activityDao.findActivityVoListByCreditCardId(creditCardId);
	}

	/**
	 * @ntos 查询活动列表
	 * @return
	 */
	public List<ActivityVo> findActivityList() throws Exception {
		return activityDao.findActivityList();
	}

	/**
	 * @ntoes
	 * @param activityId
	 * @return
	 */
	public List<String> findActivityImageUrl(Integer activityId) throws Exception {
		return activityDao.findActivityImageUrl(activityId);
	}

	/**
	 * @ntoes 查询活动详情
	 * @param queryActivityVo
	 * @return
	 */
	public ActivityDetailVo findActivityDetailVo(QueryActivityVo queryActivityVo) throws Exception {
		Activity activity = activityDao.findActivity(queryActivityVo.getId());
		if(null != activity){
			activity.setActivityLookNumber(activity.getActivityLookNumber() + 1);
			activityDao.updateActivity(activity);
		}
		return activityDao.findActivityDetailVo(queryActivityVo);
	}

	/**
	 * @param activityId
	 * @return
	 * @ntoes 相关信用卡
	 */
	public List<ActivityCreditCardVo> findActivityCreditCardVo(Integer activityId) throws Exception {
		return activityDao.findActivityCreditCardVo(activityId);
	}

	/**
	 * @ntoe 查询活动相关信用卡列表
	 * @param pageBean
	 * @return
	 */
	public PageBean<ActivityCreditCardVo> findActivityCreditCardPage(PageBean<ActivityCreditCardVo> pageBean) throws Exception {
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		List<ActivityCreditCardVo> list = activityDao.findActivityCreditCardPage(pageBean.getBean().getActivityId());
		return new PageBean<ActivityCreditCardVo>(list);
	}
}
