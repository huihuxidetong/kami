package com.card.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.inteface.dao.ActivityApplyDao;
import com.card.inteface.service.ActivityApplyService;  
import com.card.inteface.entity.ActivityApply;  

/**
 * @notes:活动申请Service实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
@Service
public class ActivityApplyServiceImpl implements ActivityApplyService {

	@Autowired
	private ActivityApplyDao activityApplyDao;

	/**
	 * @notes 保存活动申请
	 * @param activityApply
	 */
	public void saveActivityApply(ActivityApply activityApply) throws Exception {
		activityApplyDao.saveActivityApply(activityApply);
	}


}
