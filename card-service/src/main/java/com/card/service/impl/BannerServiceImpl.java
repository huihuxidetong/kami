package com.card.service.impl;

import com.card.inteface.vo.BannerVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.inteface.dao.BannerDao;
import com.card.inteface.entity.Banner;  
import com.card.inteface.service.BannerService;

import java.util.List;

/**
 * @notes:首页bannerService实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
@Service
public class BannerServiceImpl implements BannerService {

	Logger logger = LoggerFactory.getLogger(BannerServiceImpl.class);

	@Autowired
	private BannerDao bannerDao;

	/**
	 * @notes 查询首页banner信息
	 * @return
	 */
	@Override
	public List<BannerVo> findIndexBannerVo() throws Exception {
		logger.info("====================进入bannerServiceImp");
		return bannerDao.findIndexBannerVo();
	}

}
