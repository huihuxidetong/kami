package com.card.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.inteface.dao.ChannelDataDao;
import com.card.inteface.entity.ChannelData;  
import com.card.inteface.service.ChannelDataService;  

/**
 * @notes:渠道数据Service实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
@Service
public class ChannelDataServiceImpl implements ChannelDataService {

	@Autowired
	private ChannelDataDao channelDataDao;

	/**
	 * @notes 保存渠道信息
	 * @param channelData
	 */
	public void saveChannelData(ChannelData channelData) throws Exception {
		channelDataDao.saveChannelData(channelData);
	}

}
