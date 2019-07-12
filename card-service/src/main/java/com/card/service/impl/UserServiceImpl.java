package com.card.service.impl;

import com.card.inteface.dao.UserDao;
import com.card.inteface.dao.UserMobileBasicDao;
import com.card.inteface.entity.User;
import com.card.inteface.entity.UserMobileBasic;
import com.card.inteface.service.UserService;
import com.card.inteface.vo.UserBasicVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @notes:用户Service实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserMobileBasicDao userMobileBasicDao;

	/**
	 * @notes 通过openId查询用户信息
	 * @param openId
	 */
	public User findUserByOpenId(String openId) throws Exception {
		return  userDao.findUserByOpenId(openId);
	}

	/**
	 * @notes 通过userId查询用户信息
	 * @param userId
	 */
	public User findUserByUserId(Integer userId) throws Exception{
		return userDao.findUserByUserId(userId);
	}

	/**
	 * @notes 保存用户信息
	 * @param user
	 */
	public void saveUser(User user) throws Exception {
		userDao.saveUser(user);
	}

	/**
	 * @notes 更新用户信息
	 * @param user
	 */
	public void updateUser(User user) throws Exception {
		userDao.updateUser(user);
	}

	/**
	 * @notes 保存用户信息
	 * @param userBasicVo
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void saveUserUserBasicVo(UserBasicVo userBasicVo) throws Exception {
		//保存用户信息
		User user = new User();
		BeanUtils.copyProperties(userBasicVo,user);
		userDao.saveUser(user);
		//保存用户手机信息
		UserMobileBasic userMobileBasic = new UserMobileBasic();
		BeanUtils.copyProperties(userBasicVo,userMobileBasic);
		userMobileBasic.setUserId(user.getId());
		userMobileBasicDao.saveUserMobileBasic(userMobileBasic);
	}
}
