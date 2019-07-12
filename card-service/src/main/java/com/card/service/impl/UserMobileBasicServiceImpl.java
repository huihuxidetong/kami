package com.card.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.inteface.service.UserMobileBasicService;  
import com.card.inteface.dao.UserMobileBasicDao;
import com.card.inteface.entity.UserMobileBasic;  

/**
 * @notes:用户手机基本信息Service实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
@Service
public class UserMobileBasicServiceImpl implements UserMobileBasicService {

	@Autowired
	private UserMobileBasicDao userMobileBasicDao;

}
