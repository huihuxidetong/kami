package com.card.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.inteface.service.ActivityFileService;  
import com.card.inteface.dao.ActivityFileDao;

/**
 * @notes:活动文件Service实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
@Service
public class ActivityFileServiceImpl implements ActivityFileService {

	@Autowired
	private ActivityFileDao activityFileDao;

}
